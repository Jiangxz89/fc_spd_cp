/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 产品表Entity
 * @author sutianqi
 * @version 2018-07-04
 */
public class PdProduct extends DataEntity<PdProduct> {
	
	private static final long serialVersionUID = 1L;
	@ExcelField(title = "产品名称",  sort = 1)
	private String number;		// number
	@ExcelField(title = "产品名称",  sort = 2)
	private String name;		// name
	private String spm;		// spm
	@ExcelField(title = "规格",  sort = 3)
	private String spec;		// spec
	@ExcelField(title = "型号",  sort = 4)
	private String version;		// version
	@ExcelField(title = "单位",  sort = 5)
	private String unit;		// unit
	@ExcelField(title = "产品组别",  sort = 6)
	private String groupId;		// group_id
	@ExcelField(title = "产品分类",  sort = 8)
	private String categoryId;		// category_id
	@ExcelField(title = "产品类型",  sort = 7)
	private String type;		// 词典;低值耗材：0高值耗材：1
	@ExcelField(title = "生产厂家",  sort = 9)
	private String venderId;		// vender_id
	private String supplierId;		// 多id以分号分隔
	@ExcelField(title = "产品进价",  sort = 10)
	private Double purPrice;		// pur_price
	@ExcelField(title = "产品出价",  sort = 11)
	private Double sellingPrice;		// selling_price
	@ExcelField(title = "注册证",  sort = 12)
	private String registration;		// registration
	@ExcelField(title = "备注",  sort = 13)
	private String description;		// description
	private String imgAuthSite;		// img_auth_site
	private String imgAuthNum;		// img_auth_num
	private Date imgAuthDate;		// img_auth_date
	private String imgRegister1Site;		// img_register1_site
	private String imgRegister1Num;		// img_register1_num
	private Date imgRegister1Date;		// img_register1_date
	private String imgRegister2Site;		// img_register2_site
	private String imgRegister2Num;		// img_register2_num
	private Date imgRegister2Date;		// img_register2_date
	private String imgRegister3Site;		// img_register3_site
	private String imgRegister3Num;		// img_register3_num
	private Date imgRegister3Date;		// img_register3_date
	private String imgLicense1Site;		// img_license1_site
	private String imgLicense1Num;		// img_license1_num
	private Date imgLicense1Date;		// img_license1_date
	private String imgLicense2Site;		// img_license2_site
	private String imgLicense2Num;		// img_license2_num
	private Date imgLicense2Date;		// img_license2_date
	private String imgLicense3Site;		// img_license3_site
	private String imgLicense3Num;		// img_license3_num
	private Date imgLicense3Date;		// img_license3_date
	private String imgLicense4Site;		// img_license4_site
	private String imgLicense4Num;		// img_license4_num
	private Date imgLicense4Date;		// img_license4_date
	private String imgProductSite1;		// img_product_site1
	private String imgProductSite2;		// img_product_site2
	private String imgProductSite3;		// img_product_site3

	private String hospitalNumber;//医院代码
	private String hospitalName;//医院名称

	//--temp
	private int rows;//excel表格行号
	private String rowsKey;//表格行号，用于传参

	//查询显示优化
	private String categoryNameShow; //分类
	private String groupNameShow;//组别
	private String venderNameShow;//生产厂家

    private List<String> productIdList;
	private List<String> hospitalNumberList;

	private String chooseFlag;
	
	public PdProduct() {
		super();
	}

	public PdProduct(String id){
		super(id);
	}

	public String getChooseFlag() {
		return chooseFlag;
	}

