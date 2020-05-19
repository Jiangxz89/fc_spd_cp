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
import com.thinkgem.jeesite.hys.entity.MedstoYpthzd;
import com.thinkgem.jeesite.hys.service.MedstoYpthzdService;

/**
 * 药品退货账单Controller
 * @author wg
 * @version 2018-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/medstoYpthzd")
public class MedstoYpthzdController extends BaseController {

	@Autowired
	private MedstoYpthzdService medstoYpthzdService;
	
	@ModelAttribute
	public MedstoYpthzd get(@RequestParam(required=false) String id) {
		MedstoYpthzd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpthzdService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpthzd();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:medstoYpthzd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpthzd medstoYpthzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpthzd> page = medstoYpthzdService.findPage(new Page<MedstoYpthzd>(request, response), medstoYpthzd); 
		model.addAttribute("page", page);
		return "jeesite/hys/medstoYpthzdList";
	}

	@RequiresPermissions("hys:medstoYpthzd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpthzd medstoYpthzd, Model model) {
		model.addAttribute("medstoYpthzd", medstoYpthzd);
		return "jeesite/hys/medstoYpthzdForm";
	}

	@RequiresPermissions("hys:medstoYpthzd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpthzd medstoYpthzd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpthzd)){
			return form(medstoYpthzd, model);
		}
		medstoYpthzdService.save(medstoYpthzd);
		addMessage(redirectAttributes, "保存药品退货账单成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYpthzd/?repage";
	}
	
	@RequiresPermissions("hys:medstoYpthzd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpthzd medstoYpthzd, RedirectAttributes redirectAttributes) {
		medstoYpthzdService.delete(medstoYpthzd);
		addMessage(redirectAttributes, "删除药品退货账单成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYpthzd/?repage";
	}

	
	/********************************************************************************************
	 * 
	 **************************************统计查询（药品退货）*************************************
	 * 
	 *******************************************************************************************/
	@RequestMapping(value = "drugRefundStatistic")
	public String drugRefundStatistic(MedstoYpthzd medstoYpthzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpthzd> page = medstoYpthzdService.findPage(new Page<MedstoYpthzd>(request, response), medstoYpthzd); 
		model.addAttribute("page", page);
		return "hys/pd/pdDrugRefundStatisticList";
	}
}