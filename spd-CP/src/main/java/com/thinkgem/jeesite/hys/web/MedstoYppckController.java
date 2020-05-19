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
import com.thinkgem.jeesite.hys.entity.MedstoYppck;
import com.thinkgem.jeesite.hys.service.MedstoYppckService;

/**
 * 药库批次库Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/medstoYppck")
public class MedstoYppckController extends BaseController {

	@Autowired
	private MedstoYppckService medstoYppckService;
	
	@ModelAttribute
	public MedstoYppck get(@RequestParam(required=false) String id) {
		MedstoYppck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYppckService.get(id);
		}
		if (entity == null){
			entity = new MedstoYppck();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:medstoYppck:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYppck medstoYppck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYppck> page = medstoYppckService.findPage(new Page<MedstoYppck>(request, response), medstoYppck); 
		model.addAttribute("page", page);
		return "jeesite/hys/medstoYppckList";
	}

	@RequiresPermissions("hys:medstoYppck:view")
	@RequestMapping(value = "form")
	public String form(MedstoYppck medstoYppck, Model model) {
		model.addAttribute("medstoYppck", medstoYppck);
		return "jeesite/hys/medstoYppckForm";
	}

	@RequiresPermissions("hys:medstoYppck:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYppck medstoYppck, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYppck)){
			return form(medstoYppck, model);
		}
		medstoYppckService.save(medstoYppck);
		addMessage(redirectAttributes, "保存药库批次库成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYppck/?repage";
	}
	
	@RequiresPermissions("hys:medstoYppck:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYppck medstoYppck, RedirectAttributes redirectAttributes) {
		medstoYppckService.delete(medstoYppck);
		addMessage(redirectAttributes, "删除药库批次库成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYppck/?repage";
	}

}