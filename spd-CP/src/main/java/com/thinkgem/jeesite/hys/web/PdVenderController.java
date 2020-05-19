/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.HysFileUploadUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.UploadUtil;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.hys.entity.PdVender;
import com.thinkgem.jeesite.hys.service.PdVenderService;
import com.thinkgem.jeesite.modules.sys.utils.SnoGerUtil;

/**
 * 生产厂家表Controller
 * @author sutianqi
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdVender")
public class PdVenderController extends BaseController {

	@Autowired
	private PdVenderService pdVenderService;
	
	@ModelAttribute
	public PdVender get(@RequestParam(required=false) String id) {
		PdVender entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdVenderService.get(id);
		}
		if (entity == null){
			entity = new PdVender();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:pdVender:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdVender pdVender, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdVender> page = pdVenderService.findPage(new Page<PdVender>(request, response), pdVender); 
		model.addAttribute("page", page);
		return "hys/pd/pdVenderList";
	}

	@RequiresPermissions("hys:pdVender:view")
	@RequestMapping(value = "form")
	public String form(PdVender pdVender, Model model, HttpServletRequest request) {
		String flag = request.getParameter("flag");
		pdVender = pdVenderService.get(pdVender);
		model.addAttribute("pdVender", pdVender);
		request.setAttribute("flag", flag);
		return "hys/pd/pdVenderForm";
	}

	@RequiresPermissions("hys:pdVender:edit")
	@RequestMapping(value = "save")
	public String save(PdVender pdVender, @RequestParam MultipartFile[] licenceSite1Up,
			@RequestParam MultipartFile[] licenceSite2Up,@RequestParam MultipartFile[] licenceSite3Up,
			@RequestParam MultipartFile[] licenceSite4Up,@RequestParam MultipartFile[] licenceSite5Up,
			@RequestParam MultipartFile[] licenceSite6Up,@RequestParam MultipartFile[] licenceSite7Up,
			@RequestParam MultipartFile[] licenceSite8Up,@RequestParam MultipartFile[] licenceSite9Up,
			@RequestParam MultipartFile[] licenceSite10Up,@RequestParam MultipartFile[] licenceSite11Up,
			@RequestParam MultipartFile[] licenceSite12Up,Model model, RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {
		List<PdVender> pdvenders = pdVenderService.verify(pdVender);
		if(pdvenders!=null && pdvenders.size()>0){
			addMessage(redirectAttributes, "保存生产厂家失败生产厂家已存在");
		}else{
			//存入图片
			if(!isImgEmpty(licenceSite1Up)){
				pdVender.setImgLicense1Site(saveFile(licenceSite1Up));
			}
			if(!isImgEmpty(licenceSite2Up)){
				pdVender.setImgLicense2Site(saveFile(licenceSite2Up));
			}
			if(!isImgEmpty(licenceSite3Up)){
				pdVender.setImgLicense3Site(saveFile(licenceSite3Up));
			}
			if(!isImgEmpty(licenceSite4Up)){
				pdVender.setImgLicense4Site(saveFile(licenceSite4Up));
			}
			if(!isImgEmpty(licenceSite5Up)){
				pdVender.setImgLicense5Site(saveFile(licenceSite5Up));
			}
			if(!isImgEmpty(licenceSite6Up)){
				pdVender.setImgLicense6Site(saveFile(licenceSite6Up));
			}
			if(!isImgEmpty(licenceSite7Up)){
				pdVender.setImgLicense7Site(saveFile(licenceSite7Up));
			}
			if(!isImgEmpty(licenceSite8Up)){
				pdVender.setImgLicense8Site(saveFile(licenceSite8Up));
			}
			if(!isImgEmpty(licenceSite9Up)){
				pdVender.setImgLicense9Site(saveFile(licenceSite9Up));
			}
			if(!isImgEmpty(licenceSite10Up)){
				pdVender.setImgLicense10Site(saveFile(licenceSite10Up));
			}
			if(!isImgEmpty(licenceSite11Up)){
				pdVender.setImgLicense11Site(saveFile(licenceSite11Up));
			}
			if(!isImgEmpty(licenceSite12Up)){
				pdVender.setImgLicense12Site(saveFile(licenceSite12Up));
			}
			pdVenderService.save(pdVender);
			addMessage(redirectAttributes, "保存生产厂家成功");
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdVender/?repage";
	}
	
	@RequiresPermissions("hys:pdVender:edit")
	@RequestMapping(value = "delete")
	public String delete(PdVender pdVender, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String id = request.getParameter("id");
		String checkCorrelation = pdVenderService.checkCorrelation(id);	//检查是否与产品关联
		if(checkCorrelation.equals("fail")){
			addMessage(redirectAttributes, "该生产厂家有关联产品，无法删除");
		}else{
			addMessage(redirectAttributes, "删除生产厂家成功");
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdVender/?repage";
	}
	
	@RequestMapping(value = "deleteAll")
	public String deleteAll(PdVender pdVender, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String[] id = ids.substring(1).split(",");
		
		List<String> checkCorrelation = pdVenderService.checkCorrelation(id);	//检查是否与产品关联
		boolean bo = true;
		String s = "";
		if(checkCorrelation.get(0).equals("all-successful")){
			
		}else{
			bo = false;
			for(String t : checkCorrelation){
				s += t+",";
			}
		}
		if(bo){
			addMessage(redirectAttributes, "删除生产厂家成功");
		}else{
			addMessage(redirectAttributes, s+"等生产厂家有关联产品，无法删除");
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdVender/?";
	}
	
	
	/**
	 * 判断图片非空
	 * @param imgs
	 * @return
	 */
	private boolean isImgEmpty(MultipartFile[] imgs){
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			if(img.isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	// 保存多个图片
	private String saveFile(MultipartFile[] imgs) {
		StringBuilder urls = new StringBuilder("");
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			String extName = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
			Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
			String serialNumber = SnoGerUtil.getSerialNumber(5);
			String fullName = longTime + serialNumber + extName;
			String fullPath = Global.getUserfilesBaseDir()+UploadUtil.IMAGE_DIR +"spd/"+ fullName;
			String  url = UploadUtil.IMAGE_DIR+"spd/"+ fullName;//文件上传的url
			try {
				createDir(fullPath);
				img.transferTo(new File(fullPath));
				if(0==i){
					urls.append(url);
				}else{
					urls.append("&&&"+url);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return  null;
			} catch (IOException e) {
				e.printStackTrace();
				return  null;
			}
		}
		return urls.toString();
	}
	
	/**
	 * @功能描述:创建文件夹
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}
	
	/**
	 * AJAX响应方法：
	 * 生成模板
	 * */
	@RequestMapping(value = "generateTemplate")
	public void generateTemplate(HttpServletRequest request , HttpServletResponse response){
		
		String [] head = {"生产厂家名称"};
		
		ExportExcel exportExcel = new ExportExcel("生产厂家列表",head);
		
		
		try {
			exportExcel.write(response, "生产厂家列表.xlsx");
		} catch (IOException e) {
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
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(StringUtils.isBlank(originalFilename) || !originalFilename.endsWith(".xls") && !originalFilename.endsWith(".xlsx")){
			
		}else{
			try {
				ImportExcel ei = new ImportExcel(fullUrl, 1, 0);
				List<PdVender> dataList = ei.getDataList(PdVender.class);
				if(dataList!=null && dataList.size()>0){
					//查询数据库中所有的生产厂家
					List<PdVender> pdVenders = pdVenderService.findList(new PdVender());
					//校验excel中的所有内容
					boolean bl = true;
					for (int i = 0;i<dataList.size();i++) {
						//校验名称是否唯一
						PdVender pdVender = dataList.get(i);
						if(StringUtils.EMPTY.equals(pdVender.getName())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行生产厂家名称不能为空");
							bl = false;
							break;
						}
						if(checkName(pdVenders,pdVender)){
							pdVenders.add(pdVender);
						}else{
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行生产厂家名称不能重复");
							bl = false;
							break;
						}
					}
					if(bl){
						if(pdVenderService.saveBatchPdVender(dataList)){
							addMessage(redirectAttributes, "批量导入成功");	
						};
					}
				}else{
					addMessage(redirectAttributes, "批量导入失败,没有数据");
				}
			} catch (Exception e) {
				addMessage(redirectAttributes, "批量导入失败");
				e.printStackTrace();
			}
			
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdVender/?repage";
	}
	
	
	/**
	 * 校验名称是否重复
	 * @param pdVenders
	 * @param pdVender
	 * @return
	 */
	private boolean checkName(List<PdVender> pdVenders, PdVender pdVender) {
		for (PdVender p : pdVenders) {
			if(p.getName().equals(pdVender.getName())){
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验生产厂家名称
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("hys:pdSupplier:view")
	@RequestMapping(value = "checkVenderName")
	public String checkVenderName( Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		//查询供应商名称是否已经存在
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		try {
			PdVender pdVender = new PdVender();
			pdVender.setName(name);
			if(id!=null && !"".equals(id)){
				pdVender.setId(id);
			}
			List<PdVender>  venders = pdVenderService.verify(pdVender);
			if(venders!=null && venders.size()>0){
				return "false";
			}else{
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@ResponseBody
	@RequestMapping(value = "synDataFromSpd")
	public String synDataFromSpd(@RequestBody String param, HttpServletRequest request, HttpServletResponse response) {
		return pdVenderService.synDataFromSpd(param);
	}

	@ResponseBody
	@RequestMapping(value = "synFileFromSpd")
	public String synFileFromSpd(@RequestParam(value = "imageFile", required = false) MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) {
		return pdVenderService.synFileFromSpd(imageFile);
	}
}