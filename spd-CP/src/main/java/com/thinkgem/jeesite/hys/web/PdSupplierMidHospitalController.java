/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.hys.entity.PdSupplierMidHospital;
import com.thinkgem.jeesite.hys.service.PdSupplierMidHospitalService;

/**
 * 供应商医院关联表Controller
 * @author sutianqi
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdSupplierMidHospital")
public class PdSupplierMidHospitalController extends BaseController {

	@Autowired
	private PdSupplierMidHospitalService pdSupplierMidHospitalService;
	
	@ModelAttribute
	public PdSupplierMidHospital get(@RequestParam(required=false) String id) {
		PdSupplierMidHospital entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdSupplierMidHospitalService.get(id);
		}
		if (entity == null){
			entity = new PdSupplierMidHospital();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:pdSupplierMidHospital:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdSupplierMidHospital pdSupplierMidHospital, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdSupplierMidHospital> page = pdSupplierMidHospitalService.findPage(new Page<PdSupplierMidHospital>(request, response), pdSupplierMidHospital); 
		model.addAttribute("page", page);
		return "jeesite/hys/pdSupplierMidHospitalList";
	}

	@RequiresPermissions("hys:pdSupplierMidHospital:view")
	@RequestMapping(value = "form")
	public String form(PdSupplierMidHospital pdSupplierMidHospital, Model model) {
		model.addAttribute("pdSupplierMidHospital", pdSupplierMidHospital);
		return "jeesite/hys/pdSupplierMidHospitalForm";
	}

	@RequiresPermissions("hys:pdSupplierMidHospital:edit")
	@RequestMapping(value = "save")
	public String save(PdSupplierMidHospital pdSupplierMidHospital, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdSupplierMidHospital)){
			return form(pdSupplierMidHospital, model);
		}
		pdSupplierMidHospitalService.save(pdSupplierMidHospital);
		addMessage(redirectAttributes, "保存保存中间表成功成功");
		return "redirect:"+Global.getAdminPath()+"/hys/pdSupplierMidHospital/?repage";
	}
	
	@RequiresPermissions("hys:pdSupplierMidHospital:edit")
	@RequestMapping(value = "delete")
	public String delete(PdSupplierMidHospital pdSupplierMidHospital, RedirectAttributes redirectAttributes) {
		pdSupplierMidHospitalService.delete(pdSupplierMidHospital);
		addMessage(redirectAttributes, "删除保存中间表成功成功");
		return "redirect:"+Global.getAdminPath()+"/hys/pdSupplierMidHospital/?repage";
	}

}