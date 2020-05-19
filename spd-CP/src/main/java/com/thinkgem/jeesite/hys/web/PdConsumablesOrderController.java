/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.hys.entity.*;
import com.thinkgem.jeesite.hys.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.hys.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.hys.constants.MinaMsgCode;
import com.thinkgem.jeesite.hys.constants.RspVo;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
/**
 * 耗材订单表Controller
 * @author zhangxiaohan
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdConsumablesOrder")
public class PdConsumablesOrderController extends BaseController {

	@Autowired
	private PdConsumablesOrderService pdConsumablesOrderService;
	
	@Autowired
	private PdConsumablesOrderDetailService pdConsumablesOrderDetailService;
	
	@Autowired
	private PdConsumablesOrderTimeService pdConsumablesOrderTimeService;
	
	@Autowired
	private PdConsumablesRtService pdConsumablesRtService;
	
	@Autowired
	private PdConsumablesApartService pdConsumablesApartService;
	
	@Autowired
	private PdConsumablesApartRelevanceService pdConsumablesApartRelevanceService;
	
	@Autowired
	private PdSupplierService pdSupplierService;

	@Autowired
	private PdSupplierMidProductService pdSupplierMidProductService;
	
	@ModelAttribute
	public PdConsumablesOrder get(@RequestParam(required=false) String id) {
		PdConsumablesOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdConsumablesOrderService.get(id);
		}
		if (entity == null){
			entity = new PdConsumablesOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:pdConsumablesOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdConsumablesOrder pdConsumablesOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		//如果是供应商角色
		User user = UserUtils.getUser();
		if(user.getSupplierId()!=null){
			PdConsumablesApart consumablesApart = new PdConsumablesApart();
			consumablesApart.setSupplierId(user.getSupplierId());//供应商id
			consumablesApart.setQueryDate(pdConsumablesOrder.getQueryDate());//订单日期
			consumablesApart.setNumber(pdConsumablesOrder.getNumber());//订单编号
			consumablesApart.setOrderState(pdConsumablesOrder.getOrderState());//订单状态
			consumablesApart.setHospitalName(pdConsumablesOrder.getHospitalName());//下单医院
			Page<PdConsumablesApart> page = pdConsumablesApartService.findPage(new Page<PdConsumablesApart>(request, response), consumablesApart);
			model.addAttribute("page", page);
			return "hys/pd/pdSuConsumablesOrderList";
		}else{
			Page<PdConsumablesOrder> page = pdConsumablesOrderService.findDataList(new Page<PdConsumablesOrder>(request, response), pdConsumablesOrder); 
			model.addAttribute("page", page);
			return "hys/pd/pdConsumablesOrderList";
		}
	}

	@RequiresPermissions("hys:pdConsumablesOrder:view")
	@RequestMapping(value = "form")
	public String form(PdConsumablesOrder pdConsumablesOrder, Model model) {
		model.addAttribute("pdConsumablesOrder", pdConsumablesOrder);
		return "jeesite/hys/pdConsumablesOrderForm";
	}

	@RequiresPermissions("hys:pdConsumablesOrder:edit")
	@RequestMapping(value = "save")
	public String save(PdConsumablesOrder pdConsumablesOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdConsumablesOrder)){
			return form(pdConsumablesOrder, model);
		}
		pdConsumablesOrderService.save(pdConsumablesOrder);
		addMessage(redirectAttributes, "保存保存耗材订单成功成功");
		return "redirect:"+Global.getAdminPath()+"/hys/pdConsumablesOrder/?repage";
	}
	
	@RequiresPermissions("hys:pdConsumablesOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(PdConsumablesOrder pdConsumablesOrder, RedirectAttributes redirectAttributes) {
		pdConsumablesOrderService.delete(pdConsumablesOrder);
		addMessage(redirectAttributes, "删除保存耗材订单成功成功");
		return "redirect:"+Global.getAdminPath()+"/hys/pdConsumablesOrder/?repage";
	}
	
	/**
	 * 获取耗材订单详情
	 * @param pdConsumablesOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hys:pdConsumablesOrder:view")
	@RequestMapping(value = "viewIn")
	public String viewIn(PdConsumablesOrder pdConsumablesOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(pdConsumablesOrder.getId())){
			pdConsumablesOrder = pdConsumablesOrderService.getConsumablesOrder(pdConsumablesOrder);
			PdConsumablesApart pdConsumablesApart  = new PdConsumablesApart();
			pdConsumablesApart.setNumber(pdConsumablesOrder.getNumber());
			List<PdConsumablesApart> pdConsumablesAparts = pdConsumablesApartService.findList(pdConsumablesApart);
			model.addAttribute("pdConsumablesOrder", pdConsumablesOrder);
			model.addAttribute("pdConsumablesAparts", pdConsumablesAparts);
		}
		return "hys/pd/pdConsumablesOrderDetail";
	}
	
	/**
	 * 获取拆分后的详情
	 * @param pdConsumablesApart
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hys:pdConsumablesOrder:view")
	@RequestMapping(value = "pdConsumablesApartDetail")
	public String pdConsumablesApartDetail(PdConsumablesApart pdConsumablesApart,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(pdConsumablesApart.getId())){
			pdConsumablesApart = pdConsumablesApartService.getDetail(pdConsumablesApart);
			PdConsumablesApartRelevance pdConsumablesApartRelevance = new PdConsumablesApartRelevance();
			pdConsumablesApartRelevance.setApartId(pdConsumablesApart.getId());
			pdConsumablesApartRelevance.setHospital(pdConsumablesApart.getHospital());
			List<PdConsumablesApartRelevance> pdConsumablesApartRelevances = pdConsumablesApartRelevanceService.findList(pdConsumablesApartRelevance);
			pdConsumablesApart.setPdConsumablesApartRelevances(pdConsumablesApartRelevances);
			model.addAttribute("pdConsumablesApart", pdConsumablesApart);
		}
		return "hys/pd/PdConsumablesApartDetail";
	}
	
	/**
	 * 审核页面详情
	 * @param pdConsumablesOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hys:pdConsumablesOrder:view")
	@RequestMapping(value = "examine")
	public String examine(PdConsumablesOrder pdConsumablesOrder, String formType,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		if(StringUtils.isNotBlank(pdConsumablesOrder.getId())){
			pdConsumablesOrder = pdConsumablesOrderService.get(pdConsumablesOrder);
			PdConsumablesOrderDetail pdConsumablesOrderDetail = new PdConsumablesOrderDetail();
			pdConsumablesOrderDetail.setOrderId(pdConsumablesOrder.getId());
			pdConsumablesOrderDetail.setHospital(pdConsumablesOrder.getHospital());
			List<PdConsumablesOrderDetail> pdConsumablesOrderDetails = pdConsumablesOrderDetailService.findList(pdConsumablesOrderDetail);
			pdConsumablesOrder.setPdConsumablesOrderDetails(pdConsumablesOrderDetails);
		}
		model.addAttribute("formType", formType);
		model.addAttribute("pdConsumablesOrder", pdConsumablesOrder);
		return "hys/pd/pdConsumablesOrderExamine";
	}
	
	/**
	 * 耗材订单审核
	 * @param pdConsumablesOrder
	 * @return
	 */
	@RequestMapping(value = "auditConsumablesOrder", method=RequestMethod.POST)
	@ResponseBody
	public RspVo auditApplyOrder(PdConsumablesOrder pdConsumablesOrder){
		RspVo vo = new RspVo();
		String orderState = pdConsumablesOrder.getOrderState();
		try {
			//如果是审核
			if(MinaGlobalConstants.CONSUMABLES_ORDER_STATE_1.equals(orderState)){
				//查询订单详情
				//通过时拆分订单
				pdConsumablesOrder = pdConsumablesOrderService.get(pdConsumablesOrder);
				pdConsumablesOrder.setOrderState(orderState);
				PdConsumablesOrderDetail pdConsumablesOrderDetail = new PdConsumablesOrderDetail();
				pdConsumablesOrderDetail.setOrderId(pdConsumablesOrder.getId());
				pdConsumablesOrderDetail.setHospital(pdConsumablesOrder.getHospital());
				//查询该订单所有的耗材
				List<PdConsumablesOrderDetail> pdConsumablesOrderDetails = pdConsumablesOrderDetailService.findList(pdConsumablesOrderDetail);
				if(pdConsumablesOrderDetails!=null && pdConsumablesOrderDetails.size()>0){
					Map<String,List<PdConsumablesApartRelevance>> map = new HashMap<String,List<PdConsumablesApartRelevance>>();
					for(PdConsumablesOrderDetail consumablesOrderDetail :pdConsumablesOrderDetails){
						//查找耗材对应的供应商名称查找供应商id
						PdSupplier pdSupplier = new PdSupplier ();
						pdSupplier.setName(consumablesOrderDetail.getSupplierName());
						pdSupplier.setHospital(pdConsumablesOrder.getHospital());
						pdSupplier = pdSupplierService.getByName(pdSupplier);
						if(pdSupplier!=null){
							String key = pdSupplier.getId();
							if(map.containsKey(key)){
								List<PdConsumablesApartRelevance> pdConsumablesApartRelevances = map.get(key);
								PdConsumablesApartRelevance pdConsumablesApartRelevance = new PdConsumablesApartRelevance();
								pdConsumablesApartRelevance.setConsumablesNumber(consumablesOrderDetail.getNumber());
								pdConsumablesApartRelevance.setOrderQuantity(consumablesOrderDetail.getOrderQuantity());
								pdConsumablesApartRelevance.setPrice(consumablesOrderDetail.getPrice());
								pdConsumablesApartRelevance.setAmount(consumablesOrderDetail.getAmount());
								pdConsumablesApartRelevances.add(pdConsumablesApartRelevance);
								map.put(key, pdConsumablesApartRelevances);
							}else{
								List<PdConsumablesApartRelevance> pdConsumablesApartRelevances = new ArrayList<PdConsumablesApartRelevance>();
								PdConsumablesApartRelevance pdConsumablesApartRelevance = new PdConsumablesApartRelevance();
								pdConsumablesApartRelevance.setConsumablesNumber(consumablesOrderDetail.getNumber());
								pdConsumablesApartRelevance.setOrderQuantity(consumablesOrderDetail.getOrderQuantity());
								pdConsumablesApartRelevance.setPrice(consumablesOrderDetail.getPrice());
								pdConsumablesApartRelevance.setAmount(consumablesOrderDetail.getAmount());
								pdConsumablesApartRelevances.add(pdConsumablesApartRelevance);
								map.put(key, pdConsumablesApartRelevances);
							}
						}else{
							vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
							vo.setInfo("系统没找到供应商名称为:"+consumablesOrderDetail.getSupplierName()+"的信息或该供应商没有关联下单的医院");
							return vo;
						}
					}
					//循环遍历map
					List<PdConsumablesApart> pdConsumablesAparts = new ArrayList<PdConsumablesApart>();
					List<PdConsumablesApartRelevance> pdConsumablesApartRelevanceList = new ArrayList<PdConsumablesApartRelevance>();
					for(String key:map.keySet()){
						List<PdConsumablesApartRelevance> pdConsumablesApartRelevances = map.get(key);
						PdConsumablesApart pdConsumablesApart = new PdConsumablesApart();
						pdConsumablesApart.setNumber(pdConsumablesOrder.getNumber());
						pdConsumablesApart.setSupplierId(key);
						pdConsumablesApart.preInsert();
						int orderQuantity = 0;
						BigDecimal dosagertMoney = new BigDecimal(0);	//金额
						for(PdConsumablesApartRelevance pdConsumablesApartRelevance:pdConsumablesApartRelevances){
							pdConsumablesApartRelevance.setApartId(pdConsumablesApart.getId());
							pdConsumablesApartRelevance.preInsert();
							orderQuantity += pdConsumablesApartRelevance.getOrderQuantity();
							BigDecimal pdMoney = new BigDecimal(pdConsumablesApartRelevance.getOrderQuantity()).multiply(new BigDecimal(pdConsumablesApartRelevance.getPrice()));
							dosagertMoney = pdMoney.add(dosagertMoney);
						}
						//获取订单量
						pdConsumablesApart.setOrderQuantity(orderQuantity);
						pdConsumablesApart.setOrderAmount(dosagertMoney.doubleValue());
						pdConsumablesAparts.add(pdConsumablesApart);
						pdConsumablesApartRelevanceList.addAll(pdConsumablesApartRelevances);
					}
					if(pdConsumablesAparts.size()>0){
						//批量添加耗材订单拆分
						pdConsumablesApartService.batchSave(pdConsumablesAparts);
					}
					if(pdConsumablesApartRelevanceList.size()>0){
						//批量添加耗材订单拆分关联耗材
						pdConsumablesApartRelevanceService.batchSave(pdConsumablesApartRelevanceList);
					}
					//修改状态
					pdConsumablesOrder.setAuditBy(UserUtils.getUser().getId());
					int flag = pdConsumablesOrderService.updateStatus(pdConsumablesOrder);
					if(flag == 0)
						vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
				}
			}else{
				//修改状态
				pdConsumablesOrder.setAuditBy(UserUtils.getUser().getId());
				int flag = pdConsumablesOrderService.updateStatus(pdConsumablesOrder);
				if(flag == 0)
					vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
		}
		return vo;
	}
	
	/**
	 * 保存耗材订单(供其他服务访问)
	 */
/*	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "saveConsumablesOrder")
	public String saveConsumablesOrder(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray= null;
		try{
			//接收数据
			jsonArray =JSONArray.fromObject(param);
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "数据格式异常");
			return resultJson.toString();
		}
		try{
			//如果已经同步便不再同步
			Map<String, Class> classMap = new HashMap<String, Class>();
	        classMap.put("pdConsumablesOrderDetails", PdConsumablesOrderDetail.class);
			//转换成耗材订单对象
	        if(jsonArray!=null && jsonArray.size()>0){
	        	String[] dateFormats = new String[] {"yyyy-MM-dd"};
		        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
	        	List<PdConsumablesOrder> pdConsumablesOrders = new ArrayList<PdConsumablesOrder>();
	        	List<PdConsumablesOrderDetail> pdConsumablesOrderDetailList = new ArrayList<PdConsumablesOrderDetail>();
	        	 for(int i=0;i<jsonArray.size();i++){
	        		 PdConsumablesOrder pdConsumablesOrder = (PdConsumablesOrder) JSONObject.toBean((JSONObject) jsonArray.get(i), PdConsumablesOrder.class,classMap);
	        		//保存耗材订单
	     			pdConsumablesOrder.setOrderState(MinaGlobalConstants.CONSUMABLES_ORDER_STATE_0);//待接收
	     			//pdConsumablesOrder.setOrderDate(DateUtils.parseDate(DateUtils.getDate()));//订单日期
	     			//pdConsumablesOrderService.save(pdConsumablesOrder);
	     			pdConsumablesOrder.preInsert();
	     			pdConsumablesOrder.setSyncDate(DateUtils.parseDate(DateUtils.getDateTime()));//同步时间
	     			pdConsumablesOrders.add(pdConsumablesOrder);
	     			List<PdConsumablesOrderDetail> pdConsumablesOrderDetails = pdConsumablesOrder.getPdConsumablesOrderDetails();
	     			if(pdConsumablesOrderDetails != null && pdConsumablesOrderDetails.size()>0){
	     				for(PdConsumablesOrderDetail pdConsumablesOrderDetail :pdConsumablesOrderDetails){
	     					pdConsumablesOrderDetail.setOrderId(pdConsumablesOrder.getId());
	     					pdConsumablesOrderDetail.preInsert();
	     				}
	     				//保存耗材订单详情
	     				pdConsumablesOrderDetailList.addAll(pdConsumablesOrderDetails);
	     				//pdConsumablesOrderDetailService.batchSave(pdConsumablesOrderDetails);
	     			}else{
	     				resultJson.put("statusCode", "500");
	     				resultJson.put("msg", "数据格式异常");
	     				return resultJson.toString();
	     			}
	        	 }
	        	 //批量保存耗材订单
	        	 if(pdConsumablesOrders.size()>0){
	        		//根据订单日期是否已经存在订单
	        		 PdConsumablesOrder co = new PdConsumablesOrder();
	        		 co.setQueryDate(DateUtils.formatDateTime(pdConsumablesOrders.get(0).getOrderDate()));
	        		 List<PdConsumablesOrder> datas = pdConsumablesOrderService.findList(co);
	        		 if(datas!=null && datas.size()==0){
	        			 pdConsumablesOrderService.batchSave(pdConsumablesOrders);
        			 	 //批量保存耗材订单详情
			        	 if(pdConsumablesOrderDetailList.size()>0){
			        		 pdConsumablesOrderDetailService.batchSave(pdConsumablesOrderDetailList);
			        	 }
	        		 }
	        	 }
	        }
		
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "保存失败");
			return resultJson.toString();
		}
		resultJson.put("statusCode", "200");
		resultJson.put("msg", "success");
		return resultJson.toString();
	}*/
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "saveConsumablesOrder")
	public String saveConsumablesOrder(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray= null;
		try{
			//接收数据
			jsonArray =JSONArray.fromObject(param);
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "数据格式异常");
			return resultJson.toString();
		}
		try{
			//如果已经同步便不再同步
			Map<String, Class> classMap = new HashMap<String, Class>();
	        classMap.put("pdConsumablesOrderDetails", PdConsumablesOrderDetail.class);
			//转换成耗材订单对象
	        if(jsonArray!=null && jsonArray.size()>0){
	        	String[] dateFormats = new String[] {"yyyy-MM-dd"};
		        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
	        	List<PdConsumablesOrder> pdConsumablesOrders = new ArrayList<PdConsumablesOrder>();
	        	List<PdConsumablesOrderDetail> pdConsumablesOrderDetailList = new ArrayList<PdConsumablesOrderDetail>();
	        	 for(int i=0;i<jsonArray.size();i++){
	        		 PdConsumablesOrder pdConsumablesOrder = (PdConsumablesOrder) JSONObject.toBean((JSONObject) jsonArray.get(i), PdConsumablesOrder.class,classMap);


	        		//保存耗材订单
	     			pdConsumablesOrder.setOrderState(MinaGlobalConstants.CONSUMABLES_ORDER_STATE_0);//待接收
	     			//pdConsumablesOrder.setOrderDate(DateUtils.parseDate(DateUtils.getDate()));//订单日期
	     			//pdConsumablesOrderService.save(pdConsumablesOrder);
	     			pdConsumablesOrder.preInsert();
	     			pdConsumablesOrder.setSyncDate(DateUtils.parseDate(DateUtils.getDateTime()));//同步时间
	     			pdConsumablesOrders.add(pdConsumablesOrder);
	     			List<PdConsumablesOrderDetail> pdConsumablesOrderDetails = pdConsumablesOrder.getPdConsumablesOrderDetails();
	     			if(pdConsumablesOrderDetails != null && pdConsumablesOrderDetails.size()>0){
	     				for(PdConsumablesOrderDetail pdConsumablesOrderDetail :pdConsumablesOrderDetails){
							// add by jiangxz 20191105 这边判断spd与平台 产品-供应商 关系是否一致，如果不一直则终止流程并提示
							PdSupplierMidProduct midProduct = new PdSupplierMidProduct();
							midProduct.setProductId(pdConsumablesOrderDetail.getProductId());
							midProduct.setSupplierName(pdConsumablesOrderDetail.getSupplierName());
							List<PdSupplierMidProduct> midProductList = pdSupplierMidProductService.findMidProductList(midProduct);
							if(midProductList==null || midProductList.size() <= 0){
								resultJson.put("statusCode", "500");
								resultJson.put("msg", "供应商["+pdConsumablesOrderDetail.getSupplierName()+"]下没有产品["+pdConsumablesOrderDetail.getName()+"]，请检查该供应商或联系平台管理员！");
								return resultJson.toString();
							}

	     					pdConsumablesOrderDetail.setOrderId(pdConsumablesOrder.getId());
	     					pdConsumablesOrderDetail.preInsert();
	     				}
	     				//保存耗材订单详情
	     				pdConsumablesOrderDetailList.addAll(pdConsumablesOrderDetails);
	     				//pdConsumablesOrderDetailService.batchSave(pdConsumablesOrderDetails);
	     			}else{
	     				resultJson.put("statusCode", "500");
	     				resultJson.put("msg", "数据格式异常");
	     				return resultJson.toString();
	     			}
	        	 }
	        	 //批量保存耗材订单
	        	 if(pdConsumablesOrders.size()>0){
	        		//根据订单编号查询是否已经存在订单
	        		 PdConsumablesOrder co = new PdConsumablesOrder();
	        		 co.setNumber(pdConsumablesOrders.get(0).getNumber());
	        		 List<PdConsumablesOrder> datas = pdConsumablesOrderService.findList(co);
	        		 if(datas!=null && datas.size()==0){
	        			 pdConsumablesOrderService.batchSave(pdConsumablesOrders);
        			 	 //批量保存耗材订单详情
			        	 if(pdConsumablesOrderDetailList.size()>0){
			        		 pdConsumablesOrderDetailService.batchSave(pdConsumablesOrderDetailList);
			        	 }
	        		 }
	        	 }
	        }
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "保存失败");
			return resultJson.toString();
		}
		resultJson.put("statusCode", "200");
		resultJson.put("msg", "success");
		return resultJson.toString();
	}
	
	/**
	 * 耗材订单及配送查询
	 * @param pdConsumablesOrderTime
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hys:pdConsumablesOrder:view")
	@RequestMapping(value = {"detailList", ""})
	public String detailList(PdConsumablesOrderTime pdConsumablesOrderTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdConsumablesOrderTime> page = pdConsumablesOrderTimeService.findPage(new Page<PdConsumablesOrderTime>(request, response), pdConsumablesOrderTime); 
		model.addAttribute("page", page);
		return "hys/pd/pdConsumablesDetailList";
	}
	
	
	/**
	 * 保存耗材订单及配送(供其他服务访问)
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "saveConsumablesOrderDetail")
	public String saveConsumablesOrderDetail(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = null;
		try{
			//接收数据
			jsonArray =JSONArray.fromObject(param);
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "数据格式异常");
			return resultJson.toString();
		}
		try{
			List<PdConsumablesOrderTime> pdConsumablesOrderTimes = (List<PdConsumablesOrderTime>) JSONArray.toCollection(jsonArray, PdConsumablesOrderTime.class); 
			if(pdConsumablesOrderTimes!=null && pdConsumablesOrderTimes.size()>0){
				List<String> idArrays = new ArrayList<String>();
				for(PdConsumablesOrderTime pdConsumablesOrderTime:pdConsumablesOrderTimes){
					idArrays.add(pdConsumablesOrderTime.getNumber());
					pdConsumablesOrderTime.preInsert();
					pdConsumablesOrderTime.setSyncDate(DateUtils.parseDate(DateUtils.getDateTime()));
				}
				PdConsumablesOrderTime consumablesOrderTime = new PdConsumablesOrderTime();
				consumablesOrderTime.setCheckTime(pdConsumablesOrderTimes.get(0).getCheckTime());
				List<PdConsumablesOrderTime> consumablesOrderTimeList = pdConsumablesOrderTimeService.findList(consumablesOrderTime);
				//避免重复
				if(consumablesOrderTimeList!=null && consumablesOrderTimeList.size()==0){
					//保存耗材订单及配送
					pdConsumablesOrderTimeService.batchSave(pdConsumablesOrderTimes);
					//修改耗材订单的状态 已收货  批量更新
					if(idArrays.size()>0){
						Map<String, Object> map = new HashMap<String,Object>();
						map.put("orderState", MinaGlobalConstants.CONSUMABLES_ORDER_STATE_3);
						map.put("ids", idArrays);
						pdConsumablesOrderService.batchUpdate(map);
					}
				}
			}else{
				resultJson.put("statusCode", "500");
				resultJson.put("msg", "数据格式异常");
				return resultJson.toString();
			}
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "保存失败");
			return resultJson.toString();
		}
		resultJson.put("statusCode", "200");
		resultJson.put("msg", "success");
		return resultJson.toString();
	}
	
	/**
	 * 耗材退货查询
	 * @param pdConsumablesRt
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hys:pdConsumablesOrder:view")
	@RequestMapping(value = {"rtDetailList", ""})
	public String rtDetailList(PdConsumablesRt pdConsumablesRt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdConsumablesRt> page = pdConsumablesRtService.findPage(new Page<PdConsumablesRt>(request, response), pdConsumablesRt); 
		model.addAttribute("page", page);
		return "hys/pd/pdConsumablesRtDetailList";
	}
	
	/**
	 * 保存耗材退货(供其他服务访问)
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "saveConsumablesRtDetail")
	public String saveConsumablesRtDetail(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = null;
		try{
			//接收数据
			jsonArray =JSONArray.fromObject(param);
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "数据格式异常");
			return resultJson.toString();
		}
		try{
			String[] dateFormats = new String[] {"yyyy-MM-dd"};
	        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
			List<PdConsumablesRt> pdConsumablesRts = (List<PdConsumablesRt>) JSONArray.toCollection(jsonArray, PdConsumablesRt.class); 
			if(pdConsumablesRts!=null && pdConsumablesRts.size()>0){
				PdConsumablesRt cRt = new PdConsumablesRt();
				cRt.setQueryDate(DateUtils.formatDateTime(pdConsumablesRts.get(0).getRtDate()));
				List<PdConsumablesRt> consumablesRtList = pdConsumablesRtService.findList(cRt);
				//避免重复
				if(consumablesRtList!=null && consumablesRtList.size()==0){
					for(PdConsumablesRt pdConsumablesRt:pdConsumablesRts){
						pdConsumablesRt.preInsert();
						pdConsumablesRt.setSyncDate(DateUtils.parseDate(DateUtils.getDateTime()));
					}
					//保存耗材退货
					pdConsumablesRtService.batchSave(pdConsumablesRts);
				}
			}else{
				resultJson.put("statusCode", "500");
				resultJson.put("msg", "数据格式异常");
				return resultJson.toString();
			}
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "保存失败");
			return resultJson.toString();
		}
		resultJson.put("statusCode", "200");
		resultJson.put("msg", "success");
		return resultJson.toString();
	}
	
	/**
	 * 为了同步spd的产品采购订单的状态(中心平台对订单进行操作改变了订单的状态)
	 * @param dayTime
	 * @param hospitalCode
	 * @return
	 */
	@RequestMapping(value = "syncPdoductStatus", method=RequestMethod.POST)
	@ResponseBody
	public String syncPdoductStatus(@RequestParam String dayTime, @RequestParam String hospitalCode) {
		JSONObject json = new JSONObject();
		try {
			PdConsumablesOrder consumablesOrder = new PdConsumablesOrder();
			consumablesOrder.setSyncDate(DateUtils.parseDate(dayTime));
			consumablesOrder.setHospital(hospitalCode);
			List<HashMap<String, String>> list = pdConsumablesOrderService.getOrderByHospAndDayTime(consumablesOrder);
			json.put("statusCode", "200");
			json.put("msg", "推送成功");
			if (list != null && list.size() > 0)
				json.put("data", JSONArray.fromObject(list));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("statusCode", "500");
			json.put("msg", "推送失败");
		}
		return json.toString();
	}
}