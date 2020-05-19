/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品目录表Entity
 * @author zxh
 * @version 2019-05-06
 */
public class MedstoYpml extends DataEntity<MedstoYpml> {
	
	private static final long serialVersionUID = 1L;
	private String xh;		// 序号
	private String ggIdm;		// 规格idm
	private String lcIdm;		// 临床idm
	private String yplh;		// 药品类号
	private String ypmc;		// 药品名称
	private String ypdm;		// 药品代码
	private String ypdm1;		// 备用代码1
	private String ypdm2;		// 备用代码2
	private String py;		// 拼音
	private String wb;		// 五笔
	private String ypgg;		// 药品规格
	private String jxdm;		// 剂型代码
	private String fldm;		// 分类代码
	private String zxdw;		// 最小单位
	private String ggdw;		// 规格单位
	private String ggxs;		// 规格系数
	private String cjdm;		// 厂家代码
	private String cjmc;		// 厂家名称
	private String yxq;		// 有效期
	private String ykdw;		// 药库单位
	private String ykxs;		// 药库系数
	private String jhdw;		// 进货单位
	private String jhxs;		// 进货系数
	private String mzdw;		// 门诊单位
	private String mzxs;		// 门诊系数
	private String zydw;		// 住院单位
	private String zyxs;		// 住院系数
	private String ylsj;		// 零售价
	private String ypfj;		// 批发价
	private String mrjj;		// 默认进价
	private String sxjg;		// 上限价格
	private String zfbz;		// 自费标志
	private String zfbl;		// 自费比例
	private String zyzfbz;		// 住院自费标志
	private String zyzfbl;		// 住院自费比例
	private String gzbz;		// 贵重标志
	private String tsbz;		// 特殊标志
	private String ljlybz;		// 累计领药标志
	private String psbz;		// 皮试标志
	private String tybz;		// 停用标志
	private String qzbz;		// 取整标志
	private String ybxzbz;		// 医保限制标志
	private String ybkzbz;		// 医保控制标志
	private String ypkl;		// 药品扣率
	private String zbbz;		// 招标标志
	private Date lrrq;		// 录入日期
	private String zmlb;		// 账目类别
	private String ghdwId;		// 供货单位代码
	private String ghdwMc;		// 供货单位名称
	private String yply;		// 药品来源
	private String pzwh;		// 批准文号
	private String gmpbz;		// gmp标志
	private String czyh;		// 操作员
	private Date czrq;		// 操作日期
	private String zbdw;		// 招标单位
	private String mrzbj;		// 默认招标价
	private String zbqh;		// 招标期号
	private String dffbz;		// 单复方标志
	private String dydm;		// 对应代码
	private String memo;		// 说明
	private String babz;		// 备案标志
	private String otcbz;		// otc标志
	private String bxbz;		// 报销标志
	private String ypsms;		// 药品说明书
	private String govprice;		// 官方价格
	private String jgyj;		// 价格依据
	private String cfyp;		// 处方药品
	private String gfmc;		// 官方名称
	private String ybmc;		// 医保名称
	private String gfbz;		// 公费标志
	private String ybfydj;		// 医保费用等级
	private String syfw;		// 适用范围
	private String syzbz;		// 适应症标志
	private String hybz;		// 换药标志
	private String yzglbz;		// 医嘱管理标志
	private String sybbz;		// 省医保标志
	private String sgfbz;		// 省公费标志
	private String islcjsyp;		// 零差结算药品
	private String lcjsdj;		// 零差结算单价
	private String tsypglbz;		// 特殊药品管理标志
	private String tsypcgfs;		// 特殊药品采购方式
	private String gybz;		// 公用标志
	private Date pzwhxq;		// 批准文号效期
	private String tbid;		// 同步代码
	private String basicdrugFlag;		// 国家基药标志
	private String jhsyypFlag;		// 计划生育药品标志
	private String kjhypFlag;		// 抗结核药品标
	private String cfsm;		// 存放说明
	private String mzbz;		// 麻醉标志
	private String sbbxbl;		// 省补报销比例
	private String icon;		// 说明书地址
	private String sbjybz;		// 省补基药标志
	private String djybz;		// 低价药标志
	private String qtybz;		// 其他药标志
	private String ybbz;		// 医保标志
	private String kssjb;		// 抗生素级别
	private String jsypjb;		// 精神药品级别
	private String kzlypbz;		// 控制类药品标志
	