	public void setChooseFlag(String chooseFlag) {
		this.chooseFlag = chooseFlag;
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
	
	@Length(min=0, max=100, message="spm长度必须介于 0 和 100 之间")
	public String getSpm() {
		return spm;
	}

	public void setSpm(String spm) {
		this.spm = spm;
	}
	
	@Length(min=0, max=100, message="spec长度必须介于 0 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=0, max=100, message="version长度必须介于 0 和 100 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=100, message="unit长度必须介于 0 和 100 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=64, message="group_id长度必须介于 0 和 64 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=0, max=64, message="category_id长度必须介于 0 和 64 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=0, max=10, message="词典;低值耗材：0高值耗材：1长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="vender_id长度必须介于 0 和 64 之间")
	public String getVenderId() {
		return venderId;
	}

	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	
	@Length(min=0, max=1000, message="多id以分号分隔长度必须介于 0 和 1000 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	
	public Double getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(Double purPrice) {
		this.purPrice = purPrice;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Length(min=0, max=500, message="registration长度必须介于 0 和 500 之间")
	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}
	
	@Length(min=0, max=1000, message="description长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=200, message="img_auth_site长度必须介于 0 和 200 之间")
	public String getImgAuthSite() {
		return imgAuthSite;
	}

	public void setImgAuthSite(String imgAuthSite) {
		this.imgAuthSite = imgAuthSite;
	}
	
	@Length(min=0, max=200, message="img_auth_num长度必须介于 0 和 200 之间")
	public String getImgAuthNum() {
		return imgAuthNum;
	}

	public void setImgAuthNum(String imgAuthNum) {
		this.imgAuthNum = imgAuthNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgAuthDate() {
		return imgAuthDate;
	}

	public void setImgAuthDate(Date imgAuthDate) {
		this.imgAuthDate = imgAuthDate;
	}
	
	@Length(min=0, max=200, message="img_register1_site长度必须介于 0 和 200 之间")
	public String getImgRegister1Site() {
		return imgRegister1Site;
	}

	public void setImgRegister1Site(String imgRegister1Site) {
		this.imgRegister1Site = imgRegister1Site;
	}
	
	@Length(min=0, max=200, message="img_register1_num长度必须介于 0 和 200 之间")
	public String getImgRegister1Num() {
		return imgRegister1Num;
	}

	public void setImgRegister1Num(String imgRegister1Num) {
		this.imgRegister1Num = imgRegister1Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgRegister1Date() {
		return imgRegister1Date;
	}

	public void setImgRegister1Date(Date imgRegister1Date) {
		this.imgRegister1Date = imgRegister1Date;
	}
	
	@Length(min=0, max=200, message="img_register2_site长度必须介于 0 和 200 之间")
	public String getImgRegister2Site() {
		return imgRegister2Site;
	}

	public void setImgRegister2Site(String imgRegister2Site) {
		this.imgRegister2Site = imgRegister2Site;
	}
	
	@Length(min=0, max=200, message="img_register2_num长度必须介于 0 和 200 之间")
	public String getImgRegister2Num() {
		return imgRegister2Num;
	}

	public void setImgRegister2Num(String imgRegister2Num) {
		this.imgRegister2Num = imgRegister2Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgRegister2Date() {
		return imgRegister2Date;
	}

	public void setImgRegister2Date(Date imgRegister2Date) {
		this.imgRegister2Date = imgRegister2Date;
	}
	
	@Length(min=0, max=200, message="img_register3_site长度必须介于 0 和 200 之间")
	public String getImgRegister3Site() {
		return imgRegister3Site;
	}

	public void setImgRegister3Site(String imgRegister3Site) {
		this.imgRegister3Site = imgRegister3Site;
	}
	
	@Length(min=0, max=200, message="img_register3_num长度必须介于 0 和 200 之间")
	public String getImgRegister3Num() {
		return imgRegister3Num;
	}

	public void setImgRegister3Num(String imgRegister3Num) {
		this.imgRegister3Num = imgRegister3Num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImgRegister3Date() {
		return imgRegister3Date;
	}

	public void setImgRegister3Date(Date imgRegister3Date) {
		this.imgRegister3Date = imgRegister3Date;
	}
	
	@Length(min=0, max=200, message="img_license1_site长度必须介于 0 和 200 之间")
	public String getImgLicense1Site() {
		return imgLicense1Site;
	}

	public void setImgLicense1Site(String imgLicense1Site) {
		this.imgLicense1Site = imgLicense1Site;
	}
	
	@Length(min=0, max=200, message="img_license1_num长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=200, message="img_license2_num长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=200, message="img_license3_num长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=200, message="img_license4_num长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=200, message="img_product_site1长度必须介于 0 和 200 之间")
	public String getImgProductSite1() {
		return imgProductSite1;
	}

	public void setImgProductSite1(String imgProductSite1) {
		this.imgProductSite1 = imgProductSite1;
	}
	
	@Length(min=0, max=200, message="img_product_site2长度必须介于 0 和 200 之间")
	public String getImgProductSite2() {
		return imgProductSite2;
	}

	public void setImgProductSite2(String imgProductSite2) {
		this.imgProductSite2 = imgProductSite2;
	}
	
	@Length(min=0, max=200, message="img_product_site3长度必须介于 0 和 200 之间")
	public String getImgProductSite3() {
		return imgProductSite3;
	}

	public void setImgProductSite3(String imgProductSite3) {
		this.imgProductSite3 = imgProductSite3;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getRowsKey() {
		return rowsKey;
	}

	public void setRowsKey(String rowsKey) {
		this.rowsKey = rowsKey;
	}


	public String getCategoryNameShow() {
		return categoryNameShow;
	}

	public void setCategoryNameShow(String categoryNameShow) {
		this.categoryNameShow = categoryNameShow;
	}

	public String getGroupNameShow() {
		return groupNameShow;
	}

	public void setGroupNameShow(String groupNameShow) {
		this.groupNameShow = groupNameShow;
	}

	public String getVenderNameShow() {
		return venderNameShow;
	}

	public void setVenderNameShow(String venderNameShow) {
		this.venderNameShow = venderNameShow;
	}

    public List<String> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<String> productIdList) {
        this.productIdList = productIdList;
    }

	public List<String> getHospitalNumberList() {
		return hospitalNumberList;
	}

	public void setHospitalNumberList(List<String> hospitalNumberList) {
		this.hospitalNumberList = hospitalNumberList;
	}
}