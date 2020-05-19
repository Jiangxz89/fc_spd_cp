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
import com.thinkgem.jeesite.hys.entity.MedstoYpcgmx;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgzd;
import com.thinkgem.jeesite.hys.service.MedstoYpcgmxService;
import com.thinkgem.jeesite.hys.service.MedstoYpcgzdService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 药库采购明细Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/medstoYpcgmx")
public class MedstoYpcgmxController extends BaseController {

	@Autowired
	private MedstoYpcgmxService medstoYpcgmxService;
	@Autowired
	private MedstoYpcgzdService medstoYpcgzdService;
	
	@ModelAttribute
	public MedstoYpcgmx get(@RequestParam(required=false) String id) {
		MedstoYpcgmx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpcgmxService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpcgmx();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:medstoYpcgmx:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpcgmx medstoYpcgmx, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpcgmx> page = medstoYpcgmxService.findPage(new Page<MedstoYpcgmx>(request, response), medstoYpcgmx); 
		model.addAttribute("page", page);
		return "jeesite/hys/medstoYpcgmxList";
	}

	@RequiresPermissions("hys:medstoYpcgmx:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpcgmx medstoYpcgmx,String oprt, Model model) {
		User user = UserUtils.getUser();
		String supplierId = user.getSupplierId();
		//供应商用户
		if (!StringUtils.isEmpty(supplierId)) {
			medstoYpcgmx.setGhdwMc(user.getSupplierName());
		}
		model.addAttribute("detailList", medstoYpcgmxService.findList(medstoYpcgmx));
		MedstoYpcgzd zd = medstoYpcgzdService.get(medstoYpcgmx.getCgxh());
		model.addAttribute("medstoYpcgzd", zd);
		model.addAttribute("oprt", oprt);
		return "hys/pd/pdDrugOrderForm";
	}

	@RequiresPermissions("hys:medstoYpcgmx:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpcgmx medstoYpcgmx, Model model, RedirectAttributes redirectAttributes) {
		medstoYpcgmxService.save(medstoYpcgmx);
		addMessage(redirectAttributes, "保存药库采购明细成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYpcgmx/?repage";
	}
	
	@RequiresPermissions("hys:medstoYpcgmx:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpcgmx medstoYpcgmx, RedirectAttributes redirectAttributes) {
		medstoYpcgmxService.delete(medstoYpcgmx);
		addMessage(redirectAttributes, "删除药库采购明细成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYpcgmx/?repage";
	}

}