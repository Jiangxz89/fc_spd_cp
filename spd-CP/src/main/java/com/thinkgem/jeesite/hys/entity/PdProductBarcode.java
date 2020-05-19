/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 条形码表Entity
 * @author sutianqi
 * @version 2018-07-04
 */
public class PdProductBarcode extends DataEntity<PdProductBarcode> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// number
	private String site;		// site
	
	public PdProductBarcode() {
		super();
	}

	public PdProductBarcode(String id){
		super(id);
	}

	@Length(min=0, max=200, message="number长度必须介于 0 和 200 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=200, message="site长度必须介于 0 和 200 之间")
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
}