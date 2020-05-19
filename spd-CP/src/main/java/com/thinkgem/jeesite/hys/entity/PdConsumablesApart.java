/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 耗材订单拆分表Entity
 * @author zxh
 * @version 2018-07-24
 */
public class PdConsumablesApart extends DataEntity<PdConsumablesApart> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 订单编号
	private Integer orderQuantity;		// 订货量
	private Double orderAmount;		// 订单金额
	private String supplierId;		// 供应商id
	private String hospital; //医院编号
	private List<PdConsumablesApartRelevance> pdConsumablesApartRelevances;
	
	//冗余
	private String supplierName; //供应商名称
	private Date orderDate; //订单时间
	private String orderState; //订单状态
	private String hospitalName; //医院
	
	public PdConsumablesApart() {
		super();
	}

	public PdConsumablesApart(String id){
		super(id);
	}

	@Length(min=0, max=100, message="订单编号长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=11, message="订货量长度必须介于 0 和 11 之间")
	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	
	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Length(min=0, max=64, message="供应商id长度必须介于 0 和 64 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public List<PdConsumablesApartRelevance> getPdConsumablesApartRelevances() {
		return pdConsumablesApartRelevances;
	}

	public void setPdConsumablesApartRelevances(List<PdConsumablesApartRelevance> pdConsumablesApartRelevances) {
		this.pdConsumablesApartRelevances = pdConsumablesApartRelevances;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
}