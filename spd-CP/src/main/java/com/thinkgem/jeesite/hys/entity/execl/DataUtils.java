package com.thinkgem.jeesite.hys.entity.execl;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.hys.entity.*;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DataUtils {
	/**
	 * 药品出库明细导出
	 * @param list
	 * @return
	 */
	public static List<MedstoYpckzdExcel> getDrugOutDetaileData(List<MedstoYpckzd> list) {
		List<MedstoYpckzdExcel> dataList = Lists.newArrayList();
		for(MedstoYpckzd medstoYpckzd :list){
			MedstoYpckzdExcel mye = new MedstoYpckzdExcel();
			mye.setRkdm(medstoYpckzd.getRkdm());
			mye.setRkdh(medstoYpckzd.getRkdh());
			mye.setKsdm(medstoYpckzd.getKsdm());
			mye.setGhdwdm(medstoYpckzd.getGhdwdm());
			mye.setFph(medstoYpckzd.getFph());
			mye.setKprq(medstoYpckzd.getKprq()==null?"":DateUtils.formatDate(medstoYpckzd.getKprq(), "yyyy-MM-dd"));
			mye.setDprq(medstoYpckzd.getDprq()==null?"":DateUtils.formatDate(medstoYpckzd.getDprq(), "yyyy-MM-dd"));
			mye.setRkrq(medstoYpckzd.getRkrq()==null?"":DateUtils.formatDate(medstoYpckzd.getRkrq(), "yyyy-MM-dd"));
			mye.setRkczyh(medstoYpckzd.getRkczyh());
			mye.setLsje(medstoYpckzd.getLsje());
			mye.setPfje(medstoYpckzd.getPfje());
			mye.setJjje(medstoYpckzd.getJjje());
			mye.setYhje(medstoYpckzd.getYhje());
			mye.setPsdh(medstoYpckzd.getPsdh());
			mye.setDrugName(medstoYpckzd.getMedstoYpckmx().getDrugName());
			mye.setDrugSpec(medstoYpckzd.getMedstoYpckmx().getDrugSpec());
			mye.setSxrq(medstoYpckzd.getMedstoYpckmx().getSxrq()==null?"":DateUtils.formatDate(medstoYpckzd.getMedstoYpckmx().getSxrq(), "yyyy-MM-dd"));
			mye.setPh(medstoYpckzd.getMedstoYpckmx().getPh());
			mye.setCzsl(medstoYpckzd.getMedstoYpckmx().getCzsl());
			mye.setYpjj(medstoYpckzd.getMedstoYpckmx().getYpjj());
			mye.setYpfj(medstoYpckzd.getMedstoYpckmx().getYpfj());
			mye.setYlsj(medstoYpckzd.getMedstoYpckmx().getYlsj());
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 * 药品调价单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYpdjdExcel> getdrugDrugPriceAdjustmentData(List<MedstoYpdjd> list) {
		List<MedstoYpdjdExcel> dataList = Lists.newArrayList();
		for(MedstoYpdjd medstoYpdjd :list){
			MedstoYpdjdExcel mye = new MedstoYpdjdExcel();
			mye.setDrugId(medstoYpdjd.getDrugId());
			mye.setDrugName(medstoYpdjd.getDrugName());
			mye.setDrugSpec(medstoYpdjd.getDrugSpec());
			mye.setYpmrkl(medstoYpdjd.getYpmrkl());
			mye.setStoreName(medstoYpdjd.getStoreName());
			mye.setYbtybm(medstoYpdjd.getYbtybm());
			mye.setIsjbyw(medstoYpdjd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYpdjd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYpdjd.getCmedCode());
			mye.setDrugDetailType(medstoYpdjd.getDrugDetailType());
			mye.setDoseCode(medstoYpdjd.getDoseCode());
			mye.setPackageUnit(medstoYpdjd.getPackageUnit());
			mye.setPackageQty(medstoYpdjd.getPackageQty());
			mye.setSalesUnit(medstoYpdjd.getSalesUnit());
			mye.setSalesQty(medstoYpdjd.getSalesQty());
			mye.setSalesRelation(medstoYpdjd.getSalesRelation());
			mye.setProducerName(medstoYpdjd.getProducerName());
			mye.setCostPrice(medstoYpdjd.getCostPrice());
			mye.setSalePrice(medstoYpdjd.getSalePrice());
			mye.setCostAmt(medstoYpdjd.getCostAmt());
			mye.setSaleAmt(medstoYpdjd.getSaleAmt());
			mye.setBatchNo(medstoYpdjd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYpdjd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYpdjd.getSdrughabitat());
			mye.setSratificationno(medstoYpdjd.getSratificationno());
			mye.setDdateproduce(medstoYpdjd.getDdateproduce()==null?"":DateUtils.formatDate(medstoYpdjd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYpdjd.getExpiry()==null?"":DateUtils.formatDate(medstoYpdjd.getExpiry(), "yyyy-MM-dd"));
			mye.setBeforeprice(medstoYpdjd.getBeforeprice());
			mye.setAfterprice(medstoYpdjd.getAfterprice());
			mye.setScrq(medstoYpdjd.getScrq()==null?"":DateUtils.formatDate(medstoYpdjd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 * 药品盘点单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYppddExcel> getdrugInventoryDetailedData(List<MedstoYppdd> list) {

		List<MedstoYppddExcel> dataList = Lists.newArrayList();
		for(MedstoYppdd medstoYppdd :list){
			MedstoYppddExcel mye = new MedstoYppddExcel();
			mye.setDrugId(medstoYppdd.getDrugId());
			mye.setDrugName(medstoYppdd.getDrugName());
			mye.setDrugSpec(medstoYppdd.getDrugSpec());
			mye.setYbtybm(medstoYppdd.getYbtybm());
			mye.setIsjbyw(medstoYppdd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYppdd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYppdd.getCmedCode());
			mye.setDrugDetailType(medstoYppdd.getDrugDetailType());
			mye.setDoseCode(medstoYppdd.getDoseCode());
			mye.setPackageUnit(medstoYppdd.getPackageUnit());
			mye.setPackageQty(medstoYppdd.getPackageQty());
			mye.setSalesUnit(medstoYppdd.getSalesUnit());
			mye.setSalesQty(medstoYppdd.getSalesQty());
			mye.setSalesRelation(medstoYppdd.getSalesRelation());
			mye.setProducerName(medstoYppdd.getProducerName());
			mye.setCostPrice(medstoYppdd.getCostPrice());
			mye.setSalePrice(medstoYppdd.getSalePrice());
			mye.setCostAmt(medstoYppdd.getCostAmt());
			mye.setSaleAmt(medstoYppdd.getSaleAmt());
			mye.setBatchNo(medstoYppdd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYppdd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYppdd.getSdrughabitat());
			mye.setSratificationno(medstoYppdd.getSratificationno());
			mye.setDdateproduce(medstoYppdd.getDdateproduce()==null?"":DateUtils.formatDate(medstoYppdd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYppdd.getExpiry()==null?"":DateUtils.formatDate(medstoYppdd.getExpiry(), "yyyy-MM-dd"));
			mye.setInventorystorage(medstoYppdd.getInventorystorage());
			mye.setStoragenumber(medstoYppdd.getStoragenumber());
			mye.setInventorynumber(medstoYppdd.getInventorynumber());
			mye.setYpmrkl(medstoYppdd.getYpmrkl());
			mye.setScrq(medstoYppdd.getScrq()==null?"":DateUtils.formatDate(medstoYppdd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;

	}

	/**
	 * 药品丢失单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYpbsdExcel> getDrugLostOrderDetaileData(List<MedstoYpbsd> list) {
		List<MedstoYpbsdExcel> dataList = Lists.newArrayList();
		for(MedstoYpbsd medstoYpbsd :list){
			MedstoYpbsdExcel mye = new MedstoYpbsdExcel();
			mye.setStoreName(medstoYpbsd.getStoreName());
			mye.setDrugId(medstoYpbsd.getDrugId());
			mye.setDrugName(medstoYpbsd.getDrugName());
			mye.setDrugSpec(medstoYpbsd.getDrugSpec());
			mye.setYbtybm(medstoYpbsd.getYbtybm());
			mye.setIsjbyw(medstoYpbsd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYpbsd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYpbsd.getCmedCode());
			mye.setDrugDetailType(medstoYpbsd.getDrugDetailType());
			mye.setDoseCode(medstoYpbsd.getDoseCode());
			mye.setPackageUnit(medstoYpbsd.getPackageUnit());
			mye.setPackageQty(medstoYpbsd.getPackageQty());
			mye.setSalesUnit(medstoYpbsd.getSalesUnit());
			mye.setSalesQty(medstoYpbsd.getSalesQty());
			mye.setSalesRelation(medstoYpbsd.getSalesRelation());
			mye.setProducerName(medstoYpbsd.getProducerName());
			mye.setCostPrice(medstoYpbsd.getCostPrice());
			mye.setSalePrice(medstoYpbsd.getSalePrice());
			mye.setCostAmt(medstoYpbsd.getCostAmt());
			mye.setSaleAmt(medstoYpbsd.getSaleAmt());
			mye.setBatchNo(medstoYpbsd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYpbsd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYpbsd.getSdrughabitat());
			mye.setSratificationno(medstoYpbsd.getSratificationno());
			mye.setDamagenumber(medstoYpbsd.getDamagenumber());
			mye.setDamagedatetime(medstoYpbsd.getDamagedatetime()==null?"":DateUtils.formatDate(medstoYpbsd.getDamagedatetime(), "yyyy-MM-dd"));
			mye.setDamageremark(medstoYpbsd.getDamageremark());
			mye.setDdateproduce(medstoYpbsd.getDdateproduce()==null?"":DateUtils.formatDate(medstoYpbsd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYpbsd.getExpiry()==null?"":DateUtils.formatDate(medstoYpbsd.getExpiry(), "yyyy-MM-dd"));
			mye.setScrq(medstoYpbsd.getScrq()==null?"":DateUtils.formatDate(medstoYpbsd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 *	药品退药单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYptydExcel> getDrugReturnBillDetaileData(List<MedstoYptyd> list) {
		List<MedstoYptydExcel> dataList = Lists.newArrayList();
		for (MedstoYptyd medstoYptyd : list) {
			MedstoYptydExcel mye = new MedstoYptydExcel();
			mye.setStoreName(medstoYptyd.getStoreName());
			mye.setDrugId(medstoYptyd.getDrugId());
			mye.setDrugName(medstoYptyd.getDrugName());
			mye.setDrugSpec(medstoYptyd.getDrugSpec());
			mye.setYbtybm(medstoYptyd.getYbtybm());
			mye.setIsjbyw(medstoYptyd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYptyd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYptyd.getCmedCode());
			mye.setDrugDetailType(medstoYptyd.getDrugDetailType());
			mye.setDoseCode(medstoYptyd.getDoseCode());
			mye.setPackageUnit(medstoYptyd.getPackageUnit());
			mye.setPackageQty(medstoYptyd.getPackageQty());
			mye.setSalesUnit(medstoYptyd.getSalesUnit());
			mye.setSalesQty(medstoYptyd.getSalesQty());
			mye.setSalesRelation(medstoYptyd.getSalesRelation());
			mye.setProducerName(medstoYptyd.getProducerName());
			mye.setCostPrice(medstoYptyd.getCostPrice());
			mye.setSalePrice(medstoYptyd.getSalePrice());
			mye.setCostAmt(medstoYptyd.getCostAmt());
			mye.setSaleAmt(medstoYptyd.getSaleAmt());
			mye.setBatchNo(medstoYptyd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYptyd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYptyd.getSdrughabitat());
			mye.setSratificationno(medstoYptyd.getSratificationno());
			mye.setDdateproduce(medstoYptyd.getDdateproduce() == null ? "" : DateUtils.formatDate(medstoYptyd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYptyd.getExpiry() == null ? "" : DateUtils.formatDate(medstoYptyd.getExpiry(), "yyyy-MM-dd"));
			mye.setInventorystorage(medstoYptyd.getInventorystorage());
			mye.setStoragenumber(medstoYptyd.getStoragenumber());
			mye.setInventorynumber(medstoYptyd.getInventorynumber());
			mye.setThrq(medstoYptyd.getThrq() == null ? "" : DateUtils.formatDate(medstoYptyd.getThrq(), "yyyy-MM-dd"));
			mye.setThyy(medstoYptyd.getThyy());
			mye.setScrq(medstoYptyd.getScrq() == null ? "" : DateUtils.formatDate(medstoYptyd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 * 药品发药单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYpfydExcel> getdispensingListDetailData(List<MedstoYpfyd> list) {
		List<MedstoYpfydExcel> dataList = Lists.newArrayList();
		for (MedstoYpfyd medstoYpfyd : list) {
			MedstoYpfydExcel mye = new MedstoYpfydExcel();
			mye.setStoreName(medstoYpfyd.getStoreName());
			mye.setDrugId(medstoYpfyd.getDrugId());
			mye.setDrugName(medstoYpfyd.getDrugName());
			mye.setDrugSpec(medstoYpfyd.getDrugSpec());
			mye.setYpmrkl(medstoYpfyd.getYpmrkl());
			mye.setYbtybm(medstoYpfyd.getYbtybm());
			mye.setIsjbyw(medstoYpfyd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYpfyd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYpfyd.getCmedCode());
			mye.setDrugDetailType(medstoYpfyd.getDrugDetailType());
			mye.setDoseCode(medstoYpfyd.getDoseCode());
			mye.setPackageUnit(medstoYpfyd.getPackageUnit());
			mye.setPackageQty(medstoYpfyd.getPackageQty());
			mye.setSalesUnit(medstoYpfyd.getSalesUnit());
			mye.setSalesQty(medstoYpfyd.getSalesQty());
			mye.setSalesRelation(medstoYpfyd.getSalesRelation());
			mye.setProducerName(medstoYpfyd.getProducerName());
			mye.setCostPrice(medstoYpfyd.getCostPrice());
			mye.setSalePrice(medstoYpfyd.getSalePrice());
			mye.setCostAmt(medstoYpfyd.getCostAmt());
			mye.setSaleAmt(medstoYpfyd.getSaleAmt());
			mye.setBatchNo(medstoYpfyd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYpfyd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYpfyd.getSdrughabitat());
			mye.setSratificationno(medstoYpfyd.getSratificationno());
			mye.setDdateproduce(medstoYpfyd.getDdateproduce() == null ? "" : DateUtils.formatDate(medstoYpfyd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYpfyd.getExpiry() == null ? "" : DateUtils.formatDate(medstoYpfyd.getExpiry(), "yyyy-MM-dd"));
			mye.setPatientname(medstoYpfyd.getPatientname());
			mye.setPatientage(medstoYpfyd.getPatientage());
			mye.setPatientsex(medstoYpfyd.getPatientsex());
			mye.setNumber(medstoYpfyd.getNumber());
			mye.setPatientid(medstoYpfyd.getPatientid());
			mye.setOutpatientid(medstoYpfyd.getOutpatientid());
			mye.setOutpatientdoctor(medstoYpfyd.getOutpatientdoctor());
			mye.setPharmacist(medstoYpfyd.getPharmacist());
			mye.setFydatetime(medstoYpfyd.getFydatetime() == null ? "" : DateUtils.formatDate(medstoYpfyd.getFydatetime(), "yyyy-MM-dd"));
			mye.setScrq(medstoYpfyd.getScrq() == null ? "" : DateUtils.formatDate(medstoYpfyd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}


	/**
	 * 药品出库明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoRkzjlExcel> getDrugInDetaileData(List<MedstoRkzjl> list) {
		List<MedstoRkzjlExcel> dataList = Lists.newArrayList();
		for (MedstoRkzjl medstoRkzjl : list) {
			MedstoRkzjlExcel mye = new MedstoRkzjlExcel();
			mye.setJgbm(medstoRkzjl.getJgbm());
			mye.setStoreId(medstoRkzjl.getStoreId());
			mye.setStoreName(medstoRkzjl.getStoreName());
			mye.setEntryNo(medstoRkzjl.getEntryNo());
			mye.setDeliveryNo(medstoRkzjl.getDeliveryNo());
			mye.setOperName(medstoRkzjl.getOperName());
			mye.setCheckName(medstoRkzjl.getCheckName());
			mye.setCheckFlag(medstoRkzjl.getCheckFlag()=="0"?"未审核":"已审核");
			mye.setFph(medstoRkzjl.getFph());
			mye.setRkrq(medstoRkzjl.getRkrq() == null ? "" : DateUtils.formatDate(medstoRkzjl.getRkrq(), "yyyy-MM-dd"));
			mye.setLsje(medstoRkzjl.getLsje());
			mye.setProducerName(medstoRkzjl.getProducerName());
			mye.setKprq(medstoRkzjl.getMedstoYprkmxNew().getKprq() == null ? "" : DateUtils.formatDate(medstoRkzjl.getMedstoYprkmxNew().getKprq(), "yyyy-MM-dd"));
			mye.setDprq(medstoRkzjl.getMedstoYprkmxNew().getDprq() == null ? "" : DateUtils.formatDate(medstoRkzjl.getMedstoYprkmxNew().getDprq(), "yyyy-MM-dd"));
			mye.setCzdm(medstoRkzjl.getMedstoYprkmxNew().getCzdm());
			mye.setCdIdm(medstoRkzjl.getMedstoYprkmxNew().getCdIdm());
			mye.setGgIdm(medstoRkzjl.getMedstoYprkmxNew().getGgIdm());
			mye.setDrugName(medstoRkzjl.getMedstoYprkmxNew().getDrugName());
			mye.setDrugSpec(medstoRkzjl.getMedstoYprkmxNew().getDrugSpec());
			mye.setYpdm(medstoRkzjl.getMedstoYprkmxNew().getYpdm());
			mye.setPcxh(medstoRkzjl.getMedstoYprkmxNew().getPcxh());
			mye.setExpiry(medstoRkzjl.getMedstoYprkmxNew().getExpiry() == null ? "" : DateUtils.formatDate(medstoRkzjl.getMedstoYprkmxNew().getExpiry(), "yyyy-MM-dd"));
			mye.setPh(medstoRkzjl.getMedstoYprkmxNew().getPh());
			mye.setYkxs(medstoRkzjl.getMedstoYprkmxNew().getYkxs());
			mye.setYpdw(medstoRkzjl.getMedstoYprkmxNew().getYpdw());
			mye.setDwxs(medstoRkzjl.getMedstoYprkmxNew().getDwxs());
			mye.setRksl(medstoRkzjl.getMedstoYprkmxNew().getRksl());
			mye.setCzsl(medstoRkzjl.getMedstoYprkmxNew().getCzsl());
			mye.setYpkl(medstoRkzjl.getMedstoYprkmxNew().getYpkl());
			mye.setYpjj(medstoRkzjl.getMedstoYprkmxNew().getYpjj());
			mye.setYpfj(medstoRkzjl.getMedstoYprkmxNew().getYpfj());
			mye.setYlsj(medstoRkzjl.getMedstoYprkmxNew().getYlsj());
			mye.setJjje(medstoRkzjl.getMedstoYprkmxNew().getJjje());
			mye.setJxce(medstoRkzjl.getMedstoYprkmxNew().getJxce());
			mye.setXgxh(medstoRkzjl.getMedstoYprkmxNew().getXgxh());
			mye.setDpbz(medstoRkzjl.getMedstoYprkmxNew().getDpbz());
			mye.setMrzbj(medstoRkzjl.getMedstoYprkmxNew().getMrzbj());
			mye.setZbqh(medstoRkzjl.getMedstoYprkmxNew().getZbqh());
			mye.setZbdw(medstoRkzjl.getMedstoYprkmxNew().getZbdw());
			mye.setYpmrkl(medstoRkzjl.getMedstoYprkmxNew().getYpmrkl());
			mye.setScrq(medstoRkzjl.getMedstoYprkmxNew().getScrq() == null ? "" : DateUtils.formatDate(medstoRkzjl.getMedstoYprkmxNew().getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 * 药品库存明细---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<MedstoYpkcmxListExcel> getmedstoYpkcmxListData(List<MedstoYpkcmx> list) {
		List<MedstoYpkcmxListExcel> dataList = Lists.newArrayList();
		for(MedstoYpkcmx medstoYpkcmx :list){
			MedstoYpkcmxListExcel psr = new MedstoYpkcmxListExcel();
			psr.setJgbm(medstoYpkcmx.getJgbm());
			psr.setStoreId(medstoYpkcmx.getStoreId());
			psr.setStoreName(medstoYpkcmx.getStoreName());
			psr.setDrugId(medstoYpkcmx.getDrugId());
			psr.setYbtybm(medstoYpkcmx.getYbtybm());
			psr.setIsjbyw(medstoYpkcmx.getIsjbyw()=="1"?"是":"否");
			psr.setKss(medstoYpkcmx.getKss()=="1"?"是":"否");
			psr.setCmedCode(medstoYpkcmx.getCmedCode());
			psr.setDrugDetailType(medstoYpkcmx.getDrugDetailType());
			psr.setDoseCode(medstoYpkcmx.getDoseCode());
			psr.setDrugName(medstoYpkcmx.getDrugName());
			psr.setDrugSpec(medstoYpkcmx.getDrugSpec());
			psr.setPackageUnit(medstoYpkcmx.getPackageUnit());
			psr.setPackageQty(medstoYpkcmx.getPackageQty());
			psr.setSalesUnit(medstoYpkcmx.getSalesUnit());
			psr.setSalesQty(medstoYpkcmx.getSalesQty());
			psr.setSalesRelation(medstoYpkcmx.getSalesRelation());
			psr.setProducerName(medstoYpkcmx.getProducerName());
			psr.setCostPrice(medstoYpkcmx.getCostPrice());
			psr.setSalePrice(medstoYpkcmx.getSalePrice());
			psr.setCostAmt(medstoYpkcmx.getCostAmt());
			psr.setSaleAmt(medstoYpkcmx.getSaleAmt());
			psr.setBatchNo(medstoYpkcmx.getBatchNo());
			psr.setSdrugmanufacturers(medstoYpkcmx.getSdrugmanufacturers());
			psr.setSdrughabitat(medstoYpkcmx.getSdrughabitat());
			psr.setSratificationno(medstoYpkcmx.getSratificationno());
			psr.setDdateproduce(DateUtils.formatDate(medstoYpkcmx.getDdateproduce(), "yyyy-MM-dd"));
			psr.setExpiry(DateUtils.formatDate(medstoYpkcmx.getExpiry(),"yyyy-MM-dd"));
			psr.setScrq(DateUtils.formatDate(medstoYpkcmx.getScrq(),"yyyy-MM-dd"));
			dataList.add(psr);
		}
		return dataList;
	}
}
