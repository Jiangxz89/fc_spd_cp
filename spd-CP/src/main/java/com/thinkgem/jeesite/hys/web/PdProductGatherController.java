/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.hys.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrder;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrderTime;
import com.thinkgem.jeesite.hys.entity.PdHospital;
import com.thinkgem.jeesite.hys.entity.PdSupplier;
import com.thinkgem.jeesite.hys.entity.temp.PdProductGather;
import com.thinkgem.jeesite.hys.service.PdConsumablesOrderService;
import com.thinkgem.jeesite.hys.service.PdConsumablesOrderTimeService;
import com.thinkgem.jeesite.hys.service.PdHospitalService;
import com.thinkgem.jeesite.hys.service.PdProductGatherService;
import com.thinkgem.jeesite.hys.service.PdSupplierService;
import com.thinkgem.jeesite.hys.utils.CommonUtils;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.City;
import com.thinkgem.jeesite.modules.sys.service.AreaService;

import net.sf.json.JSONArray;

/**
 * 耗材汇总统计
 * @author sutianqi
 * @version 2018-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdProductGather")
public class PdProductGatherController extends BaseController {

	@Autowired
	private PdProductGatherService pdProductGatherService;
	@Autowired
	private PdSupplierService pdSupplierService;
	@Autowired
	private PdConsumablesOrderService pdConsumablesOrderService;
	@Autowired
	private PdConsumablesOrderTimeService pdConsumablesOrderTimeService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private PdHospitalService pdHospitalService;
	
	/**
	 * 耗材汇总统计页面
	 * */
	@RequestMapping(value = {"base", ""})
	public String base(HttpServletRequest request, HttpServletResponse response, Model model) {
		PdProductGather gather = new PdProductGather();
		gather.setBeginTime(DateUtils.getBeginDate());
		gather.setEndTime(DateUtils.getEndDate());
		Area area = new Area();
		area.setId("1");
		area.setType(MinaGlobalConstants.AREA_2);
		List<City> findList = areaService.findListByParentCode(area);
		request.setAttribute("gather", gather);
		model.addAttribute("provincelist", JSONArray.fromObject(findList));//城市信息
		return "hys/pd/pdProductChart";
	}
	
	/**
	 * 耗材采购金额排名表
	 * */
	@RequestMapping(value = {"consumablesAmountTable", ""})
	public String consumablesAmountTable(HttpServletRequest request, HttpServletResponse response, Model model) {
		String time = request.getParameter("time");
		model.addAttribute("time",time);
		return "hys/pd/pdConsumablesAmountTable";
	}
	
	/**
	 * 初始化可视化图表信息（默认：时间：上月1号-29/30/31号，供应商：国药集团，医院：东乡妇幼保健院，地区：江西省抚州市东乡县）
	 * */
	@RequestMapping(value = "baseJson")
	public void baseJson(HttpServletRequest request, HttpServletResponse response, Model model) {
		PdProductGather gather = new PdProductGather();
		PdProductGather init = pdProductGatherService.init(gather);
		
		String json = JSONObject.toJSONString(init);
		
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax
	 * 取得所有供应商
	 * */
	@RequestMapping(value = "supplierJson")
	public void supplierJson(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PdSupplier> findList = pdSupplierService.findList(new PdSupplier());
		
		String json = JSONObject.toJSONString(findList);
		
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax
	 * 企业配送金额比率供应商变更
	 * */
	@RequestMapping(value = "changeSupplierJson")
	public void changeSupplierJson(HttpServletRequest request, HttpServletResponse response, Model model) {
		String supplierId = request.getParameter("supplierId");
		PdSupplier pdSupplier = pdSupplierService.get(supplierId);
		
		PdProductGather gather = new PdProductGather();
		gather.setPdSupplier(pdSupplier);
		pdProductGatherService.supplierDelivery(gather);
		
		
		String json = JSONObject.toJSONString(gather);
		
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 区域采购情况分析区域变更
	 * */
	@RequestMapping(value = "changeAreaJson")
	public void changeAreaJson(HttpServletRequest request, HttpServletResponse response, Model model) {
		String areaProvince = request.getParameter("areaProvince");
		String areaCity = request.getParameter("areaCity");
		String areaTown = request.getParameter("areaTown");
		
		PdProductGather gather = new PdProductGather();
		gather.setAreaProvince(areaProvince);
		gather.setAreaCity(areaCity);
		gather.setAreaTown(areaTown);
		
		pdProductGatherService.areaConsumablesAnalyze(gather);
		
		String json = JSONObject.toJSONString(gather);
		
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 时间变更
	 * */
	@RequestMapping(value = "changeDateJson")
	public void changeDateJson(HttpServletRequest request, HttpServletResponse response, Model model) {
		String time = request.getParameter("time");
		
		String times[] = time.split(" - ");
		Date beginTime = CommonUtils.stringToDate(times[0]);
		Date endTime = CommonUtils.stringToDate(times[1]);
		
		PdProductGather gather = new PdProductGather();
		gather.setBeginTime(beginTime);
		gather.setEndTime(endTime);
		
		pdProductGatherService.init(gather);
		
		String json = JSONObject.toJSONString(gather);
		
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 采购金额排名TOP10
	 * @param pdConsumablesRt
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findPurchaseAmount")
	public List<PdConsumablesOrder> findPurchaseAmount(HttpServletRequest request, HttpServletResponse response, Model model) {
		String time = request.getParameter("time");
		String times[] = time.split(" - ");
		Date beginTime = CommonUtils.stringToDate(times[0]);
		Date endTime = CommonUtils.stringToDate(times[1]);
		PdConsumablesOrder pdConsumablesOrder = new PdConsumablesOrder();
		pdConsumablesOrder.setStartDate(beginTime);
		pdConsumablesOrder.setEndDate(endTime);
		List<PdConsumablesOrder> pdConsumablesOrders = pdConsumablesOrderService.findPurchaseAmount(pdConsumablesOrder);
		return pdConsumablesOrders;
	}
	
	/**
	 * 根据时间查询一段时间内所有的供应商
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSupplierList")
	public List<PdConsumablesOrderTime> findSupplierList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String time = request.getParameter("time");
		String times[] = time.split(" - ");
		Date beginTime = CommonUtils.stringToDate(times[0]);
		Date endTime = CommonUtils.stringToDate(times[1]);
		PdConsumablesOrderTime pdConsumablesOrderTime = new PdConsumablesOrderTime();
		pdConsumablesOrderTime.setStartDate(beginTime);
		pdConsumablesOrderTime.setEndDate(endTime);
		List<PdConsumablesOrderTime> pdConsumablesOrderTimes = pdConsumablesOrderTimeService.findSupplierList(pdConsumablesOrderTime);
		return pdConsumablesOrderTimes;
	}
	
	/**
	 * 企业配送金额比率
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findPsAmount")
	public List<PdConsumablesOrderTime> findPsAmount(HttpServletRequest request, HttpServletResponse response, Model model) {
		String time = request.getParameter("time");
		String supplierId = request.getParameter("supplierId");
		String times[] = time.split(" - ");
		Date beginTime = CommonUtils.stringToDate(times[0]);
		Date endTime = CommonUtils.stringToDate(times[1]);
		PdConsumablesOrderTime pdConsumablesOrderTime = new PdConsumablesOrderTime();
		pdConsumablesOrderTime.setStartDate(beginTime);
		pdConsumablesOrderTime.setEndDate(endTime);
		pdConsumablesOrderTime.setSupplierId(supplierId);
		List<PdConsumablesOrderTime> pdConsumablesOrderTimes = pdConsumablesOrderTimeService.findPsAmount(pdConsumablesOrderTime);
		return pdConsumablesOrderTimes;
	}
	
	/**
	 * 区域采购金额情况分析
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findCgAmountSituation")
	public List<PdHospital> findCgAmountSituation(HttpServletRequest request, HttpServletResponse response, Model model) {
		String time = request.getParameter("time");
		String times[] = time.split(" - ");
		Date beginTime = CommonUtils.stringToDate(times[0]);
		Date endTime = CommonUtils.stringToDate(times[1]);
		String areaProvince = request.getParameter("areaProvince");
		String areaCity = request.getParameter("areaCity");
		String areaTown = request.getParameter("areaTown");
		List<PdHospital> hospitals = new ArrayList<PdHospital>();
		if(areaProvince!=null && !"".equals(areaProvince) 
				&& areaCity!=null && !"".equals(areaCity) 
				&& areaTown!=null && !"".equals(areaTown)){
			PdHospital hospital = new PdHospital();
			hospital.setStartDate(beginTime);
			hospital.setEndDate(endTime);
			hospital.setAreaProvince(areaProvince);
			hospital.setAreaCity(areaCity);
			hospital.setAreaTown(areaTown);
			hospitals = pdHospitalService.findCgAmountSituation(hospital);
			return hospitals;
		}else{
			return hospitals;
		}
	}
	
	/**
	 * 查看表格
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "pdConsumablesAmountData")
	public List<PdConsumablesOrder> pdConsumablesAmountData(HttpServletRequest request, HttpServletResponse response, Model model) {
		String time = request.getParameter("time");
		String times[] = time.split(" - ");
		Date beginTime = CommonUtils.stringToDate(times[0]);
		Date endTime = CommonUtils.stringToDate(times[1]);
		PdConsumablesOrder pdConsumablesOrder = new PdConsumablesOrder();
		pdConsumablesOrder.setStartDate(beginTime);
		pdConsumablesOrder.setEndDate(endTime);
		List<PdConsumablesOrder> pdConsumablesOrders = pdConsumablesOrderService.findPurchaseAmount(pdConsumablesOrder);
		return pdConsumablesOrders;
	}
}