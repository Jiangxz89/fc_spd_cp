/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.hys.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.hys.constants.MinaMsgCode;
import com.thinkgem.jeesite.hys.constants.RspVo;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgmx;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgmxcf;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgmxcfdt;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgzd;
import com.thinkgem.jeesite.hys.entity.PdSupplier;
import com.thinkgem.jeesite.hys.service.MedstoYpcgmxService;
import com.thinkgem.jeesite.hys.service.MedstoYpcgmxcfService;
import com.thinkgem.jeesite.hys.service.MedstoYpcgmxcfdtService;
import com.thinkgem.jeesite.hys.service.MedstoYpcgzdService;
import com.thinkgem.jeesite.hys.service.PdSupplierService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.SnoGerUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 药库采购单Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/medstoYpcgzd")
public class MedstoYpcgzdController extends BaseController {

	@Autowired
	private MedstoYpcgzdService medstoYpcgzdService;
	@Autowired
	private MedstoYpcgmxService medstoYpcgmxService;
	@Autowired
	private PdSupplierService pdSupplierService;
	@Autowired
	private MedstoYpcgmxcfService medstoYpcgmxcfService;
	@Autowired
	private MedstoYpcgmxcfdtService medstoYpcgmxcfdtService;
	
	@ModelAttribute
	public MedstoYpcgzd get(@RequestParam(required=false) String id) {
		MedstoYpcgzd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpcgzdService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpcgzd();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:medstoYpcgzd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpcgzd medstoYpcgzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String supplierId = user.getSupplierId();
		//供应商用户
		if (!StringUtils.isEmpty(supplierId)) {
			MedstoYpcgmxcf medstoYpcgmxcf = new MedstoYpcgmxcf();
			medstoYpcgmxcf.setSupplierId(user.getSupplierId());//供应商id
			medstoYpcgmxcf.setQueryDate(medstoYpcgzd.getQueryDate());//订单日期
			medstoYpcgmxcf.setDjh(medstoYpcgzd.getDjh());//订单编号
			medstoYpcgmxcf.setJlzt(medstoYpcgzd.getJlzt());//订单状态
			medstoYpcgmxcf.setHospitalCode(medstoYpcgzd.getHospitalCode());//下单医院
			Page<MedstoYpcgmxcf> page = medstoYpcgmxcfService.findPage(new Page<MedstoYpcgmxcf>(request, response), medstoYpcgmxcf);
			model.addAttribute("page", page);
			return "hys/pd/pdSuDrugOrderList";
		}else{
			Page<MedstoYpcgzd> page = medstoYpcgzdService.findPage(new Page<MedstoYpcgzd>(request, response), medstoYpcgzd); 
			model.addAttribute("page", page);
			return "hys/pd/pdDrugOrderManageList";
		}
	}

	@RequiresPermissions("hys:medstoYpcgzd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpcgzd medstoYpcgzd, Model model) {
		model.addAttribute("medstoYpcgzd", medstoYpcgzd);
		return "jeesite/hys/medstoYpcgzdForm";
	}

