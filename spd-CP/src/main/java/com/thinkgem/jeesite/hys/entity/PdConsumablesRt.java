/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 耗材退货查询表Entity
 * 
 * @author zhangxiaohan
 * @version 2018-07-17
 */
public class PdConsumablesRt extends DataEntity<PdConsumablesRt> {

	private static final long serialVersionUID = 1L;
	private Date rtDate; // 退货日期
	private String number; // 订单编号
	private String rtNumber; // 退单编号
	private String productNumber; // 产品编码
	private Integer orderQuantity; // 订货量
	private Integer rtQuantity; // 退货量
	private Double price; // 单价
	private Double amount; // 金额
	private String hospital; // 医院编号
	private Date syncDate; // 同步时间

	// 冗余
	private String productName;//产品名称
	private String manufacturer;//生产厂家
	private String spec;//规格
	private String unit; //单位
	private String hospitalName;//医院名称
	private String supplierName;//供应商名称

	public PdConsumablesRt() {
		super();
	}

	public PdConsumablesRt(String id) {
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRtDate() {
		return rtDate;
	}

	public void setRtDate(Date rtDate) {
		this.rtDate = rtDate;
	}

	@Length(min = 0, max = 100, message = "订单编号长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Length(min = 0, max = 100, message = "退单编号长度必须介于 0 和 100 之间")
	public String getRtNumber() {
		return rtNumber;
	}

	public void setRtNumber(String rtNumber) {
		this.rtNumber = rtNumber;
	}

	@Length(min = 0, max = 100, message = "产品编码长度必须介于 0 和 100 之间")
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Integer getRtQuantity() {
		return rtQuantity;
	}

	public void setRtQuantity(Integer rtQuantity) {
		this.rtQuantity = rtQuantity;
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

	@Length(min = 0, max = 100, message = "医院编号长度必须介于 0 和 100 之间")
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
}