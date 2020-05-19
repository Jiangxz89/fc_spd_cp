/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.hys.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.City;
import com.thinkgem.jeesite.modules.sys.service.AreaService;

/**
 * 药材汇总统计
 * @author sutianqi
 * @version 2018-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdDrugGather")
public class PdDrugGatherController extends BaseController {

	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = {"base", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		//默认本月时间
		model.addAttribute("beginTime", DateUtils.getBeginDate());
		model.addAttribute("endTime", DateUtils.getEndDate());
		Area area = new Area();
		area.setId("1");
		area.setType(MinaGlobalConstants.AREA_2);
		List<City> findList = areaService.findListByParentCode(area);
		model.addAttribute("provincelist", JSONArray.fromObject(findList));//城市信息
		
		return "hys/pd/pdDrugChart";
	}
}