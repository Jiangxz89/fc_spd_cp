/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity.temp;


import java.util.Date;
import java.util.List;
import com.thinkgem.jeesite.hys.entity.PdSupplier;
import com.thinkgem.jeesite.hys.utils.CommonUtils;

/**
 * 耗材汇总统计实例
 * @author sutianqi
 * @version 2018-07-23
 */
public class PdProductGather{
	
	private Date beginTime;	//
	private Date endTime;	//
	
	private List<PdConsumablesRank> pdConsumablesRankList;	//
	
	private PdSupplier pdSupplier;	//
	private List<PdSupplierDelivery> pdSupplierDeliveryList;//
	
	private String areaProvince;	//
	private String areaCity;	//
	private String areaTown;	//
	List<PdAreaConsumablesAnalyze> pdAreaConsumablesAnalyzeList;	//
	
	
	
	
	
	
	public PdProductGather() {//初始化时间
		super();
		beginTime = CommonUtils.getLastMonthFirstDay();
		endTime = CommonUtils.getLastMonthLastDay();
	}
	
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<PdConsumablesRank> getPdConsumablesRankList() {
		return pdConsumablesRankList;
	}
	public void setPdConsumablesRankList(
			List<PdConsumablesRank> pdConsumablesRankList) {
		this.pdConsumablesRankList = pdConsumablesRankList;
	}
	public PdSupplier getPdSupplier() {
		return pdSupplier;
	}
	public void setPdSupplier(PdSupplier pdSupplier) {
		this.pdSupplier = pdSupplier;
	}
	public List<PdSupplierDelivery> getPdSupplierDeliveryList() {
		return pdSupplierDeliveryList;
	}
	public void setPdSupplierDeliveryList(
			List<PdSupplierDelivery> pdSupplierDeliveryList) {
		this.pdSupplierDeliveryList = pdSupplierDeliveryList;
	}
	public String getAreaProvince() {
		return areaProvince;
	}
	public void setAreaProvince(String areaProvince) {
		this.areaProvince = areaProvince;
	}
	public String getAreaCity() {
		return areaCity;
	}
	public void setAreaCity(String areaCity) {
		this.areaCity = areaCity;
	}
	public String getAreaTown() {
		return areaTown;
	}
	public void setAreaTown(String areaTown) {
		this.areaTown = areaTown;
	}
	public List<PdAreaConsumablesAnalyze> getPdAreaConsumablesAnalyzeList() {
		return pdAreaConsumablesAnalyzeList;
	}
	public void setPdAreaConsumablesAnalyzeList(
			List<PdAreaConsumablesAnalyze> pdAreaConsumablesAnalyzeList) {
		this.pdAreaConsumablesAnalyzeList = pdAreaConsumablesAnalyzeList;
	}
	
	
	
	
}