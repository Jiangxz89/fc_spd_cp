/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.hys.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrderTime;
import com.thinkgem.jeesite.hys.entity.PdGroup;
import com.thinkgem.jeesite.hys.entity.PdProduct;
import com.thinkgem.jeesite.hys.service.PdGroupService;
import com.thinkgem.jeesite.hys.service.PdProductService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 耗材组别表Controller
 * @author sutianqi
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdGroup")
public class PdGroupController extends BaseController {

	@Autowired
	private PdGroupService pdGroupService;
	@Autowired
	private PdProductService pdProductService;
	
	@ModelAttribute
	public PdGroup get(@RequestParam(required=false) String id) {
		PdGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdGroupService.get(id);
		}
		if (entity == null){
			entity = new PdGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:pdGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdGroup pdGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		String delKey = request.getParameter("delKey") == null ? "" : request.getParameter("delKey");
		String delAllKey = request.getParameter("idKey") == null ? "" : request.getParameter("idKey");
		Page<PdGroup> page = pdGroupService.findPage(new Page<PdGroup>(request, response), pdGroup); 
		
		if(!delAllKey.equals("")){
			String[] ids = delAllKey.split(",");
			String names = "";
			for(int i = 0 ; i < ids.length ; i ++){
				PdGroup gg = pdGroupService.get(ids[i]);
				names += ","+gg.getName();
			}
			String nameArr = names.substring(1);
			request.setAttribute("delAllKey", nameArr);
		}
		
		request.setAttribute("delKey", delKey);
		model.addAttribute("page", page);
		return "hys/pd/pdGroupList";
	}

	@RequiresPermissions("hys:pdGroup:view")
	@RequestMapping(value = "form")
	public String form(PdGroup pdGroup, Model model) {
		model.addAttribute("pdGroup", pdGroup);
		return "hys/pd/pdGroupForm";
	}

	@RequiresPermissions("hys:pdGroup:edit")
	@RequestMapping(value = "save")
	public String save(PdGroup pdGroup, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pdGroup)){
			return form(pdGroup, model);
		}
		
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		if(!name.equals("")){
			pdGroup.setName(name);
		}
		List<PdGroup>  pdGroups = pdGroupService.verify(pdGroup);
		if(pdGroups!=null && pdGroups.size()>0){
			addMessage(redirectAttributes, "保存组别失败,组别已存在");
		}else{
			pdGroupService.save(pdGroup);
			addMessage(redirectAttributes, "保存组别表成功");
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdGroup/?repage";
	}
	
	@RequiresPermissions("hys:pdGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(PdGroup pdGroup, RedirectAttributes redirectAttributes) {
		String id = pdGroup.getId();
		
		PdProduct prod = new PdProduct();
		prod.setGroupId(id);
		List<PdProduct> findList = pdProductService.checkCorrelation(prod);
		
		if(findList.size() != 0){
			return "redirect:"+Global.getAdminPath()+"/hys/pdGroup/?delKey=error"; 
		}else if(findList.size() == 0){
			pdGroupService.delete(pdGroup);
			addMessage(redirectAttributes, "删除组别表成功");
			return "redirect:"+Global.getAdminPath()+"/hys/pdGroup/?repage";
		}
		return null;
	}
	
	/**
	 * ajax的保存
	 * */
	@RequestMapping(value = "saveAjax")
	public void saveAjax(PdGroup pdGroup, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		if(!name.equals("")){
			pdGroup.setName(name);
		}
		List<PdGroup>  pdGroups = pdGroupService.verify(pdGroup);
		Map<String,Object> map = new HashMap<String,Object>();
		if(pdGroups!=null && pdGroups.size()>0){
			map.put("statusCode", "500");
			map.put("msg", "保存失败组别名称不能重复");
		}else{
			pdGroupService.save(pdGroup);
			map.put("statusCode", "200");
			map.put("id", pdGroup.getId());
			map.put("name", pdGroup.getName());
			map.put("msg", "保存成功");
		}
		try {
			response.getWriter().write(JSONObject.toJSONString(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "update")
	public String update(PdGroup pdGroup, RedirectAttributes redirectAttributes) {
		
		List<PdGroup>  pdGroups = pdGroupService.verify(pdGroup);
		if(pdGroups!=null && pdGroups.size()>0){
			addMessage(redirectAttributes, "保存组别失败,组别已存在");
		}else{
			pdGroupService.update(pdGroup);
			addMessage(redirectAttributes, "更新组别表成功");
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdGroup/?repage";
	}
	
	/**
	 * @param pdGroup
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteAll")
	public String deleteAll(PdGroup pdGroup, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String[] id = ids.substring(1).split(",");
		
		String delAllIds = "";
		
		boolean bo = true;
		PdProduct prod = new PdProduct();
		for(int i = 0 ; i < id.length ; i ++){
			pdGroup.setId(id[i]);
			
			prod.setGroupId(id[i]);
			List<PdProduct> findList = pdProductService.checkCorrelation(prod);
			if(findList.size() == 0){
				pdGroupService.delete(pdGroup);
			}else{
				bo = false;
				delAllIds += id[i]+",";
			}
		}
		if(bo){
			addMessage(redirectAttributes, "删除组别成功");
			return "redirect:"+Global.getAdminPath()+"/hys/pdGroup/?repage";
		}else{
			String idArr = delAllIds.substring(0,delAllIds.length()-1);
			return "redirect:"+Global.getAdminPath()+"/hys/pdGroup/?idKey="+idArr+"&delKey=error";
		}
		
	}
	
	/**
	 * 校验产品组别是否存在
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("hys:pdGroup:view")
	@RequestMapping(value = "checkGroupName")
	public String checkGroupName( Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		//查询产品组别是否已经存在
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		try {
			PdGroup pdGroup = new PdGroup();
			pdGroup.setName(name);
			if(id!=null && !"".equals(id)){
				pdGroup.setId(id);
			}
			List<PdGroup>  pdGroups = pdGroupService.verify(pdGroup);
			if(pdGroups!=null && pdGroups.size()>0){
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
		return pdGroupService.synDataFromSpd(param);
	}

}