	public MedstoYpml() {
		super();
	}

	public MedstoYpml(String id){
		super(id);
	}

	@Length(min=1, max=60, message="序号长度必须介于 1 和 60 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=0, max=60, message="规格idm长度必须介于 0 和 60 之间")
	public String getGgIdm() {
		return ggIdm;
	}

	public void setGgIdm(String ggIdm) {
		this.ggIdm = ggIdm;
	}
	
	@Length(min=0, max=60, message="临床idm长度必须介于 0 和 60 之间")
	public String getLcIdm() {
		return lcIdm;
	}

	public void setLcIdm(String lcIdm) {
		this.lcIdm = lcIdm;
	}
	
	@Length(min=0, max=60, message="药品类号长度必须介于 0 和 60 之间")
	public String getYplh() {
		return yplh;
	}

	public void setYplh(String yplh) {
		this.yplh = yplh;
	}
	
	@Length(min=0, max=200, message="药品名称长度必须介于 0 和 200 之间")
	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	
	@Length(min=0, max=60, message="药品代码长度必须介于 0 和 60 之间")
	public String getYpdm() {
		return ypdm;
	}

	public void setYpdm(String ypdm) {
		this.ypdm = ypdm;
	}
	
	@Length(min=0, max=60, message="备用代码1长度必须介于 0 和 60 之间")
	public String getYpdm1() {
		return ypdm1;
	}

	public void setYpdm1(String ypdm1) {
		this.ypdm1 = ypdm1;
	}
	
	@Length(min=0, max=60, message="备用代码2长度必须介于 0 和 60 之间")
	public String getYpdm2() {
		return ypdm2;
	}

	public void setYpdm2(String ypdm2) {
		this.ypdm2 = ypdm2;
	}
	
	@Length(min=0, max=60, message="拼音长度必须介于 0 和 60 之间")
	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}
	
	@Length(min=0, max=60, message="五笔长度必须介于 0 和 60 之间")
	public String getWb() {
		return wb;
	}

	public void setWb(String wb) {
		this.wb = wb;
	}
	
