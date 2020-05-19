/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 耗材订单拆分关联耗材表Entity
 * @author zxh
 * @version 2018-07-24
 */
public class PdConsumablesApartRelevance extends DataEntity<PdConsumablesApartRelevance> {
	
	private static final long serialVersionUID = 1L;
	private String apartId;		// 拆分表id
	private String consumablesNumber;		// 耗材编号
	private Integer orderQuantity; //订货量
	private Double price; //单价
	private Double amount; //金额
	
	//冗余
	private String name;//耗材名称
	private String spec;//规格
	private String unit; //单位
	private String venderName; //生产厂家
	private String hospital;
	
	public PdConsumablesApartRelevance() {
		super();
	}

	public PdConsumablesApartRelevance(String id){
		super(id);
	}

	@Length(min=0, max=64, message="拆分表id长度必须介于 0 和 64 之间")
	public String getApartId() {
		return apartId;
	}

	public void setApartId(String apartId) {
		this.apartId = apartId;
	}
	
	@Length(min=0, max=100, message="耗材编号长度必须介于 0 和 100 之间")
	public String getConsumablesNumber() {
		return consumablesNumber;
	}

	public void setConsumablesNumber(String consumablesNumber) {
		this.consumablesNumber = consumablesNumber;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
}