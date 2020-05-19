/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商医院关联表Entity
 * @author sutianqi
 * @version 2018-07-04
 */
public class PdSupplierMidHospital extends DataEntity<PdSupplierMidHospital> {
	
	private static final long serialVersionUID = 1L;
	private String supplierId;		// supplier_id
	private String hospitalId;		// hospital_id
	private String midCode;			//关联代码
	
	private String hospitalName;//医院名称
	private String hospitalNumber;//医院编号
	private String supplierName;//供应商名称
	private String supplierNumber;//供应商编号
	
	public PdSupplierMidHospital() {
		super();
	}

	public PdSupplierMidHospital(String id){
		super(id);
	}

	@Length(min=0, max=64, message="supplier_id长度必须介于 0 和 64 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=64, message="hospital_id长度必须介于 0 和 64 之间")
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getMidCode() {
		return midCode;
	}

	public void setMidCode(String midCode) {
		this.midCode = midCode;
	}

	
	
}