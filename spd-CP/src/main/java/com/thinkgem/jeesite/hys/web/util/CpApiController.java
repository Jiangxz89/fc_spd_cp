/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web.util;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.hys.entity.MedstoYkzkc;
import com.thinkgem.jeesite.hys.entity.MedstoYpcdmlk;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgzd;
import com.thinkgem.jeesite.hys.entity.MedstoYprkmx;
import com.thinkgem.jeesite.hys.entity.MedstoYprkzd;
import com.thinkgem.jeesite.hys.entity.MedstoYpthmx;
import com.thinkgem.jeesite.hys.entity.MedstoYpthzd;
import com.thinkgem.jeesite.hys.service.MedstoYkzkcService;
import com.thinkgem.jeesite.hys.service.MedstoYpcdmlkService;
import com.thinkgem.jeesite.hys.service.MedstoYpcgmxService;
import com.thinkgem.jeesite.hys.service.MedstoYpcgzdService;
import com.thinkgem.jeesite.hys.service.MedstoYppckService;
import com.thinkgem.jeesite.hys.service.MedstoYprkmxService;
import com.thinkgem.jeesite.hys.service.MedstoYprkzdService;
import com.thinkgem.jeesite.hys.service.MedstoYpthmxService;
import com.thinkgem.jeesite.hys.service.MedstoYpthzdService;

/**
 * 中心平台接受推送数据统一处理controller
 * @author Mr.Wang
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/pushData/api")
public class CpApiController extends BaseController {

	@Autowired
	private MedstoYpcgzdService medstoYpcgzdService;
	@Autowired
	private MedstoYpcgmxService medstoYpcgmxService;
	@Autowired
	private MedstoYpcdmlkService medstoYpcdmlkService;
	@Autowired
	private MedstoYkzkcService medstoYkzkcService;
	@Autowired
	private MedstoYppckService medstoYppckService;
	@Autowired
	private MedstoYprkzdService medstoYprkzdService;
	@Autowired
	private MedstoYprkmxService medstoYprkmxService;
	@Autowired
	private MedstoYpthzdService medstoYpthzdService;
	@Autowired
	private MedstoYpthmxService medstoYpthmxService;
	
	
	//同步药品
	@RequestMapping(value="drugInfo")
	@ResponseBody
	public String drugInfo(@RequestBody String body) {
		JSONObject json = new JSONObject();
		try {
			medstoYpcdmlkService.clearData();
			List<MedstoYpcdmlk> list = JSON.parseArray(body, MedstoYpcdmlk.class);
			medstoYpcdmlkService.batchInsert(list);
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toJSONString();
	}
	
	//同步药品采购订单
	@RequestMapping(value="drugOrder")
	@ResponseBody
	public String drugOrder(@RequestBody String body) {
		JSONObject json = new JSONObject();
		try {
			JSONArray array = JSON.parseArray(body);
			JSONObject obj = (JSONObject) array.get(0);
			dealData(obj);
			JSONArray ja = (JSONArray)obj.get("purchaseDetailList");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jb = ((JSONObject)ja.get(i));
				dealData(jb);
			}
			obj.put("purchaseDetailList", ja);
			MedstoYpcgzd zd = JSON.parseObject(obj.toJSONString(), MedstoYpcgzd.class);
			//保存药品采购单以及明细
			medstoYpcgzdService.save(zd);
			medstoYpcgmxService.batchInsert(zd.getPurchaseDetailList());
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toJSONString();
	}
	
	//同步药品总库存
	@RequestMapping(value="drugTotalStock")
	@ResponseBody
	public String drugTotalStock(@RequestBody String body) {
		JSONObject json = new JSONObject();
		try {
			medstoYkzkcService.clearData();
			List<MedstoYkzkc> list = JSON.parseArray(body, MedstoYkzkc.class);
			medstoYkzkcService.batchInsert(list);
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toJSONString();
	}
	
	//同步药品入库
	@RequestMapping(value="drugInStock")
	@ResponseBody
	public String drugInStock(@RequestBody String body) {
		JSONObject json = new JSONObject();
		try {
			//medstoYprkzdService.clearData();
			List<MedstoYprkzd> list = JSON.parseArray(body, MedstoYprkzd.class);
			medstoYprkzdService.batchInsert(list);
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toJSONString();
	}
	
	//同步药品入库明细
	@RequestMapping(value="drugInStockDetail")
	@ResponseBody
	public String drugInStockDetail(@RequestBody String body) {
		JSONObject json = new JSONObject();
		try {
			List<MedstoYprkmx> list = JSON.parseArray(body, MedstoYprkmx.class);
			medstoYprkmxService.batchInsert(list);
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toJSONString();
	}
	
	//同步药品退货
	@RequestMapping(value="drugRefund")
	@ResponseBody
	public String drugRefund(@RequestBody String body) {
		JSONObject json = new JSONObject();
		try {
			List<MedstoYpthzd> list = JSON.parseArray(body, MedstoYpthzd.class);
			medstoYpthzdService.batchInsert(list);
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toJSONString();
	}
	
	//同步药品退货明细
	@RequestMapping(value="drugRefundDetail")
	@ResponseBody
	public String drugRefundDetail(@RequestBody String body) {
		JSONObject json = new JSONObject();
		try {
			List<MedstoYpthmx> list = JSON.parseArray(body, MedstoYpthmx.class);
			medstoYpthmxService.batchInsert(list);
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toJSONString();
	}
	
	//为了同步spd的药品采购订单的状态(中心平台对订单进行操作改变了订单的状态)
	@RequestMapping(value = "syncDrugCgzdStatus", method=RequestMethod.POST)
	@ResponseBody
	public String syncDrugCgzdStatus(@RequestParam String dayTime, @RequestParam String hospitalCode) {
		JSONObject json = new JSONObject();
		try {
			MedstoYpcgzd zd = new MedstoYpcgzd();
			zd.setCjrq(dayTime);
			zd.setHospitalCode(hospitalCode);
			List<HashMap<String, String>> list = medstoYpcgzdService.getYpcgzdByHospAndDayTime(zd);
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
			if (list != null && list.size() > 0)
				json.put("data", JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toJSONString();
	}
	
	
	
	private JSONObject dealData(JSONObject json) {
		if (json == null)
			return json;
		json.remove("currentUser");
		json.remove("createBy");
		json.remove("createDate");
		json.remove("updateBy");
		json.remove("updateDate");
		json.remove("page");
		json.remove("global");
		json.remove("sqlMap");
		json.remove("dbName");
		json.remove("isNewRecord");
		json.remove("id");
		return json;
	}
}