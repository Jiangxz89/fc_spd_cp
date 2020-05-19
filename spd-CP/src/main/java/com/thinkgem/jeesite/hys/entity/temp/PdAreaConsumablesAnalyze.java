package com.thinkgem.jeesite.hys.entity.temp;

/**
 * 区域采购金额情况分析
 * @author sutianqi
 * @version 2018-07-23
 */
public class PdAreaConsumablesAnalyze {

	private String hospitalName;
	private Double money;	//金额
	private Integer type;	//种类
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
