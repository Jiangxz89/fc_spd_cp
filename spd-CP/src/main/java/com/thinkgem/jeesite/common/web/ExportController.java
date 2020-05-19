package com.thinkgem.jeesite.common.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.hys.entity.*;
import com.thinkgem.jeesite.hys.entity.execl.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;

/**
 * Excel导出controller
 * @author Mr.Wang
 *
 */
@RestController
@RequestMapping(value = "${adminPath}/excelExport/")
public class ExportController extends BaseController {
	/**
	 * 药品出库明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "drugOutDetaileExport", method=RequestMethod.POST)
	public void drugOutDetaileExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品出库明细"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYpckzd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpckzd.class);
			new ExportExcel("药品出库明细", MedstoYpckzdExcel.class).setDataList(DataUtils.getDrugOutDetaileData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 药品调价单明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "drugDrugPriceAdjustmentExport", method=RequestMethod.POST)
	public void drugDrugPriceAdjustmentExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品调价单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYpdjd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpdjd.class);
			new ExportExcel("药品调价单明细", MedstoYpdjdExcel.class).setDataList(DataUtils.getdrugDrugPriceAdjustmentData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 药品盘点单明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "drugInventoryDetailedExport", method=RequestMethod.POST)
	public void drugInventoryDetailedExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品盘点单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYppdd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYppdd.class);
			new ExportExcel("药品盘点单明细", MedstoYppddExcel.class).setDataList(DataUtils.getdrugInventoryDetailedData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 药品丢失单明细导出
     * @param data
     * @param request
     * @param response
     */
    @RequestMapping(value = "drugLostOrderDetaileExport", method=RequestMethod.POST)
    public void drugLostOrderDetaileExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
        String fileName = "药品丢失单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
        try {
            List<MedstoYpbsd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpbsd.class);
            new ExportExcel("药品丢失单明细", MedstoYpbsdExcel.class).setDataList(DataUtils.getDrugLostOrderDetaileData(list)).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 药品退药单明细导出
     * @param data
     * @param request
     * @param response
     */
    @RequestMapping(value = "drugReturnBillDetaileExport", method=RequestMethod.POST)
    public void drugReturnBillDetaileExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
        String fileName = "药品退药单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
        try {
            List<MedstoYptyd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYptyd.class);
            new ExportExcel("药品退药单明细", MedstoYptydExcel.class).setDataList(DataUtils.getDrugReturnBillDetaileData(list)).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * 药品发药单明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "dispensingListDetailExport", method=RequestMethod.POST)
	public void dispensingListDetailExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品发药单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYpfyd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpfyd.class);
			new ExportExcel("药品发药单明细", MedstoYpfydExcel.class).setDataList(DataUtils.getdispensingListDetailData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 药品入库明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "drugInDetaileExport", method=RequestMethod.POST)
	public void drugInDetaileExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品入库明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoRkzjl> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoRkzjl.class);
			new ExportExcel("药品入库明细", MedstoRkzjlExcel.class).setDataList(DataUtils.getDrugInDetaileData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 药品库存明细导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "medstoYpkcmxList", method= RequestMethod.POST)
	public void medstoYpkcmxListExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品库存明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYpkcmx> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpkcmx.class);
			new ExportExcel("药品库存明细", MedstoYpkcmxListExcel.class).setDataList(DataUtils.getmedstoYpkcmxListData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}