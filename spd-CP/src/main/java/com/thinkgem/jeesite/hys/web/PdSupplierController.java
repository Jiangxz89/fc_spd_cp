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
import com.thinkgem.jeesite.hys.entity.PdProduct;
import com.thinkgem.jeesite.hys.entity.PdSupplier;
import com.thinkgem.jeesite.hys.service.PdProductService;
import com.thinkgem.jeesite.hys.service.PdSupplierService;
import com.thinkgem.jeesite.hys.utils.SpdUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 供应商表Controller
 * @author sutianqi
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdSupplier")
public class PdSupplierController extends BaseController {

	@Autowired
	private PdSupplierService pdSupplierService;
	@Autowired
	private PdProductService pdProductService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public PdSupplier get(@RequestParam(required=false) String id) {
		PdSupplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdSupplierService.get(id);
		}
		if (entity == null){
			entity = new PdSupplier();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:pdSupplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdSupplier pdSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdSupplier> page = pdSupplierService.findPage(new Page<PdSupplier>(request, response), pdSupplier); 
		model.addAttribute("page", page);
		return "hys/pd/pdSupplierList";
	}

	@RequiresPermissions("hys:pdSupplier:view")
	@RequestMapping(value = "form") 
	public String form(PdSupplier pdSupplier, Model model, HttpServletRequest request) {
		String flag = request.getParameter("flag");
		model.addAttribute("flag", flag);
		model.addAttribute("pdSupplier", pdSupplier);
		return "hys/pd/pdSupplierForm";
	}
	
	//跳转到修改页面
	@RequestMapping(value = "toUpdate")
	public String toUpdate(PdSupplier pdSupplier, Model model, HttpServletRequest request) {
		model.addAttribute("pdSupplier", pdSupplier);
		String flag = request.getParameter("flag");
		request.setAttribute("flag",flag);
		pdSupplier = pdSupplierService.get(pdSupplier);
		model.addAttribute("pdSupplier", pdSupplier);
		return "hys/pd/pdSupplierForm";
	}

