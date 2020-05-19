/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.hys.entity.HisPurchaseOrderItem;
import com.thinkgem.jeesite.hys.service.HisPurchaseOrderItemService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * his采购订单详情Controller
 * @author jiangxz
 * @version 2019-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/hisPurchaseOrderItem")
public class HisPurchaseOrderItemController extends BaseController {

	@Autowired
	private HisPurchaseOrderItemService hisPurchaseOrderItemService;
	
	@ModelAttribute
	public HisPurchaseOrderItem get(@RequestParam(required=false) String id) {
		HisPurchaseOrderItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hisPurchaseOrderItemService.get(id);
		}
		if (entity == null){
			entity = new HisPurchaseOrderItem();
		}
		return entity;
	}
	
//	@RequiresPermissions("pd:hisPurchaseOrderItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(HisPurchaseOrderItem hisPurchaseOrderItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		//如果是供应商角色
		User user = UserUtils.getUser();
		if(user.getSupplierId()!=null){
			hisPurchaseOrderItem.setProducerName(user.getSupplierName());
		}
		Page<HisPurchaseOrderItem> page = hisPurchaseOrderItemService.findPage(new Page<HisPurchaseOrderItem>(request, response), hisPurchaseOrderItem);
		model.addAttribute("page", page);
		model.addAttribute("hisPurchaseOrderItem", hisPurchaseOrderItem);
		return "hys/pd/box/hisPurchaseOrderItemBox";
	}

//	@RequiresPermissions("pd:hisPurchaseOrderItem:view")
//	@RequestMapping(value = "form")
//	public String form(HisPurchaseOrderItem hisPurchaseOrderItem, Model model) {
//		model.addAttribute("hisPurchaseOrderItem", hisPurchaseOrderItem);
//		return "hys/pd/hisPurchaseOrderItemForm";
//	}
//
//	@RequiresPermissions("pd:hisPurchaseOrderItem:edit")
//	@RequestMapping(value = "save")
//	public String save(HisPurchaseOrderItem hisPurchaseOrderItem, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, hisPurchaseOrderItem)){
//			return form(hisPurchaseOrderItem, model);
//		}
//		hisPurchaseOrderItemService.save(hisPurchaseOrderItem);
//		addMessage(redirectAttributes, "保存his采购订单详情成功");
//		return "redirect:"+Global.getAdminPath()+"/pd/hisPurchaseOrderItem/?repage";
//	}
//
//	@RequiresPermissions("pd:hisPurchaseOrderItem:edit")
//	@RequestMapping(value = "delete")
//	public String delete(HisPurchaseOrderItem hisPurchaseOrderItem, RedirectAttributes redirectAttributes) {
//		hisPurchaseOrderItemService.delete(hisPurchaseOrderItem);
//		addMessage(redirectAttributes, "删除his采购订单详情成功");
//		return "redirect:"+Global.getAdminPath()+"/pd/hisPurchaseOrderItem/?repage";
//	}

}