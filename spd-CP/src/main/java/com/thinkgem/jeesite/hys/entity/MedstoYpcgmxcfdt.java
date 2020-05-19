/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;
//import java.math.BigDecimal;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品订单拆分详情表Entity
 * @author zxh
 * @version 2018-10-10
 */
public class MedstoYpcgmxcfdt extends DataEntity<MedstoYpcgmxcfdt> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 主键 : 主键
	private String apartId;		// 拆分表主键 : 拆分表主键
	private String ypdm;		// 药品代码 : 药品代码
	private String cgsl;		// 订货数 : 订货数
	private Double ypjj;		// 药品进价 : 药品进价
	private Double ypjjje;//药品进价金额
	
	private String ypmc;//药品名称
	private String ypgg;//药品规格
	private String zxdw;//药品单位
	private String cjmc;//生产厂家
	
	public MedstoYpcgmxcfdt() {
		super();
	}

	public MedstoYpcgmxcfdt(String id){
		super(id);
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	public String getApartId() {
		return apartId;
	}

	public void setApartId(String apartId) {
		this.apartId = apartId;
	}
	
	@Length(min=0, max=18, message="药品代码 : 药品代码长度必须介于 0 和 18 之间")
	public String getYpdm() {
		return ypdm;
	}

	public void setYpdm(String ypdm) {
		this.ypdm = ypdm;
	}
	
	@Length(min=0, max=10, message="订货数 : 订货数长度必须介于 0 和 10 之间")
	public String getCgsl() {
		return cgsl;
	}

	public void setCgsl(String cgsl) {
		this.cgsl = cgsl;
	}

/*	public Double getYpjj() {
		return new BigDecimal(this.ypjj).multiply(new BigDecimal(this.cgsl)).doubleValue();
	}

	public void setYpjj(Double ypjj) {
		this.ypjj = ypjj;
	}*/
	
	
	public Double getYpjjje() {
		return ypjjje;
	}

	public Double getYpjj() {
		return ypjj;
	}

	public void setYpjj(Double ypjj) {
		this.ypjj = ypjj;
	}

	public void setYpjjje(Double ypjjje) {
		this.ypjjje = ypjjje;
	}

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}

	public String getZxdw() {
		return zxdw;
	}

	public void setZxdw(String zxdw) {
		this.zxdw = zxdw;
	}

	public String getCjmc() {
		return cjmc;
	}

	public void setCjmc(String cjmc) {
		this.cjmc = cjmc;
	}
	
	
	
}