	@RequiresPermissions("hys:pdSupplier:edit")
	@RequestMapping(value = "save")
	public String save(PdSupplier pdSupplier, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pdSupplier)){
			return form(pdSupplier, model, request);
		}
		
		//查询供应商名称是否已经存在
		List<PdSupplier>  suppliers = pdSupplierService.verify(pdSupplier);
		if(suppliers!=null && suppliers.size()>0){
			addMessage(redirectAttributes, "保存供应商失败,供应商已存在");
		}else{
			//查询供应商账户是否已经存在
			User u = new User();
			u.setLoginName(pdSupplier.getLoginName());
			List<User> users = systemService.verify(u);
			if(users!=null && users.size()>0){
				//账号已存在
				addMessage(redirectAttributes, "保存供应商失败,登录账号已存在");
			}else{
				pdSupplierService.save(pdSupplier);
				//更新供应商缓存
				SpdUtils.updateSupploerInfo();
				addMessage(redirectAttributes, "保存供应商成功");
			}
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdSupplier/?repage";
	}
	
	@RequiresPermissions("hys:pdSupplier:edit")
	@RequestMapping(value = "update")
	public String update(PdSupplier pdSupplier, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pdSupplier)){
			return form(pdSupplier, model, request);
		}
		//查询供应商名称是否已经存在
		List<PdSupplier>  suppliers = pdSupplierService.verify(pdSupplier);
		if(suppliers!=null && suppliers.size()>0){
			addMessage(redirectAttributes, "更新供应商失败,供应商已存在");
		}else{
			//查询老账号是否和新账号一致
			PdSupplier ps = pdSupplierService.get(pdSupplier);
			pdSupplier.setOldLoginName(ps.getAccount());
			if(ps.getAccount().equals(pdSupplier.getLoginName())){
				pdSupplierService.update(pdSupplier);
			}else{
				User u = new User();
				u.setLoginName(pdSupplier.getLoginName());
				List<User> users = systemService.verify(u);
				if(users!=null && users.size()>0){
					//账号已存在
					addMessage(redirectAttributes, "更新供应商失败,登录账号已存在");
				}else{
					pdSupplierService.update(pdSupplier);
				}
			}
		}
		//更新供应商缓存
		SpdUtils.updateSupploerInfo();
		addMessage(redirectAttributes, "保存供应商成功");
		return "redirect:"+Global.getAdminPath()+"/hys/pdSupplier/?repage";
	}
	
	@RequiresPermissions("hys:pdSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(PdSupplier pdSupplier, RedirectAttributes redirectAttributes) {
		
		PdProduct prod = new PdProduct();
		prod.setSupplierId(pdSupplier.getId());
		List<PdProduct> findList = pdProductService.findList(prod);
		if(findList.size() > 0){
			addMessage(redirectAttributes, "删除供应商失败，有关联耗材或药品");
		}else{
			//删除账号
			pdSupplierService.delete(pdSupplier);
			addMessage(redirectAttributes, "删除供应商成功");
		}
		//更新供应商缓存
		SpdUtils.updateSupploerInfo();
		return "redirect:"+Global.getAdminPath()+"/hys/pdSupplier/?repage";
	}
	
	/**
	 * 校验供应商是否存在
	 * @param pdSupplier
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("hys:pdSupplier:view")
	@RequestMapping(value = "checkSupplierName")
	public String checkSupplierName( Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		//查询供应商名称是否已经存在
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		try {
			PdSupplier pdSupplier = new PdSupplier();
			pdSupplier.setName(name);
			if(id!=null && !"".equals(id)){
				pdSupplier.setId(id);
			}
			List<PdSupplier>  suppliers = pdSupplierService.verify(pdSupplier);
			if(suppliers!=null && suppliers.size()>0){
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
	 * 下载模板
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "generateTemplate")
	public void generateTemplate(HttpServletRequest request , HttpServletResponse response){
		String [] head = {"供应商名称","联系人","联系电话","联系人邮箱","联系人地址","登录账号"};
		ExportExcel exportExcel = new ExportExcel("供应商列表",head);
		try {
			exportExcel.write(response, "供应商列表.xlsx");
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
				List<PdSupplier> dataList = ei.getDataList(PdSupplier.class);
				if(dataList!=null && dataList.size()>0){
					//查询数据库中所有供应商
					List<PdSupplier> pdSuppliers = pdSupplierService.findList(new PdSupplier());
					//查询数据库中的所有用户
					List<User> users = systemService.findUser(new User());
					//校验excel中的所有内容
					boolean bl = true;
					for (int i = 0;i<dataList.size();i++) {
						PdSupplier pdSupplier = dataList.get(i);
						//校验名称是否为空
						if(StringUtils.isEmpty(pdSupplier.getName())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行供应商名称不能为空");
							bl = false;
							break;
						}
						//校验名称是否唯一
						if(checkName(pdSuppliers,pdSupplier)){
							pdSuppliers.add(pdSupplier);
						}else{
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行供应商名称不能重复");
							bl = false;
							break;
						}
						//校验登录账号是否为空
						if(StringUtils.isEmpty(pdSupplier.getAccount())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行登录账号不能为空");
							bl = false;
							break;
						}
						//校验登录账号是否唯一
						if(checkLoginName(users,pdSupplier)){
							User user = new User();
							user.setLoginName(pdSupplier.getAccount());
							users.add(user);
						}else{
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行登录账号不能重复");
							bl = false;
							break;
						}
						//校验联系人
						if(StringUtils.isEmpty(pdSupplier.getPerson())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"联系人不能为空");
							bl = false;
							break;
						}
						//校验手机号格式是否正确
						if(!CheckUtils.isPhone(pdSupplier.getTel())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行手机号格式不正确");
							bl = false;
							break;
						}
						//校验邮箱格式是否正确
						if(!CheckUtils.isEmail(pdSupplier.getMail())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行邮箱格式不正确");
							bl = false;
							break;
						}
					}
					if(bl){
						if(pdSupplierService.saveBatchPdSupplier(dataList)){
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
		return "redirect:"+Global.getAdminPath()+"/hys/pdSupplier/?repage";
	}
	
	/**
	 * 校验供应商名称是否唯一
	 * @return
	 */
	public boolean checkName(PdSupplier pdSupplier){
		List<PdSupplier>  suppliers = pdSupplierService.verify(pdSupplier);
		if(suppliers!=null && suppliers.size()>0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 校验登录名是否唯一
	 * @param loginName
	 * @return
	 */
	public boolean checkLoginName(PdSupplier pdSupplier) {
		if (pdSupplier.getAccount() !=null && systemService.getUserByLoginName(pdSupplier.getAccount()) == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 校验名称是否重复
	 * @param pdSuppliers
	 * @param pdSupplier
	 * @return
	 */
	public boolean checkName(List<PdSupplier> pdSuppliers,PdSupplier pdSupplier){
		for (PdSupplier p : pdSuppliers) {
			if(p.getName().equals(pdSupplier.getName())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 校验登录名是否唯一
	 * @param loginName
	 * @return
	 */
	public boolean checkLoginName(List<User> users ,PdSupplier pdSupplier) {
		for (User user : users) {
			if(user.getLoginName().equals(pdSupplier.getAccount())){
				return false;
			}
		}
		return true;
	}
}