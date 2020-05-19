/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 耗材订单及配送查询表Entity
 * @author zhangxiaohan
 * @version 2018-07-12
 */
public class PdConsumablesOrderTime extends DataEntity<PdConsumablesOrderTime> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 订单编号
	private String productNumber;		// 产品编号
	private Integer orderQuantity;		// 订货量
	private Integer psQuantity;		// 配送量
	private Double price;		// 单价
	private Double amount;		// 金额
	private String hospital;		// 医院编号
	private Date syncDate;		// 同步时间
	private String checkTime; //入库审核时间
	
	//冗余
	private String manufacturer;//生产厂家
	private Date orderDate;//订单日期
	private String orderState;// 订单状态
	private String productName;//产品名称
	private String supplierId;//供应商Id
	private String supplierName;//供应商名称
	private String spec;//规格
	private String unit; //单位
	private String venderName; //生产厂家
	private String hospitalName;//医院名称
	private Double totalAmount;//配送总金额
	
	public PdConsumablesOrderTime() {
		super();
	}

	public PdConsumablesOrderTime(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单编号长度必须介于 0 和 64 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=100, message="产品编号长度必须介于 0 和 100 之间")
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	@Length(min=0, max=11, message="订货量长度必须介于 0 和 11 之间")
	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	@Length(min=0, max=11, message="配送量长度必须介于 0 和 11 之间")
	public Integer getPsQuantity() {
		return psQuantity;
	}

	public void setPsQuantity(Integer psQuantity) {
		this.psQuantity = psQuantity;
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

	@Length(min=0, max=64, message="医院名称长度必须介于 0 和 64 之间")
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
}