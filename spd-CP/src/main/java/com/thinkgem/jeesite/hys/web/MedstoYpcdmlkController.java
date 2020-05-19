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
import com.thinkgem.jeesite.hys.entity.MedstoYpcdmlk;
import com.thinkgem.jeesite.hys.service.MedstoYpcdmlkService;

/**
 * 药品厂家库Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/spdcp/medstoYpcdmlk")
public class MedstoYpcdmlkController extends BaseController {

	@Autowired
	private MedstoYpcdmlkService medstoYpcdmlkService;
	
	@ModelAttribute
	public MedstoYpcdmlk get(@RequestParam(required=false) String id) {
		MedstoYpcdmlk entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpcdmlkService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpcdmlk();
		}
		return entity;
	}
	
	@RequiresPermissions("spdcp:medstoYpcdmlk:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpcdmlk medstoYpcdmlk, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpcdmlk> page = medstoYpcdmlkService.findPage(new Page<MedstoYpcdmlk>(request, response), medstoYpcdmlk); 
		model.addAttribute("page", page);
		return "hys/pd/pdDrugManageList";
	}

	@RequiresPermissions("spdcp:medstoYpcdmlk:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpcdmlk medstoYpcdmlk, Model model) {
		model.addAttribute("medstoYpcdmlk", medstoYpcdmlk);
		return "hys/pd/pdDrugManageForm";
	}
	//查看和修改
	@RequiresPermissions("spdcp:medstoYpcdmlk:view")
	@RequestMapping(value = "operation")
	public String operation(MedstoYpcdmlk medstoYpcdmlk, String oprt, Model model) {
		model.addAttribute("medstoYpcdmlk", medstoYpcdmlk);
		model.addAttribute("oprt", oprt);
		return "hys/pd/pdDrugManageForm";
	}

	@RequiresPermissions("spdcp:medstoYpcdmlk:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpcdmlk medstoYpcdmlk, Model model, RedirectAttributes redirectAttributes) {
		medstoYpcdmlkService.save(medstoYpcdmlk);
		addMessage(redirectAttributes, "保存药品厂家库成功");
		return "redirect:"+Global.getAdminPath()+"/spdcp/medstoYpcdmlk/?repage";
	}
	
	@RequiresPermissions("spdcp:medstoYpcdmlk:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpcdmlk medstoYpcdmlk, RedirectAttributes redirectAttributes) {
		medstoYpcdmlkService.delete(medstoYpcdmlk);
		addMessage(redirectAttributes, "删除药品厂家库成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYpcdmlk/?repage";
	}

}