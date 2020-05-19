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
import com.thinkgem.jeesite.hys.entity.MedstoYprkmx;
import com.thinkgem.jeesite.hys.service.MedstoYprkmxService;

/**
 * 药库入库明细Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/medstoYprkmx")
public class MedstoYprkmxController extends BaseController {

	@Autowired
	private MedstoYprkmxService medstoYprkmxService;
	
	@ModelAttribute
	public MedstoYprkmx get(@RequestParam(required=false) String id) {
		MedstoYprkmx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYprkmxService.get(id);
		}
		if (entity == null){
			entity = new MedstoYprkmx();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:medstoYprkmx:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYprkmx medstoYprkmx, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYprkmx> page = medstoYprkmxService.findPage(new Page<MedstoYprkmx>(request, response), medstoYprkmx); 
		model.addAttribute("page", page);
		return "jeesite/hys/medstoYprkmxList";
	}

	@RequiresPermissions("hys:medstoYprkmx:view")
	@RequestMapping(value = "form")
	public String form(MedstoYprkmx medstoYprkmx, Model model) {
		model.addAttribute("medstoYprkmx", medstoYprkmx);
		return "jeesite/hys/medstoYprkmxForm";
	}

	@RequiresPermissions("hys:medstoYprkmx:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYprkmx medstoYprkmx, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYprkmx)){
			return form(medstoYprkmx, model);
		}
		medstoYprkmxService.save(medstoYprkmx);
		addMessage(redirectAttributes, "保存药库入库明细成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYprkmx/?repage";
	}
	
	@RequiresPermissions("hys:medstoYprkmx:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYprkmx medstoYprkmx, RedirectAttributes redirectAttributes) {
		medstoYprkmxService.delete(medstoYprkmx);
		addMessage(redirectAttributes, "删除药库入库明细成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYprkmx/?repage";
	}

}