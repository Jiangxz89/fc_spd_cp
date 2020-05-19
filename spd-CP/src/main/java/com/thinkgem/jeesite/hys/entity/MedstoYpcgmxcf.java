/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品订单拆分表Entity
 * @author zxh
 * @version 2018-10-10
 */
public class MedstoYpcgmxcf extends DataEntity<MedstoYpcgmxcf> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 主键 : 主键
	private String djh;		// 订单编号 : 订单编号
	private String orderQuantity;		// 订货量 : 订货量
	private Double orderAmount;		// 订单金额 : 订单金额
	private String supplierId;		// 供应商id : 供应商id
	private String supplierName;   //供应商名称
	private List<MedstoYpcgmxcfdt> medstoYpcgmxcfdts; //订单详情
	private String cjrq;//订单日期
	private String jlzt;//订单状态
	private String hospitalName; //下单医院
	private String hospitalCode; //下单医院代号
	public MedstoYpcgmxcf() {
		super();
	}

	public MedstoYpcgmxcf(String id){
		super(id);
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=0, max=18, message="订单编号 : 订单编号长度必须介于 0 和 18 之间")
	public String getDjh() {
		return djh;
	}

	public void setDjh(String djh) {
		this.djh = djh;
	}
	
	@Length(min=0, max=11, message="订货量 : 订货量长度必须介于 0 和 11 之间")
	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	
	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Length(min=0, max=64, message="供应商id : 供应商id长度必须介于 0 和 64 之间")
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

	public List<MedstoYpcgmxcfdt> getMedstoYpcgmxcfdts() {
		return medstoYpcgmxcfdts;
	}

	public void setMedstoYpcgmxcfdts(List<MedstoYpcgmxcfdt> medstoYpcgmxcfdts) {
		this.medstoYpcgmxcfdts = medstoYpcgmxcfdts;
	}

	public String getCjrq() {
		return cjrq;
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	
	
}