/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 供应商表Entity
 * @author sutianqi
 * @version 2018-07-04
 */
public class PdSupplier extends DataEntity<PdSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// number
	@ExcelField(title = "供应商名称",  sort = 1)
	private String name;		// name
	@ExcelField(title = "登录账号",  sort = 6)
	private String account;		//账号
	private String code;		// code
	@ExcelField(title = "联系人",  sort = 2)
	private String person;		// person
	@ExcelField(title = "联系电话",  sort = 3)
	private String tel;		// tel
	@ExcelField(title = "联系人邮箱",  sort = 4)
	private String mail;		// mail
	@ExcelField(title = "联系人地址",  sort = 5)
	private String address;		// address
	private String loginName;
	
	private String oldLoginName;//曾用账号

	private String hospital;
	
	//----temp
	private List<PdHospital> hospitalList;

	private List<PdProduct> pdProductList;

	private String productId;// add by jiangxz 20191104 只做sql查询用，本字段不处理业务逻辑
	
	public PdSupplier() {
		super();
	}

	public PdSupplier(String id){
		super(id);
	}

	@Length(min=0, max=100, message="number长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=100, message="name长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="code长度必须介于 0 和 200 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=100, message="person长度必须介于 0 和 100 之间")
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	@Length(min=0, max=199, message="tel长度必须介于 0 和 199 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=200, message="mail长度必须介于 0 和 200 之间")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Length(min=0, max=200, message="address长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PdHospital> getHospitalList() {
		return hospitalList;
	}

	public void setHospitalList(List<PdHospital> hospitalList) {
		this.hospitalList = hospitalList;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public List<PdProduct> getPdProductList() {
		return pdProductList;
	}

	public void setPdProductList(List<PdProduct> pdProductList) {
		this.pdProductList = pdProductList;
	}
	/**
	 * add by jiangxz 20191104 只做sql查询用，本字段不处理业务逻辑
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * add by jiangxz 20191104 只做sql查询用，本字段不处理业务逻辑
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
}