	@RequiresPermissions("hys:medstoYpcgzd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpcgzd medstoYpcgzd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpcgzd)){
			return form(medstoYpcgzd, model);
		}
		medstoYpcgzdService.save(medstoYpcgzd);
		addMessage(redirectAttributes, "保存药库采购单成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYpcgzd/?repage";
	}
	/**
	 * 药品采购审核
	 * @param medstoYpcgzd
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "audit")
	public String audit(MedstoYpcgzd medstoYpcgzd, Model model, RedirectAttributes redirectAttributes) {
		medstoYpcgzdService.updateStatus(medstoYpcgzd);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYpcgzd/?repage";
	}
	
	/**
	 * 审核人员药品订单查询
	 * @param medstoYpcgzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hys:medstoYpcgzd:view")
	@RequestMapping(value = "viewIn")
	public String viewIn(MedstoYpcgzd medstoYpcgzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(medstoYpcgzd.getXh())){
			medstoYpcgzd = medstoYpcgzdService.getOne(medstoYpcgzd);
			MedstoYpcgmxcf medstoYpcgmxcf  = new MedstoYpcgmxcf();
			medstoYpcgmxcf.setDjh(medstoYpcgzd.getDjh());
			List<MedstoYpcgmxcf> medstoYpcgmxcfs = medstoYpcgmxcfService.findList(medstoYpcgmxcf);
			model.addAttribute("medstoYpcgzd", medstoYpcgzd);
			model.addAttribute("medstoYpcgmxcfs", medstoYpcgmxcfs);
		}
		return "hys/pd/pdDrugOrderDetail";
	}
	
	/**
	 * 审核人员药品订单详情查询
	 */
	@RequiresPermissions("hys:medstoYpcgzd:view")
	@RequestMapping(value = "pdDrugApartDetail")
	public String pdDrugApartDetail(MedstoYpcgmxcf medstoYpcgmxcf,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(medstoYpcgmxcf.getXh())){
			medstoYpcgmxcf = medstoYpcgmxcfService.getOne(medstoYpcgmxcf);
			MedstoYpcgmxcfdt medstoYpcgmxcfdt = new MedstoYpcgmxcfdt();
			medstoYpcgmxcfdt.setApartId(medstoYpcgmxcf.getXh());
			List<MedstoYpcgmxcfdt> medstoYpcgmxcfdts = medstoYpcgmxcfdtService.findList(medstoYpcgmxcfdt);
			medstoYpcgmxcf.setMedstoYpcgmxcfdts(medstoYpcgmxcfdts);
			model.addAttribute("medstoYpcgmxcf", medstoYpcgmxcf);
		}
		return "hys/pd/PdDrugApartDetail";
	}
	
	/**
	 * 药品审核
	 * @param medstoYpcgzd
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "auditDrugOrder")
	@ResponseBody
	public RspVo auditDrugOrder(MedstoYpcgzd medstoYpcgzd, Model model, RedirectAttributes redirectAttributes) {
		RspVo vo = new RspVo();
		try{
			//如果是审核
			if(MinaGlobalConstants.DRUG_ORDER_STATUS_ALREADY_ACCEPT.equals(medstoYpcgzd.getJlzt())){
				User user = UserUtils.getUser();
				//查询订单详情
				//通过时拆分订单
				MedstoYpcgmx medstoYpcgmx = new MedstoYpcgmx();
				medstoYpcgmx.setCgxh(medstoYpcgzd.getXh());
				//查询该订单所有的药品
				List<MedstoYpcgmx> medstoYpcgmxs = medstoYpcgmxService.findList(medstoYpcgmx);
				if(medstoYpcgmxs!=null && medstoYpcgmxs.size()>0){
					Map<String,List<MedstoYpcgmxcfdt>> map = new HashMap<String,List<MedstoYpcgmxcfdt>>();
					for (MedstoYpcgmx mx : medstoYpcgmxs) {
						//查找药品对应的供应商名称查找供应商id
						PdSupplier pdSupplier = new PdSupplier ();
						pdSupplier.setName(mx.getGhdwMc());
						pdSupplier = pdSupplierService.getByName(pdSupplier);
						if(pdSupplier!=null){
							String key = pdSupplier.getId();
							if(map.containsKey(key)){
								List<MedstoYpcgmxcfdt> medstoYpcgmxcfdts = map.get(key);
								MedstoYpcgmxcfdt medstoYpcgmxcfdt = new MedstoYpcgmxcfdt();
								medstoYpcgmxcfdt.setYpdm(mx.getYpdm());
								medstoYpcgmxcfdt.setCgsl(mx.getCgsl());
								medstoYpcgmxcfdt.setYpjj(Double.valueOf(mx.getYpjj()));
								medstoYpcgmxcfdts.add(medstoYpcgmxcfdt);
								map.put(key, medstoYpcgmxcfdts);
							}else{
								List<MedstoYpcgmxcfdt> medstoYpcgmxcfdts = new ArrayList<MedstoYpcgmxcfdt>();
								MedstoYpcgmxcfdt medstoYpcgmxcfdt = new MedstoYpcgmxcfdt();
								medstoYpcgmxcfdt.setYpdm(mx.getYpdm());
								medstoYpcgmxcfdt.setCgsl(mx.getCgsl());
								medstoYpcgmxcfdt.setYpjj(Double.valueOf(mx.getYpjj()));
								medstoYpcgmxcfdts.add(medstoYpcgmxcfdt);
								map.put(key, medstoYpcgmxcfdts);
							}
						}else{
							vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
							vo.setInfo("系统没找到供应商名称为:"+mx.getGhdwMc()+"的信息");
							return vo;
						}
					}
					//循环遍历map
					List<MedstoYpcgmxcf> medstoYpcgmxcfs = new ArrayList<MedstoYpcgmxcf>();
					List<MedstoYpcgmxcfdt> medstoYpcgmxcfdtList = new ArrayList<MedstoYpcgmxcfdt>();
					for(String key:map.keySet()){
						List<MedstoYpcgmxcfdt> medstoYpcgmxcfdts = map.get(key);
						MedstoYpcgmxcf medstoYpcgmxcf = new MedstoYpcgmxcf();
						medstoYpcgmxcf.setDjh(medstoYpcgzd.getDjh());
						medstoYpcgmxcf.setSupplierId(key);
						medstoYpcgmxcf.setXh(SnoGerUtil.getSerialNumber(12));
						medstoYpcgmxcf.setCreateBy(user);
						medstoYpcgmxcf.setUpdateBy(user);
						medstoYpcgmxcf.setUpdateDate(new Date());
						medstoYpcgmxcf.setCreateDate(new Date());
						int orderQuantity = 0;
						BigDecimal dosagertMoney = new BigDecimal(0);	//金额
						for(MedstoYpcgmxcfdt medstoYpcgmxcfdt:medstoYpcgmxcfdts){
							medstoYpcgmxcfdt.setApartId(medstoYpcgmxcf.getXh());
							medstoYpcgmxcfdt.setXh(SnoGerUtil.getSerialNumber(12));
							medstoYpcgmxcfdt.setCreateBy(user);
							medstoYpcgmxcfdt.setUpdateBy(user);
							medstoYpcgmxcfdt.setUpdateDate(new Date());
							medstoYpcgmxcfdt.setCreateDate(new Date());
							orderQuantity += Integer.valueOf(medstoYpcgmxcfdt.getCgsl());
							BigDecimal pdMoney = new BigDecimal(Integer.valueOf(medstoYpcgmxcfdt.getCgsl())).multiply(new BigDecimal(medstoYpcgmxcfdt.getYpjj()));
							dosagertMoney = pdMoney.add(dosagertMoney);
						}
						//获取订单量
						medstoYpcgmxcf.setOrderQuantity(String.valueOf(orderQuantity));
						medstoYpcgmxcf.setOrderAmount(dosagertMoney.doubleValue());
						medstoYpcgmxcfs.add(medstoYpcgmxcf);
						medstoYpcgmxcfdtList.addAll(medstoYpcgmxcfdts);
					}
					if(medstoYpcgmxcfs.size()>0){
						//批量添加药品订单拆分
						medstoYpcgmxcfService.batchSave(medstoYpcgmxcfs);
					}
					if(medstoYpcgmxcfdtList.size()>0){
						//批量添加药品订单拆分关联药品
						medstoYpcgmxcfdtService.batchSave(medstoYpcgmxcfdtList);
					}
					//修改状态
					int flag = medstoYpcgzdService.updateStatus(medstoYpcgzd);
					if(flag == 0)
						vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
				}
			}else{
				//修改状态
				int flag = medstoYpcgzdService.updateStatus(medstoYpcgzd);
				if(flag == 0)
					vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
			
			}
		}catch(Exception e){
			e.printStackTrace();
			vo.setCode(MinaMsgCode.OPERATOR_ERROR.getKey());
		}
		return vo;
	}
	
	
	@RequiresPermissions("hys:medstoYpcgzd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpcgzd medstoYpcgzd, RedirectAttributes redirectAttributes) {
		medstoYpcgzdService.delete(medstoYpcgzd);
		addMessage(redirectAttributes, "删除药库采购单成功");
		return "redirect:"+Global.getAdminPath()+"/hys/medstoYpcgzd/?repage";
	}

	/**********************************************************************
	 * 
	 **************************统计查询（药品订单）**************************
	 *
	 **********************************************************************/
	@RequestMapping(value = "drugOrderStatistic")
	public String drugOrderStatistic(MedstoYpcgzd medstoYpcgzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String supplierId = user.getSupplierId();
		//供应商用户
		if (!StringUtils.isEmpty(supplierId)) {
			/*medstoYpcgzd.setGhdwId(supplierId);
			List<String> stats = new ArrayList<String>();
			stats.add(MinaGlobalConstants.DRUG_ORDER_STATUS_ALREADY_ACCEPT);
			stats.add(MinaGlobalConstants.DRUG_ORDER_STATUS_ALREADY_INROOM);
			medstoYpcgzd.setStatusList(stats);*/
		}
		Page<MedstoYpcgzd> page = medstoYpcgzdService.findPage(new Page<MedstoYpcgzd>(request, response), medstoYpcgzd); 
		model.addAttribute("page", page);
		return "hys/pd/pdDrugOrderStatisticList";
	}
}