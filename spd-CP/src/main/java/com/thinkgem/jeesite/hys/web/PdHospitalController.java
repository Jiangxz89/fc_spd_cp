/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.CheckUtils;
import com.thinkgem.jeesite.common.utils.HysFileUploadUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.hys.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.hys.entity.PdHospital;
import com.thinkgem.jeesite.hys.entity.PdSupplierMidHospital;
import com.thinkgem.jeesite.hys.service.PdHospitalService;
import com.thinkgem.jeesite.hys.service.PdSupplierMidHospitalService;
import com.thinkgem.jeesite.hys.utils.CommonUtils;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.City;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import net.sf.json.JSONArray;

/**
 * 医院表Controller
 * @author sutianqi
 * @version 2018-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdHospital")
public class PdHospitalController extends BaseController {

	@Autowired
	private PdHospitalService pdHospitalService;
	@Autowired
	private PdSupplierMidHospitalService pdSupplierMidHospitalService;
	@Autowired
	private AreaService areaService;
	
	@ModelAttribute
	public PdHospital get(@RequestParam(required=false) String id) {
		PdHospital entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdHospitalService.get(id);
		}
		if (entity == null){
			entity = new PdHospital();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:pdHospital:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdHospital pdHospital, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdHospital> page = pdHospitalService.findPage(new Page<PdHospital>(request, response), pdHospital); 
		Area area = new Area();
		area.setId("1");
		area.setType(MinaGlobalConstants.AREA_2);
		List<City> findList = areaService.findListByParentCode(area);
		model.addAttribute("page", page);
		model.addAttribute("provincelist", JSONArray.fromObject(findList));//城市信息
		model.addAttribute("hospital", pdHospital);
		return "hys/pd/pdHospitalList";
	}

	@RequiresPermissions("hys:pdHospital:view")
	@RequestMapping(value = "form")
	public String form(PdHospital pdHospital, Model model, HttpServletRequest request) {
		String flag = request.getParameter("flag");
		
		if(flag.equals("see") || flag.equals("update")){
			String id = request.getParameter("id");
			PdHospital hos = pdHospitalService.get(id);
			model.addAttribute("hospital",hos);
		}
		//查询所有城市
		Area area = new Area();
		area.setId("1");
		area.setType(MinaGlobalConstants.AREA_2);
		List<City> findList = areaService.findListByParentCode(area);
		model.addAttribute("flag",flag);
		model.addAttribute("pdHospital", pdHospital);
		model.addAttribute("provincelist", JSONArray.fromObject(findList));//城市信息
		return "hys/pd/pdHospitalForm";
	}

	@RequiresPermissions("hys:pdHospital:edit")
	@RequestMapping(value = "save")
	public String save(PdHospital pdHospital, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pdHospital)){
			return form(pdHospital, model, request);
		}
		//校验医院名称和编号是否正确
		PdHospital pdHospitalName = new PdHospital();
		pdHospitalName.setName(pdHospital.getName());
		List<PdHospital>  hospitals = pdHospitalService.verify(pdHospitalName);
		if(hospitals!=null && hospitals.size()>0){
			addMessage(redirectAttributes, "保存医院失败,医院名称已存在");
		}else{
			pdHospitalService.save(pdHospital);
			addMessage(redirectAttributes, "保存医院成功");
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdHospital/?repage";
	}
	
	@RequiresPermissions("hys:pdHospital:edit")
	@RequestMapping(value = "update")
	public String update(PdHospital pdHospital, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pdHospital)){
			return form(pdHospital, model, request);
		}
		//校验医院名称和编号是否正确
		PdHospital pdHospitalName = new PdHospital();
		pdHospitalName.setName(pdHospital.getName());
		pdHospitalName.setId(pdHospital.getId());
		List<PdHospital>  hospitals = pdHospitalService.verify(pdHospitalName);
		if(hospitals!=null && hospitals.size()>0){
			addMessage(redirectAttributes, "更新医院失败,医院名称已存在");
		}else{
			pdHospitalService.update(pdHospital);
			addMessage(redirectAttributes, "更新成功");
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdHospital/?repage";
	}
	
	@RequiresPermissions("hys:pdHospital:edit")
	@RequestMapping(value = "delete")
	public String delete(PdHospital pdHospital, RedirectAttributes redirectAttributes) {
		
		PdSupplierMidHospital mid = new PdSupplierMidHospital();
		mid.setHospitalId(pdHospital.getId());
		List<PdSupplierMidHospital> findList = pdSupplierMidHospitalService.findList(mid);
		
		if(findList.size() > 0){
			addMessage(redirectAttributes, "有关联供应商，删除医院失败");
		}else{
			pdHospitalService.delete(pdHospital);
			addMessage(redirectAttributes, "删除医院成功");
		}
		
		
		return "redirect:"+Global.getAdminPath()+"/hys/pdHospital/?repage";
	}
	
	@RequestMapping(value = "getList")
	public String getList(PdHospital pdHospital, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdHospital> page = pdHospitalService.findPage(new Page<PdHospital>(request, response), pdHospital); 
		Area area = new Area();
		area.setId("1");
		area.setType(MinaGlobalConstants.AREA_2);
		List<City> findList = areaService.findListByParentCode(area);
		model.addAttribute("page", page);
		model.addAttribute("provincelist", JSONArray.fromObject(findList));//城市信息
		model.addAttribute("hospital", pdHospital);
		return "hys/pd/box/pdSupplierBox";
	}
	
	
	/**
	 * 校验医院编号或名称是否存在
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("hys:pdSupplier:view")
	@RequestMapping(value = "checkSupplierNameOrNumber")
	public String checkSupplierNameOrNumber( Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		//查询供应商名称是否已经存在
		String number = request.getParameter("number");//医院编号
		String name = request.getParameter("name");//医院名称
		String id = request.getParameter("id");
		try {
			PdHospital hospital = new PdHospital();
			if(number!=null && !"".equals(number)){
				hospital.setNumber(number);
			}
			if(name!=null && !"".equals(name)){
				hospital.setName(name);
			}
			if(id!=null && !"".equals(id)){
				hospital.setId(id);
			}
			List<PdHospital>  hospitals = pdHospitalService.verify(hospital);
			if(hospitals!=null && hospitals.size()>0){
				return "false";
			}else{
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	/**
	 * 生成医院编号
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getHospitalCode")
	public void getHospitalCode(HttpServletRequest request , HttpServletResponse response){
		String generateNumber = CommonUtils.generateNumber("96");//生成编码
		try {
			response.getWriter().write(generateNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 下载模板
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "generateTemplate")
	public void generateTemplate(HttpServletRequest request , HttpServletResponse response){
		String [] head = {"医院名称","省","市","区/县","详细地址","联系人","联系电话","联系人邮箱"};
		ExportExcel exportExcel = new ExportExcel("医院列表",head);
		try {
			exportExcel.write(response, "医院列表.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * AJAX响应方法：
	 * 导入数据
	 * */
	@RequestMapping(value = "importData",method=RequestMethod.POST)
	public String importData(@RequestParam MultipartFile[] file,RedirectAttributes redirectAttributes, MultipartHttpServletRequest request , HttpServletResponse response){

	//	MultipartFile multipartFile = request.getFile("file");
		
		Map<String, MultipartFile> fileMap = request.getFileMap();
		MultipartFile multipartFile = fileMap.get("file");
		
		String originalFilename = multipartFile.getOriginalFilename();
		String fullUrl = "";
		try {
			fullUrl = HysFileUploadUtils.uploadAndReturnFullUrl(multipartFile, "excel");
			
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(StringUtils.isBlank(originalFilename) || !originalFilename.endsWith(".xls") && !originalFilename.endsWith(".xlsx")){
			
		}else{
			try {
				ImportExcel ei = new ImportExcel(fullUrl, 1, 0);
				//List<PdProduct> list = ei.getDataList(PdProduct.class);
				List<PdHospital> dataList = ei.getDataList(PdHospital.class);
				if(dataList!=null && dataList.size()>0){
					//查询所有医院
					List<PdHospital> pdHospitals = pdHospitalService.findList(new PdHospital());
					boolean bl = true;
					for(int i=0;i<dataList.size();i++){
						PdHospital pdHospital = dataList.get(i);
						//校验名称是否唯一
						if(checkName(pdHospitals,pdHospital)){
							pdHospitals.add(pdHospital);
						}else{
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行医院名称不能重复");
							bl = false;
							break;
						}
						//校验省
						String provinceBl = checkProvinces(pdHospital);
						if("".equals(provinceBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行省份填写不正确");
							bl = false;
							break;
						}else{
							pdHospital.setAreaProvince(provinceBl);
						}
						//校验市
						String cityBl = checkCity(pdHospital);
						if("".equals(cityBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行市填写不正确");
							bl = false;
							break;
						}else{
							pdHospital.setAreaCity(cityBl);
						}
						//校验县
						String townBl = checkTown(pdHospital);
						if("".equals(townBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行区/县填写不正确");
							bl = false;
							break;
						}else{
							pdHospital.setAreaTown(townBl);
						}
						//校验联系人
						if(StringUtils.isEmpty(pdHospital.getPerson())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"联系人不能为空");
							bl = false;
							break;
						}
						//校验手机号格式是否正确
						if(!CheckUtils.isPhone(pdHospital.getTel())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行手机号格式不正确");
							bl = false;
							break;
						}
						//校验邮箱格式是否正确
						if(!CheckUtils.isEmail(pdHospital.getMail())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行邮箱格式不正确");
							bl = false;
							break;
						}
						//保存
						pdHospital.setNumber(CommonUtils.generateNumber("96"));
					}
					if(bl){
						addMessage(redirectAttributes, "批量导入成功");
						pdHospitalService.saveBatchHospital(dataList);
					}
				}else{
					addMessage(redirectAttributes, "批量导入失败,没有数据");
				}
			} catch (Exception e) {
				addMessage(redirectAttributes, "批量导入失败");
				e.printStackTrace();
			}
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdHospital/?repage";
	}
	
	/**
	 * 校验医院名称是否唯一
	 * @param hospital
	 * @return
	 */
	private boolean checkName(List<PdHospital> pdHospitals, PdHospital pdHospital) {
		for (PdHospital hospital : pdHospitals) {
			if(hospital.getName().equals(pdHospital.getName())){
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验医院名称是否唯一
	 * @param hospital
	 * @return
	 */
	public boolean checkName(PdHospital hospital){
		List<PdHospital>  hospitals = pdHospitalService.verify(hospital);
		if(hospitals!=null && hospitals.size()>0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 校验省份是否存在
	 * @param hospital
	 * @return
	 */
	public String checkProvinces(PdHospital hospital){
		Area SysArea = new Area ();
		SysArea.setName(hospital.getAreaProvince());
		SysArea.setParent(new Area("1"));
		SysArea = areaService.checkProvinces(SysArea);
		if(SysArea!=null){
			return SysArea.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * 校验市是否存在
	 * @param pdHospital
	 * @return
	 */
	public String checkCity(PdHospital pdHospital) {
		Area SysArea = new Area ();
		SysArea.setName(pdHospital.getAreaCity());
		SysArea.setParent(new Area(pdHospital.getAreaProvince()));
		SysArea = areaService.checkProvinces(SysArea);
		if(SysArea!=null){
			return SysArea.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * 校验区/县是否存在
	 * @param pdHospital
	 * @return
	 */
	public String checkTown(PdHospital pdHospital) {
		Area SysArea = new Area ();
		SysArea.setName(pdHospital.getAreaTown());
		SysArea.setParent(new Area(pdHospital.getAreaCity()));
		SysArea = areaService.checkProvinces(SysArea);
		if(SysArea!=null){
			return SysArea.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}
	
}