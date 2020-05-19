/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity.temp;


import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.hys.entity.PdHospital;
import com.thinkgem.jeesite.hys.entity.PdSupplier;

/**
 * 药材汇总统计实例
 * @author sutianqi
 * @version 2018-07-23
 */
public class PdDrugGather{
	
	private Date BeginTime;	//
	private Date EndTime;	//
	
	private List<PdConsumablesRank> pdConsumablesRankList;	//
	
	private PdSupplier pdSupplier;	//
	private List<PdSupplierDelivery> pdSupplierDeliveryList;//
	
	private PdHospital pdHospital;//	
	private int jyCount;	//
	private int fjyCount;	//
	
	private String areaProvince;	//
	private String areaCity;	//
	private String areaTown;	//
	List<PdAreaConsumablesAnalyze> pdAreaConsumablesAnalyzeList;	//
	public Date getBeginTime() {
		return BeginTime;
	}
	public void setBeginTime(Date beginTime) {
		BeginTime = beginTime;
	}
	public Date getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime;
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
	public PdHospital getPdHospital() {
		return pdHospital;
	}
	public void setPdHospital(PdHospital pdHospital) {
		this.pdHospital = pdHospital;
	}
	public int getJyCount() {
		return jyCount;
	}
	public void setJyCount(int jyCount) {
		this.jyCount = jyCount;
	}
	public int getFjyCount() {
		return fjyCount;
	}
	public void setFjyCount(int fjyCount) {
		this.fjyCount = fjyCount;
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