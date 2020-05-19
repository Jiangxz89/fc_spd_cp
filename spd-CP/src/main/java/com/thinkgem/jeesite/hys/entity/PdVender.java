/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 生产厂家表Entity
 * @author sutianqi
 * @version 2018-07-04
 */
public class PdVender extends DataEntity<PdVender> {
	
	private static final long serialVersionUID = 1L;
	@ExcelField(title = "生产厂家名称",  sort = 1)
	private String name;		// name
	private String pinyin;   //拼音简码
	private String hospitalNumber;//医院代码
	private String imgLicense1Site;		// img_license1_site
	private String imgLicense1Name;		// img_license1_name
	private String imgLicense1Num;		// img_license1_num
	private Date imgLicense1Date;		// img_license1_date
	private String imgLicense2Site;		// img_license2_site
	private String imgLicense2Name;		// img_license2_name
	private String imgLicense2Num;		// img_license2_num
	private Date imgLicense2Date;		// img_license2_date
	private String imgLicense3Site;		// img_license3_site
	private String imgLicense3Name;		// img_license3_name
	private String imgLicense3Num;		// img_license3_num
	private Date imgLicense3Date;		// img_license3_date
	private String imgLicense4Site;		// img_license4_site
	private String imgLicense4Name;		// img_license4_name
	private String imgLicense4Num;		// img_license4_num
	private Date imgLicense4Date;		// img_license4_date
	private String imgLicense5Site;		// img_license5_site
	private String imgLicense5Name;		// img_license5_name
	private String imgLicense5Num;		// img_license5_num
	private Date imgLicense5Date;		// img_license5_date
	private String imgLicense6Site;		// img_license6_site
	private String imgLicense6Name;		// img_license6_name
	private String imgLicense6Num;		// img_license6_num
	private Date imgLicense6Date;		// img_license6_date
	private String imgLicense7Site;		// img_license7_site
	private String imgLicense7Name;		// img_license7_name
	private String imgLicense7Num;		// img_license7_num
	private Date imgLicense7Date;		// img_license7_date
	private String imgLicense8Site;		// img_license8_site
	private String imgLicense8Name;		// img_license8_name
	private String imgLicense8Num;		// img_license8_num
	private Date imgLicense8Date;		// img_license8_date
	private String imgLicense9Site;		// img_license9_site
	private String imgLicense9Name;		// img_license9_name
	private String imgLicense9Num;		// img_license9_num
	private Date imgLicense9Date;		// img_license9_date
	private String imgLicense10Site;		// img_license10_site
	private String imgLicense10Name;		// img_license10_name
	private String imgLicense10Num;		// img_license10_num
	private Date imgLicense10Date;		// img_license10_date
	private String imgLicense11Site;		// img_license11_site
	private String imgLicense11Name;		// img_license11_name
	private String imgLicense11Num;		// img_license11_num
	private Date imgLicense11Date;		// img_license11_date
	private String imgLicense12Site;		// img_license12_site
	private String imgLicense12Name;		// img_license12_name
	private String imgLicense12Num;		// img_license12_num
	private Date imgLicense12Date;		// img_license12_date
	
	public PdVender() {
		super();
	}

	public PdVender(String id){
		super(id);
	}

