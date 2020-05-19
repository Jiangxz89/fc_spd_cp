/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 供应商产品关联表Entity
 * @author sutianqi
 * @version 2018-07-04
 */
public class PdSupplierMidProduct extends DataEntity<PdSupplierMidProduct> {

	private static final long serialVersionUID = 1L;
	private String supplierId;		// supplier_id
	private String productId;		// product_id

	private String productName;//产品名称
	private String productNumber;//产品编号
	private String supplierName;//供应商名称
	private String supplierNumber;//供应商编号

	public PdSupplierMidProduct() {
		super();
	}

	public PdSupplierMidProduct(String id){
		super(id);
	}

	@Length(min=0, max=64, message="supplier_id长度必须介于 0 和 64 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=64, message="product_id长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
}