/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 医院表Entity
 * @author sutianqi
 * @version 2018-07-13
 */
public class PdHospital extends DataEntity<PdHospital> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// number
	@ExcelField(title = "医院名称",  sort = 1)
	private String name;		// name
	private String areaCountry;		// area_country
	@ExcelField(title = "省",  sort = 2)
	private String areaProvince;		// area_province
	@ExcelField(title = "市",  sort = 3)
	private String areaCity;		// area_city
	@ExcelField(title = "区/县",  sort = 4)
	private String areaTown;		// area_town
	@ExcelField(title = "详细地址",  sort = 5)
	private String address;		// address
	@ExcelField(title = "联系人",  sort = 6)
	private String person;		// person
	@ExcelField(title = "联系电话",  sort = 7)
	private String tel;		// tel
	@ExcelField(title = "联系人邮箱",  sort = 8)
	private String mail;		// mail
	
	//--temp
	private String midCode;
	private String midId;
	
	//冗余
	private String totalAmount;//总金额  耗材汇总统计时使用
	private String speciesSize;//种类个数
	
	
	public String getMidId() {
		return midId;
	}

	public void setMidId(String midId) {
		this.midId = midId;
	}

	public String getMidCode() {
		return midCode;
	}

	public void setMidCode(String midCode) {
		this.midCode = midCode;
	}

	public PdHospital() {
		super();
	}

	public PdHospital(String id){
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
	
	@Length(min=0, max=100, message="area_country长度必须介于 0 和 100 之间")
	public String getAreaCountry() {
		return areaCountry;
	}

	public void setAreaCountry(String areaCountry) {
		this.areaCountry = areaCountry;
	}
	
	@Length(min=0, max=100, message="area_province长度必须介于 0 和 100 之间")
	public String getAreaProvince() {
		return areaProvince;
	}

	public void setAreaProvince(String areaProvince) {
		this.areaProvince = areaProvince;
	}
	
	@Length(min=0, max=100, message="area_city长度必须介于 0 和 100 之间")
	public String getAreaCity() {
		return areaCity;
	}

	public void setAreaCity(String areaCity) {
		this.areaCity = areaCity;
	}
	
	@Length(min=0, max=100, message="area_town长度必须介于 0 和 100 之间")
	public String getAreaTown() {
		return areaTown;
	}

	public void setAreaTown(String areaTown) {
		this.areaTown = areaTown;
	}
	
	@Length(min=0, max=200, message="address长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="person长度必须介于 0 和 100 之间")
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	@Length(min=0, max=100, message="tel长度必须介于 0 和 100 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=100, message="mail长度必须介于 0 和 100 之间")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSpeciesSize() {
		return speciesSize;
	}

	public void setSpeciesSize(String speciesSize) {
		this.speciesSize = speciesSize;
	}
	
	
}