	@Length(min=0, max=100, message="name长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Length(min=0, max=200, message="img_license1_site长度必须介于 0 和 200 之间")
	public String getImgLicense1Site() {
		return imgLicense1Site;
	}

	public void setImgLicense1Site(String imgLicense1Site) {
		this.imgLicense1Site = imgLicense1Site;
	}
	
	@Length(min=0, max=100, message="img_license1_name长度必须介于 0 和 100 之间")
	public String getImgLicense1Name() {
		return imgLicense1Name;
	}

	public void setImgLicense1Name(String imgLicense1Name) {
		this.imgLicense1Name = imgLicense1Name;
	}
	
	@Length(min=0, max=100, message="img_license1_num长度必须介于 0 和 100 之间")
	public String getImgLicense1Num() {
		return imgLicense1Num;
	}

	public void setImgLicense1Num(String imgLicense1Num) {
		this.imgLicense1Num = imgLicense1Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense1Date() {
		return imgLicense1Date;
	}

	public void setImgLicense1Date(Date imgLicense1Date) {
		this.imgLicense1Date = imgLicense1Date;
	}
	
	@Length(min=0, max=200, message="img_license2_site长度必须介于 0 和 200 之间")
	public String getImgLicense2Site() {
		return imgLicense2Site;
	}

	public void setImgLicense2Site(String imgLicense2Site) {
		this.imgLicense2Site = imgLicense2Site;
	}
	
	@Length(min=0, max=100, message="img_license2_name长度必须介于 0 和 100 之间")
	public String getImgLicense2Name() {
		return imgLicense2Name;
	}

	public void setImgLicense2Name(String imgLicense2Name) {
		this.imgLicense2Name = imgLicense2Name;
	}
	
	@Length(min=0, max=100, message="img_license2_num长度必须介于 0 和 100 之间")
	public String getImgLicense2Num() {
		return imgLicense2Num;
	}

	public void setImgLicense2Num(String imgLicense2Num) {
		this.imgLicense2Num = imgLicense2Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense2Date() {
		return imgLicense2Date;
	}

	public void setImgLicense2Date(Date imgLicense2Date) {
		this.imgLicense2Date = imgLicense2Date;
	}
	
	@Length(min=0, max=200, message="img_license3_site长度必须介于 0 和 200 之间")
	public String getImgLicense3Site() {
		return imgLicense3Site;
	}

	public void setImgLicense3Site(String imgLicense3Site) {
		this.imgLicense3Site = imgLicense3Site;
	}
	
	@Length(min=0, max=100, message="img_license3_name长度必须介于 0 和 100 之间")
	public String getImgLicense3Name() {
		return imgLicense3Name;
	}

	public void setImgLicense3Name(String imgLicense3Name) {
		this.imgLicense3Name = imgLicense3Name;
	}
	
	@Length(min=0, max=100, message="img_license3_num长度必须介于 0 和 100 之间")
	public String getImgLicense3Num() {
		return imgLicense3Num;
	}

	public void setImgLicense3Num(String imgLicense3Num) {
		this.imgLicense3Num = imgLicense3Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense3Date() {
		return imgLicense3Date;
	}

	public void setImgLicense3Date(Date imgLicense3Date) {
		this.imgLicense3Date = imgLicense3Date;
	}
	
	@Length(min=0, max=200, message="img_license4_site长度必须介于 0 和 200 之间")
	public String getImgLicense4Site() {
		return imgLicense4Site;
	}

	public void setImgLicense4Site(String imgLicense4Site) {
		this.imgLicense4Site = imgLicense4Site;
	}
	
	@Length(min=0, max=100, message="img_license4_name长度必须介于 0 和 100 之间")
	public String getImgLicense4Name() {
		return imgLicense4Name;
	}

	public void setImgLicense4Name(String imgLicense4Name) {
		this.imgLicense4Name = imgLicense4Name;
	}
	
	@Length(min=0, max=100, message="img_license4_num长度必须介于 0 和 100 之间")
	public String getImgLicense4Num() {
		return imgLicense4Num;
	}

	public void setImgLicense4Num(String imgLicense4Num) {
		this.imgLicense4Num = imgLicense4Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense4Date() {
		return imgLicense4Date;
	}

	public void setImgLicense4Date(Date imgLicense4Date) {
		this.imgLicense4Date = imgLicense4Date;
	}
	
	@Length(min=0, max=200, message="img_license5_site长度必须介于 0 和 200 之间")
	public String getImgLicense5Site() {
		return imgLicense5Site;
	}

	public void setImgLicense5Site(String imgLicense5Site) {
		this.imgLicense5Site = imgLicense5Site;
	}
	
	@Length(min=0, max=100, message="img_license5_name长度必须介于 0 和 100 之间")
	public String getImgLicense5Name() {
		return imgLicense5Name;
	}

	public void setImgLicense5Name(String imgLicense5Name) {
		this.imgLicense5Name = imgLicense5Name;
	}
	
	@Length(min=0, max=100, message="img_license5_num长度必须介于 0 和 100 之间")
	public String getImgLicense5Num() {
		return imgLicense5Num;
	}

	public void setImgLicense5Num(String imgLicense5Num) {
		this.imgLicense5Num = imgLicense5Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense5Date() {
		return imgLicense5Date;
	}

	public void setImgLicense5Date(Date imgLicense5Date) {
		this.imgLicense5Date = imgLicense5Date;
	}
	
	@Length(min=0, max=200, message="img_license6_site长度必须介于 0 和 200 之间")
	public String getImgLicense6Site() {
		return imgLicense6Site;
	}

	public void setImgLicense6Site(String imgLicense6Site) {
		this.imgLicense6Site = imgLicense6Site;
	}
	
	@Length(min=0, max=100, message="img_license6_name长度必须介于 0 和 100 之间")
	public String getImgLicense6Name() {
		return imgLicense6Name;
	}

	public void setImgLicense6Name(String imgLicense6Name) {
		this.imgLicense6Name = imgLicense6Name;
	}
	
	@Length(min=0, max=100, message="img_license6_num长度必须介于 0 和 100 之间")
	public String getImgLicense6Num() {
		return imgLicense6Num;
	}

	public void setImgLicense6Num(String imgLicense6Num) {
		this.imgLicense6Num = imgLicense6Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense6Date() {
		return imgLicense6Date;
	}

	public void setImgLicense6Date(Date imgLicense6Date) {
		this.imgLicense6Date = imgLicense6Date;
	}
	
	@Length(min=0, max=200, message="img_license7_site长度必须介于 0 和 200 之间")
	public String getImgLicense7Site() {
		return imgLicense7Site;
	}

	public void setImgLicense7Site(String imgLicense7Site) {
		this.imgLicense7Site = imgLicense7Site;
	}
	
	@Length(min=0, max=100, message="img_license7_name长度必须介于 0 和 100 之间")
	public String getImgLicense7Name() {
		return imgLicense7Name;
	}

	public void setImgLicense7Name(String imgLicense7Name) {
		this.imgLicense7Name = imgLicense7Name;
	}
	
	@Length(min=0, max=100, message="img_license7_num长度必须介于 0 和 100 之间")
	public String getImgLicense7Num() {
		return imgLicense7Num;
	}

	public void setImgLicense7Num(String imgLicense7Num) {
		this.imgLicense7Num = imgLicense7Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense7Date() {
		return imgLicense7Date;
	}

	public void setImgLicense7Date(Date imgLicense7Date) {
		this.imgLicense7Date = imgLicense7Date;
	}
	
	@Length(min=0, max=200, message="img_license8_site长度必须介于 0 和 200 之间")
	public String getImgLicense8Site() {
		return imgLicense8Site;
	}

	public void setImgLicense8Site(String imgLicense8Site) {
		this.imgLicense8Site = imgLicense8Site;
	}
	
	@Length(min=0, max=100, message="img_license8_name长度必须介于 0 和 100 之间")
	public String getImgLicense8Name() {
		return imgLicense8Name;
	}

	public void setImgLicense8Name(String imgLicense8Name) {
		this.imgLicense8Name = imgLicense8Name;
	}
	
	@Length(min=0, max=100, message="img_license8_num长度必须介于 0 和 100 之间")
	public String getImgLicense8Num() {
		return imgLicense8Num;
	}

	public void setImgLicense8Num(String imgLicense8Num) {
		this.imgLicense8Num = imgLicense8Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense8Date() {
		return imgLicense8Date;
	}

	public void setImgLicense8Date(Date imgLicense8Date) {
		this.imgLicense8Date = imgLicense8Date;
	}
	
	@Length(min=0, max=200, message="img_license9_site长度必须介于 0 和 200 之间")
	public String getImgLicense9Site() {
		return imgLicense9Site;
	}

	public void setImgLicense9Site(String imgLicense9Site) {
		this.imgLicense9Site = imgLicense9Site;
	}
	
	@Length(min=0, max=100, message="img_license9_name长度必须介于 0 和 100 之间")
	public String getImgLicense9Name() {
		return imgLicense9Name;
	}

	public void setImgLicense9Name(String imgLicense9Name) {
		this.imgLicense9Name = imgLicense9Name;
	}
	
	@Length(min=0, max=100, message="img_license9_num长度必须介于 0 和 100 之间")
	public String getImgLicense9Num() {
		return imgLicense9Num;
	}

	public void setImgLicense9Num(String imgLicense9Num) {
		this.imgLicense9Num = imgLicense9Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense9Date() {
		return imgLicense9Date;
	}

	public void setImgLicense9Date(Date imgLicense9Date) {
		this.imgLicense9Date = imgLicense9Date;
	}
	
	@Length(min=0, max=200, message="img_license10_site长度必须介于 0 和 200 之间")
	public String getImgLicense10Site() {
		return imgLicense10Site;
	}

	public void setImgLicense10Site(String imgLicense10Site) {
		this.imgLicense10Site = imgLicense10Site;
	}
	
	@Length(min=0, max=100, message="img_license10_name长度必须介于 0 和 100 之间")
	public String getImgLicense10Name() {
		return imgLicense10Name;
	}

	public void setImgLicense10Name(String imgLicense10Name) {
		this.imgLicense10Name = imgLicense10Name;
	}
	
	@Length(min=0, max=100, message="img_license10_num长度必须介于 0 和 100 之间")
	public String getImgLicense10Num() {
		return imgLicense10Num;
	}

	public void setImgLicense10Num(String imgLicense10Num) {
		this.imgLicense10Num = imgLicense10Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense10Date() {
		return imgLicense10Date;
	}

	public void setImgLicense10Date(Date imgLicense10Date) {
		this.imgLicense10Date = imgLicense10Date;
	}
	
	@Length(min=0, max=200, message="img_license11_site长度必须介于 0 和 200 之间")
	public String getImgLicense11Site() {
		return imgLicense11Site;
	}

	public void setImgLicense11Site(String imgLicense11Site) {
		this.imgLicense11Site = imgLicense11Site;
	}
	
	@Length(min=0, max=100, message="img_license11_name长度必须介于 0 和 100 之间")
	public String getImgLicense11Name() {
		return imgLicense11Name;
	}

	public void setImgLicense11Name(String imgLicense11Name) {
		this.imgLicense11Name = imgLicense11Name;
	}
	
	@Length(min=0, max=100, message="img_license11_num长度必须介于 0 和 100 之间")
	public String getImgLicense11Num() {
		return imgLicense11Num;
	}

	public void setImgLicense11Num(String imgLicense11Num) {
		this.imgLicense11Num = imgLicense11Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense11Date() {
		return imgLicense11Date;
	}

	public void setImgLicense11Date(Date imgLicense11Date) {
		this.imgLicense11Date = imgLicense11Date;
	}
	
	@Length(min=0, max=200, message="img_license12_site长度必须介于 0 和 200 之间")
	public String getImgLicense12Site() {
		return imgLicense12Site;
	}

	public void setImgLicense12Site(String imgLicense12Site) {
		this.imgLicense12Site = imgLicense12Site;
	}
	
	@Length(min=0, max=100, message="img_license12_name长度必须介于 0 和 100 之间")
	public String getImgLicense12Name() {
		return imgLicense12Name;
	}

	public void setImgLicense12Name(String imgLicense12Name) {
		this.imgLicense12Name = imgLicense12Name;
	}
	
	@Length(min=0, max=100, message="img_license12_num长度必须介于 0 和 100 之间")
	public String getImgLicense12Num() {
		return imgLicense12Num;
	}

	public void setImgLicense12Num(String imgLicense12Num) {
		this.imgLicense12Num = imgLicense12Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgLicense12Date() {
		return imgLicense12Date;
	}

	public void setImgLicense12Date(Date imgLicense12Date) {
		this.imgLicense12Date = imgLicense12Date;
	}

	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}
}