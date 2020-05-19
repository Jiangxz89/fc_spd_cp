/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity.temp;


import com.thinkgem.jeesite.hys.entity.PdHospital;

/**
 * 采购金额排名
 * @author sutianqi
 * @version 2018-07-23
 */
public class PdConsumablesRank {
	
	private String hosName;
	private Double consumables;
	private int rank;
	
	
	public String getHosName() {
		return hosName;
	}
	public void setHosName(String hosName) {
		this.hosName = hosName;
	}
	public Double getConsumables() {
		return consumables;
	}
	public void setConsumables(Double consumables) {
		this.consumables = consumables;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
}