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
import com.thinkgem.jeesite.hys.entity.MedstoYprkzd;
import com.thinkgem.jeesite.hys.service.MedstoYprkzdService;

/**
 * 药库入库单Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/medstoYprkzd")
public class MedstoYprkzdController extends BaseController {

	@Autowired
	private MedstoYprkzdService medstoYprkzdService;
	
	@ModelAttribute
	public MedstoYprkzd get(@RequestParam(required=false) String id) {
		MedstoYprkzd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYprkzdService.get(id);
		}
		if (entity == null){
			entity = new MedstoYprkzd();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:medstoYprkzd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYprkzd medstoYprkzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYprkzd> page = medstoYprkzdService.findPage(new Page<MedstoYprkzd>(request, response), medstoYprkzd); 
		model.addAttribute("page", page);
		return "jeesite/hys/medstoYprkzdList";
	}

	@RequiresPermissions("hys:medstoYprkzd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYprkzd medstoYprkzd, Model model) {
		model.addAttribute("medstoYprkzd", medstoYprkzd);
		return "jeesite/hys/medstoYprkzdForm";
	}

	@RequiresPermissions("hys:medstoYprkzd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYprkzd medstoYprkzd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYprkzd)){
			return form(medstoYprkzd, model);
		}
		medstoYprkzdService.save(medstoYprkzd);
		addMessage(redirectAttributes, "保存药库入库单成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYprkzd/?repage";
	}
	
	@RequiresPermissions("hys:medstoYprkzd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYprkzd medstoYprkzd, RedirectAttributes redirectAttributes) {
		medstoYprkzdService.delete(medstoYprkzd);
		addMessage(redirectAttributes, "删除药库入库单成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYprkzd/?repage";
	}

}