	@Length(min=0, max=60, message="药品规格长度必须介于 0 和 60 之间")
	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}
	
	@Length(min=0, max=60, message="剂型代码长度必须介于 0 和 60 之间")
	public String getJxdm() {
		return jxdm;
	}

	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}
	
	@Length(min=0, max=60, message="分类代码长度必须介于 0 和 60 之间")
	public String getFldm() {
		return fldm;
	}

	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	
	@Length(min=0, max=60, message="最小单位长度必须介于 0 和 60 之间")
	public String getZxdw() {
		return zxdw;
	}

	public void setZxdw(String zxdw) {
		this.zxdw = zxdw;
	}
	
	@Length(min=0, max=60, message="规格单位长度必须介于 0 和 60 之间")
	public String getGgdw() {
		return ggdw;
	}

	public void setGgdw(String ggdw) {
		this.ggdw = ggdw;
	}
	
	public String getGgxs() {
		return ggxs;
	}

	public void setGgxs(String ggxs) {
		this.ggxs = ggxs;
	}
	
	@Length(min=0, max=60, message="厂家代码长度必须介于 0 和 60 之间")
	public String getCjdm() {
		return cjdm;
	}

	public void setCjdm(String cjdm) {
		this.cjdm = cjdm;
	}
	
	@Length(min=0, max=500, message="厂家名称长度必须介于 0 和 500 之间")
	public String getCjmc() {
		return cjmc;
	}

	public void setCjmc(String cjmc) {
		this.cjmc = cjmc;
	}
	
	@Length(min=0, max=11, message="有效期长度必须介于 0 和 11 之间")
	public String getYxq() {
		return yxq;
	}

	public void setYxq(String yxq) {
		this.yxq = yxq;
	}
	
	@Length(min=0, max=60, message="药库单位长度必须介于 0 和 60 之间")
	public String getYkdw() {
		return ykdw;
	}

	public void setYkdw(String ykdw) {
		this.ykdw = ykdw;
	}
	
	public String getYkxs() {
		return ykxs;
	}

	public void setYkxs(String ykxs) {
		this.ykxs = ykxs;
	}
	
	@Length(min=0, max=60, message="进货单位长度必须介于 0 和 60 之间")
	public String getJhdw() {
		return jhdw;
	}

	public void setJhdw(String jhdw) {
		this.jhdw = jhdw;
	}
	
	public String getJhxs() {
		return jhxs;
	}

	public void setJhxs(String jhxs) {
		this.jhxs = jhxs;
	}
	
	@Length(min=0, max=60, message="门诊单位长度必须介于 0 和 60 之间")
	public String getMzdw() {
		return mzdw;
	}

	public void setMzdw(String mzdw) {
		this.mzdw = mzdw;
	}
	
	public String getMzxs() {
		return mzxs;
	}

	public void setMzxs(String mzxs) {
		this.mzxs = mzxs;
	}
	
	@Length(min=0, max=60, message="住院单位长度必须介于 0 和 60 之间")
	public String getZydw() {
		return zydw;
	}

	public void setZydw(String zydw) {
		this.zydw = zydw;
	}
	
	public String getZyxs() {
		return zyxs;
	}

	public void setZyxs(String zyxs) {
		this.zyxs = zyxs;
	}
	
	public String getYlsj() {
		return ylsj;
	}

	public void setYlsj(String ylsj) {
		this.ylsj = ylsj;
	}
	
	public String getYpfj() {
		return ypfj;
	}

	public void setYpfj(String ypfj) {
		this.ypfj = ypfj;
	}
	
	public String getMrjj() {
		return mrjj;
	}

	public void setMrjj(String mrjj) {
		this.mrjj = mrjj;
	}
	
	public String getSxjg() {
		return sxjg;
	}

	public void setSxjg(String sxjg) {
		this.sxjg = sxjg;
	}
	
	@Length(min=0, max=50, message="自费标志长度必须介于 0 和 50 之间")
	public String getZfbz() {
		return zfbz;
	}

	public void setZfbz(String zfbz) {
		this.zfbz = zfbz;
	}
	
	public String getZfbl() {
		return zfbl;
	}

	public void setZfbl(String zfbl) {
		this.zfbl = zfbl;
	}
	
	@Length(min=0, max=50, message="住院自费标志长度必须介于 0 和 50 之间")
	public String getZyzfbz() {
		return zyzfbz;
	}

	public void setZyzfbz(String zyzfbz) {
		this.zyzfbz = zyzfbz;
	}
	
	public String getZyzfbl() {
		return zyzfbl;
	}

	public void setZyzfbl(String zyzfbl) {
		this.zyzfbl = zyzfbl;
	}
	
	@Length(min=0, max=50, message="贵重标志长度必须介于 0 和 50 之间")
	public String getGzbz() {
		return gzbz;
	}

	public void setGzbz(String gzbz) {
		this.gzbz = gzbz;
	}
	
	@Length(min=0, max=50, message="特殊标志长度必须介于 0 和 50 之间")
	public String getTsbz() {
		return tsbz;
	}

	public void setTsbz(String tsbz) {
		this.tsbz = tsbz;
	}
	
	@Length(min=0, max=50, message="累计领药标志长度必须介于 0 和 50 之间")
	public String getLjlybz() {
		return ljlybz;
	}

	public void setLjlybz(String ljlybz) {
		this.ljlybz = ljlybz;
	}
	
	@Length(min=0, max=50, message="皮试标志长度必须介于 0 和 50 之间")
	public String getPsbz() {
		return psbz;
	}

	public void setPsbz(String psbz) {
		this.psbz = psbz;
	}
	
	@Length(min=0, max=50, message="停用标志长度必须介于 0 和 50 之间")
	public String getTybz() {
		return tybz;
	}

	public void setTybz(String tybz) {
		this.tybz = tybz;
	}
	
	@Length(min=0, max=50, message="取整标志长度必须介于 0 和 50 之间")
	public String getQzbz() {
		return qzbz;
	}

	public void setQzbz(String qzbz) {
		this.qzbz = qzbz;
	}
	
	@Length(min=0, max=50, message="医保限制标志长度必须介于 0 和 50 之间")
	public String getYbxzbz() {
		return ybxzbz;
	}

	public void setYbxzbz(String ybxzbz) {
		this.ybxzbz = ybxzbz;
	}
	
	@Length(min=0, max=50, message="医保控制标志长度必须介于 0 和 50 之间")
	public String getYbkzbz() {
		return ybkzbz;
	}

	public void setYbkzbz(String ybkzbz) {
		this.ybkzbz = ybkzbz;
	}
	
	public String getYpkl() {
		return ypkl;
	}

	public void setYpkl(String ypkl) {
		this.ypkl = ypkl;
	}
	
	@Length(min=0, max=50, message="招标标志长度必须介于 0 和 50 之间")
	public String getZbbz() {
		return zbbz;
	}

	public void setZbbz(String zbbz) {
		this.zbbz = zbbz;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLrrq() {
		return lrrq;
	}

	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}
	
	@Length(min=0, max=60, message="账目类别长度必须介于 0 和 60 之间")
	public String getZmlb() {
		return zmlb;
	}

	public void setZmlb(String zmlb) {
		this.zmlb = zmlb;
	}
	
	@Length(min=0, max=60, message="供货单位代码长度必须介于 0 和 60 之间")
	public String getGhdwId() {
		return ghdwId;
	}

	public void setGhdwId(String ghdwId) {
		this.ghdwId = ghdwId;
	}
	
	@Length(min=0, max=500, message="供货单位名称长度必须介于 0 和 500 之间")
	public String getGhdwMc() {
		return ghdwMc;
	}

	public void setGhdwMc(String ghdwMc) {
		this.ghdwMc = ghdwMc;
	}
	
	@Length(min=0, max=60, message="药品来源长度必须介于 0 和 60 之间")
	public String getYply() {
		return yply;
	}

	public void setYply(String yply) {
		this.yply = yply;
	}
	
	@Length(min=0, max=500, message="批准文号长度必须介于 0 和 500 之间")
	public String getPzwh() {
		return pzwh;
	}

	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}
	
	@Length(min=0, max=50, message="gmp标志长度必须介于 0 和 50 之间")
	public String getGmpbz() {
		return gmpbz;
	}

	public void setGmpbz(String gmpbz) {
		this.gmpbz = gmpbz;
	}
	
	@Length(min=0, max=60, message="操作员长度必须介于 0 和 60 之间")
	public String getCzyh() {
		return czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCzrq() {
		return czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}
	
	@Length(min=0, max=500, message="招标单位长度必须介于 0 和 500 之间")
	public String getZbdw() {
		return zbdw;
	}

	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}
	
	public String getMrzbj() {
		return mrzbj;
	}

	public void setMrzbj(String mrzbj) {
		this.mrzbj = mrzbj;
	}
	
	@Length(min=0, max=60, message="招标期号长度必须介于 0 和 60 之间")
	public String getZbqh() {
		return zbqh;
	}

	public void setZbqh(String zbqh) {
		this.zbqh = zbqh;
	}
	
	@Length(min=0, max=50, message="单复方标志长度必须介于 0 和 50 之间")
	public String getDffbz() {
		return dffbz;
	}

	public void setDffbz(String dffbz) {
		this.dffbz = dffbz;
	}
	
	@Length(min=0, max=60, message="对应代码长度必须介于 0 和 60 之间")
	public String getDydm() {
		return dydm;
	}

	public void setDydm(String dydm) {
		this.dydm = dydm;
	}
	
	@Length(min=0, max=500, message="说明长度必须介于 0 和 500 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=50, message="备案标志长度必须介于 0 和 50 之间")
	public String getBabz() {
		return babz;
	}

	public void setBabz(String babz) {
		this.babz = babz;
	}
	
	@Length(min=0, max=50, message="otc标志长度必须介于 0 和 50 之间")
	public String getOtcbz() {
		return otcbz;
	}

	public void setOtcbz(String otcbz) {
		this.otcbz = otcbz;
	}
	
	@Length(min=0, max=50, message="报销标志长度必须介于 0 和 50 之间")
	public String getBxbz() {
		return bxbz;
	}

	public void setBxbz(String bxbz) {
		this.bxbz = bxbz;
	}
	
	@Length(min=0, max=500, message="药品说明书长度必须介于 0 和 500 之间")
	public String getYpsms() {
		return ypsms;
	}

	public void setYpsms(String ypsms) {
		this.ypsms = ypsms;
	}
	
	public String getGovprice() {
		return govprice;
	}

	public void setGovprice(String govprice) {
		this.govprice = govprice;
	}
	
	@Length(min=0, max=200, message="价格依据长度必须介于 0 和 200 之间")
	public String getJgyj() {
		return jgyj;
	}

	public void setJgyj(String jgyj) {
		this.jgyj = jgyj;
	}
	
	@Length(min=0, max=200, message="处方药品长度必须介于 0 和 200 之间")
	public String getCfyp() {
		return cfyp;
	}

	public void setCfyp(String cfyp) {
		this.cfyp = cfyp;
	}
	
	@Length(min=0, max=200, message="官方名称长度必须介于 0 和 200 之间")
	public String getGfmc() {
		return gfmc;
	}

	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}
	
	@Length(min=0, max=200, message="医保名称长度必须介于 0 和 200 之间")
	public String getYbmc() {
		return ybmc;
	}

	public void setYbmc(String ybmc) {
		this.ybmc = ybmc;
	}
	
	@Length(min=0, max=50, message="公费标志长度必须介于 0 和 50 之间")
	public String getGfbz() {
		return gfbz;
	}

	public void setGfbz(String gfbz) {
		this.gfbz = gfbz;
	}
	
	@Length(min=0, max=50, message="医保费用等级长度必须介于 0 和 50 之间")
	public String getYbfydj() {
		return ybfydj;
	}

	public void setYbfydj(String ybfydj) {
		this.ybfydj = ybfydj;
	}
	
	@Length(min=0, max=200, message="适用范围长度必须介于 0 和 200 之间")
	public String getSyfw() {
		return syfw;
	}

	public void setSyfw(String syfw) {
		this.syfw = syfw;
	}
	
	@Length(min=0, max=200, message="适应症标志长度必须介于 0 和 200 之间")
	public String getSyzbz() {
		return syzbz;
	}

	public void setSyzbz(String syzbz) {
		this.syzbz = syzbz;
	}
	
	@Length(min=0, max=200, message="换药标志长度必须介于 0 和 200 之间")
	public String getHybz() {
		return hybz;
	}

	public void setHybz(String hybz) {
		this.hybz = hybz;
	}
	
	@Length(min=0, max=200, message="医嘱管理标志长度必须介于 0 和 200 之间")
	public String getYzglbz() {
		return yzglbz;
	}

	public void setYzglbz(String yzglbz) {
		this.yzglbz = yzglbz;
	}
	
	@Length(min=0, max=200, message="省医保标志长度必须介于 0 和 200 之间")
	public String getSybbz() {
		return sybbz;
	}

	public void setSybbz(String sybbz) {
		this.sybbz = sybbz;
	}
	
	@Length(min=0, max=200, message="省公费标志长度必须介于 0 和 200 之间")
	public String getSgfbz() {
		return sgfbz;
	}

	public void setSgfbz(String sgfbz) {
		this.sgfbz = sgfbz;
	}
	
	@Length(min=0, max=200, message="零差结算药品长度必须介于 0 和 200 之间")
	public String getIslcjsyp() {
		return islcjsyp;
	}

	public void setIslcjsyp(String islcjsyp) {
		this.islcjsyp = islcjsyp;
	}
	
	public String getLcjsdj() {
		return lcjsdj;
	}

	public void setLcjsdj(String lcjsdj) {
		this.lcjsdj = lcjsdj;
	}
	
	@Length(min=0, max=200, message="特殊药品管理标志长度必须介于 0 和 200 之间")
	public String getTsypglbz() {
		return tsypglbz;
	}

	public void setTsypglbz(String tsypglbz) {
		this.tsypglbz = tsypglbz;
	}
	
	@Length(min=0, max=200, message="特殊药品采购方式长度必须介于 0 和 200 之间")
	public String getTsypcgfs() {
		return tsypcgfs;
	}

	public void setTsypcgfs(String tsypcgfs) {
		this.tsypcgfs = tsypcgfs;
	}
	
	@Length(min=0, max=200, message="公用标志长度必须介于 0 和 200 之间")
	public String getGybz() {
		return gybz;
	}

	public void setGybz(String gybz) {
		this.gybz = gybz;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPzwhxq() {
		return pzwhxq;
	}

	public void setPzwhxq(Date pzwhxq) {
		this.pzwhxq = pzwhxq;
	}
	
	@Length(min=0, max=11, message="同步代码长度必须介于 0 和 11 之间")
	public String getTbid() {
		return tbid;
	}

	public void setTbid(String tbid) {
		this.tbid = tbid;
	}
	
	@Length(min=0, max=200, message="国家基药标志长度必须介于 0 和 200 之间")
	public String getBasicdrugFlag() {
		return basicdrugFlag;
	}

	public void setBasicdrugFlag(String basicdrugFlag) {
		this.basicdrugFlag = basicdrugFlag;
	}
	
	@Length(min=0, max=200, message="计划生育药品标志长度必须介于 0 和 200 之间")
	public String getJhsyypFlag() {
		return jhsyypFlag;
	}

	public void setJhsyypFlag(String jhsyypFlag) {
		this.jhsyypFlag = jhsyypFlag;
	}
	
	@Length(min=0, max=200, message="抗结核药品标长度必须介于 0 和 200 之间")
	public String getKjhypFlag() {
		return kjhypFlag;
	}

	public void setKjhypFlag(String kjhypFlag) {
		this.kjhypFlag = kjhypFlag;
	}
	
	@Length(min=0, max=200, message="存放说明长度必须介于 0 和 200 之间")
	public String getCfsm() {
		return cfsm;
	}

	public void setCfsm(String cfsm) {
		this.cfsm = cfsm;
	}
	
	@Length(min=0, max=200, message="麻醉标志长度必须介于 0 和 200 之间")
	public String getMzbz() {
		return mzbz;
	}

	public void setMzbz(String mzbz) {
		this.mzbz = mzbz;
	}
	
	public String getSbbxbl() {
		return sbbxbl;
	}

	public void setSbbxbl(String sbbxbl) {
		this.sbbxbl = sbbxbl;
	}
	
	@Length(min=0, max=250, message="说明书地址长度必须介于 0 和 250 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=0, max=200, message="省补基药标志长度必须介于 0 和 200 之间")
	public String getSbjybz() {
		return sbjybz;
	}

	public void setSbjybz(String sbjybz) {
		this.sbjybz = sbjybz;
	}
	
	@Length(min=0, max=200, message="低价药标志长度必须介于 0 和 200 之间")
	public String getDjybz() {
		return djybz;
	}

	public void setDjybz(String djybz) {
		this.djybz = djybz;
	}
	
	@Length(min=0, max=200, message="其他药标志长度必须介于 0 和 200 之间")
	public String getQtybz() {
		return qtybz;
	}

	public void setQtybz(String qtybz) {
		this.qtybz = qtybz;
	}
	
	@Length(min=0, max=200, message="医保标志长度必须介于 0 和 200 之间")
	public String getYbbz() {
		return ybbz;
	}

	public void setYbbz(String ybbz) {
		this.ybbz = ybbz;
	}
	
	@Length(min=0, max=200, message="抗生素级别长度必须介于 0 和 200 之间")
	public String getKssjb() {
		return kssjb;
	}

	public void setKssjb(String kssjb) {
		this.kssjb = kssjb;
	}
	
	@Length(min=0, max=200, message="精神药品级别长度必须介于 0 和 200 之间")
	public String getJsypjb() {
		return jsypjb;
	}

	public void setJsypjb(String jsypjb) {
		this.jsypjb = jsypjb;
	}
	
	@Length(min=0, max=200, message="控制类药品标志长度必须介于 0 和 200 之间")
	public String getKzlypbz() {
		return kzlypbz;
	}

	public void setKzlypbz(String kzlypbz) {
		this.kzlypbz = kzlypbz;
	}
	
}