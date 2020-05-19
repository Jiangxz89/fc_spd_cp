/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 耗材分类表Entity
 * @author sutianqi
 * @version 2018-07-04
 */
public class PdCategory extends DataEntity<PdCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String type;		// 词典：低值耗材为 0高值耗材为 1
	private String hospitalNumber;//医院代码

	public PdCategory() {
		super();
	}

	public PdCategory(String id){
		super(id);
	}

	@Length(min=0, max=100, message="name长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="词典：低值耗材为 0高值耗材为 1长度必须介于 0 和 50 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}
}