/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.hys.entity.PdConsumablesApart;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrder;
import com.thinkgem.jeesite.hys.entity.PdHospital;
import com.thinkgem.jeesite.hys.entity.PdSupplier;
import com.thinkgem.jeesite.hys.entity.temp.PdAreaConsumablesAnalyze;
import com.thinkgem.jeesite.hys.entity.temp.PdConsumablesRank;
import com.thinkgem.jeesite.hys.entity.temp.PdProductGather;
import com.thinkgem.jeesite.hys.entity.temp.PdSupplierDelivery;
import com.thinkgem.jeesite.hys.dao.PdConsumablesApartDao;
import com.thinkgem.jeesite.hys.dao.PdConsumablesOrderDao;
import com.thinkgem.jeesite.hys.dao.PdConsumablesOrderDetailDao;
import com.thinkgem.jeesite.hys.dao.PdHospitalDao;
import com.thinkgem.jeesite.hys.dao.PdSupplierDao;

/**
 * 耗材汇总统计
 * @author sutianqi
 * @version 2018-07-23
 */
@Service
@Transactional(readOnly = true)
public class PdProductGatherService{
	private PdProductGather gather;
	private PdConsumablesOrderDao pdConsumablesOrderDao = SpringContextHolder.getBean(PdConsumablesOrderDao.class);
	private PdSupplierDao pdSupplierDao = SpringContextHolder.getBean(PdSupplierDao.class);
	private PdHospitalDao pdHospitalDao = SpringContextHolder.getBean(PdHospitalDao.class);
	private PdConsumablesOrderDetailDao pdConsumablesOrderDetailDao = SpringContextHolder.getBean(PdConsumablesOrderDetailDao.class);
	private PdConsumablesApartDao pdConsumablesApartDao = SpringContextHolder.getBean(PdConsumablesApartDao.class);
	
	/**
	 * 初始化统计
	 * */
	public PdProductGather init(PdProductGather pdProductGather){
		gather = pdProductGather;
		//初始化时间
		consumablesRank(gather);//调用
		
		//默认供应商
		PdSupplier pdSupplier = pdSupplierDao.get("7e977f82b78349888a36301813ab2734");	//国药供应商id
		gather.setPdSupplier(pdSupplier);
		supplierDelivery(gather);//调用
		
		//默认地区
		gather.setAreaProvince("1037100100000001363");	//江西省编码
		gather.setAreaCity("1037100100000001459");		//抚州市编码
		gather.setAreaTown("1037100100000001470");		//东乡县编码
		areaConsumablesAnalyze(gather);
		
		
		return gather;
	}
	
	/**
	 * 计算采购金额排名
	 * */
	public PdProductGather consumablesRank(PdProductGather pdProductGather){
		PdConsumablesOrder co = new PdConsumablesOrder();
		
		co.setStartDate(pdProductGather.getBeginTime());
		co.setEndDate(pdProductGather.getEndTime());
		
		List<PdConsumablesOrder> findListByDate = pdConsumablesOrderDao.findListByDate(co);
		
		List<PdConsumablesRank> hosNumList = new ArrayList<PdConsumablesRank>();	//金额排名结果的最终容器
		
		for(PdConsumablesOrder pco : findListByDate){	//循环指定时间内的所有订单记录
			String hospitalName = pco.getHospitalName();
			Double orderAmount = pco.getOrderAmount();
			int key = 0;	//是否重复标识
			for(int i = 0 ; i < hosNumList.size() ; i ++){	//循环结果容器，如医院名称相同，则金额增加并跳出循环
				String hosName = hosNumList.get(i).getHosName();
				
				if(hosName.equals(hospitalName)){
					Double d1 = hosNumList.get(i).getConsumables();
					hosNumList.get(i).setConsumables(d1+orderAmount);
					key = 1;
					break;
				}
			}
			
			if(key == 0){//如果容器不存在医院，则实例化并添加进容器
				PdConsumablesRank cr = new PdConsumablesRank();
				cr.setHosName(hospitalName);
				cr.setConsumables(orderAmount);
				hosNumList.add(cr);
			}else{
				key = 0;	//重置标识
			}
			
		}
		pdProductGather.setPdConsumablesRankList(hosNumList);	//添加进汇总对象
		return pdProductGather;
	}
	
	/**
	 * 计算企业配送金额比率
	 * */
	public PdProductGather supplierDelivery(PdProductGather pdProductGather){
		PdConsumablesApart apart = new PdConsumablesApart();
		apart.setSupplierId(pdProductGather.getPdSupplier().getId());
		List<PdConsumablesApart> caList = pdConsumablesApartDao.findList(apart);
		
		List<PdSupplierDelivery> delList = new ArrayList<PdSupplierDelivery>();//企业配送金额比率最终容器

		for(int i = 0 ; i < caList.size() ; i ++){
			String number = caList.get(i).getNumber();
			PdConsumablesOrder co = new PdConsumablesOrder();
			co.setNumber(number);
			PdConsumablesOrder byNumber = pdConsumablesOrderDao.getByNumber(co);
			
			int key = 0 ; //循环辅助标记
			String hosName = byNumber.getHospitalName();
			Double money = caList.get(i).getOrderAmount();
			PdSupplierDelivery delivery = new PdSupplierDelivery();
			delivery.setHospitalName(hosName);
			delivery.setMoney(money);
			for(PdSupplierDelivery sd : delList){//校验是否是同家医院，如果是，则金额增加终止循环
				if(sd.getHospitalName().equals(hosName)){
					Double m = sd.getMoney();
					sd.setMoney(m+money);
					key = 1;
					break;
				}
			}
			if(key == 0){
				delList.add(delivery);
			}
		}
		
		pdProductGather.setPdSupplierDeliveryList(delList);//添加进汇总对象
		return pdProductGather;
	}
	
	/**
	 * 区域采购金额情况分析
	 * */
	public PdProductGather areaConsumablesAnalyze(PdProductGather pdProductGather){
		PdHospital hos = new PdHospital();
		hos.setAreaProvince(pdProductGather.getAreaProvince());
		hos.setAreaCity(pdProductGather.getAreaCity());
		hos.setAreaTown(pdProductGather.getAreaTown());
		List<PdHospital> hosList = pdHospitalDao.findList(hos);	//指定区域所有医院
		List<PdAreaConsumablesAnalyze> analyzeList = new ArrayList<PdAreaConsumablesAnalyze>();//区域采购的最终容器
		
		
		PdConsumablesOrder co = new PdConsumablesOrder();
		co.setStartDate(pdProductGather.getBeginTime());
		co.setEndDate(pdProductGather.getEndTime());
		
		for(int i = 0 ; i < hosList.size() ; i ++){
			String number = hosList.get(i).getNumber();
			co.setHospital(number);
			Double money = 0D;
			String hosName = "";
			int type = 0;
			List<PdConsumablesOrder> findListByDate = pdConsumablesOrderDao.findListByDate(co);//该医院在指定时间下的所有订单
			for(PdConsumablesOrder po : findListByDate){
				hosName = po.getHospitalName();
				money += po.getOrderAmount();
				type = pdConsumablesOrderDetailDao.findOrderKindSize(po);
				
			}
			if(!hosName.equals("")){
				PdAreaConsumablesAnalyze analyze = new PdAreaConsumablesAnalyze();
				analyze.setHospitalName(hosName);
				analyze.setMoney(money);
				analyze.setType(type);
				analyzeList.add(analyze);
			}
		}
		
		pdProductGather.setPdAreaConsumablesAnalyzeList(analyzeList);
		return pdProductGather;
	}
	
	
}