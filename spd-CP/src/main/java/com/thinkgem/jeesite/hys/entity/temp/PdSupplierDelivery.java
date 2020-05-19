package com.thinkgem.jeesite.hys.entity.temp;

import com.thinkgem.jeesite.hys.entity.PdHospital;
import com.thinkgem.jeesite.hys.entity.PdSupplier;

/**
 * 企业配送金额比率
 * @author sutianqi
 * @version 2018-07-23
 */
public class PdSupplierDelivery {

	private String hospitalName;
	private Double money;
	
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
	
	
}
