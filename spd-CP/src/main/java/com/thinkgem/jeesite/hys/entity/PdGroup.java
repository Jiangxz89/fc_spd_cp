/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 耗材组别表Entity
 * @author sutianqi
 * @version 2018-07-04
 */
public class PdGroup extends DataEntity<PdGroup> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String hospitalNumber;//医院代码
	
	public PdGroup() {
		super();
	}

	public PdGroup(String id){
		super(id);
	}

	@Length(min=0, max=100, message="name长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}
}