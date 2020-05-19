/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.UploadUtil;
import com.thinkgem.jeesite.modules.sys.utils.SnoGerUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.mozilla.javascript.tools.idswitch.FileBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdProduct;
import com.thinkgem.jeesite.hys.entity.PdVender;
import com.thinkgem.jeesite.hys.dao.PdProductDao;
import com.thinkgem.jeesite.hys.dao.PdVenderDao;
import org.springframework.web.multipart.MultipartFile;

/**
 * 生产厂家表Service
 * @author sutianqi
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PdVenderService extends CrudService<PdVenderDao, PdVender> {

	@Autowired
	PdVenderDao pdVenderDao;
	@Autowired
	PdProductDao pdProductDao;
	@Autowired
	private SqlSession sqlSession;
	
	public PdVender get(String id) {
		return super.get(id);
	}
	
	public List<PdVender> findList(PdVender pdVender) {
		return super.findList(pdVender);
	}
	
	public Page<PdVender> findPage(Page<PdVender> page, PdVender pdVender) {
		return super.findPage(page, pdVender);
	}
	
	@Transactional(readOnly = false)
	public void save(PdVender pdVender) {
		super.save(pdVender);
	}
	
	//批量添加
	@Transactional(readOnly = false)
	public void batchSave(List<PdVender> pdVender) {
		for(PdVender v : pdVender){
			if(v.getName() != null && !v.getName().equals("")){
				v.setCreateDate(new Date());
				v.setUpdateDate(new Date());
				super.save(v);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void update(PdVender pdVender) {
		pdVenderDao.update(pdVender);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdVender pdVender) {
		super.delete(pdVender);
	}
	/**
	 * 检查生产厂家与产品是否有关联
	 * */
	@Transactional(readOnly = false)
	public List<String> checkCorrelation(String[] id){
		PdProduct pdProduct = new PdProduct();
		List<String> failVenderNameList = new ArrayList<String>();
		for(int i = 0 ; i < id.length ; i ++){
			pdProduct.setVenderId(id[i]);
			List<PdProduct> prodList = pdProductDao.checkCorrelation(pdProduct);
			if(prodList.size() <= 0){//与产品无关联,可删除
				pdVenderDao.deleteById(id[i]);
			}else{
				PdVender pdVender = pdVenderDao.get(id[i]);
				failVenderNameList.add(pdVender.getName());
			}
		}
		if(failVenderNameList.size() == 0){
			failVenderNameList.add("all-successful");
		}
		
		
		
		return failVenderNameList;
	}
	
	/**
	 * 检查生产厂家与产品是否有关联
	 * */
	@Transactional(readOnly = false)
	public String checkCorrelation(String id){
		PdProduct pdProduct = new PdProduct();
		pdProduct.setVenderId(id);
		List<PdProduct> prodList = pdProductDao.checkCorrelation(pdProduct);
		if(prodList.size() <= 0){//与产品无关联,可删除
			pdVenderDao.deleteById(id);
		}else{
			return "fail";
		}
		return "success";
	}
	
	
	public PdVender getByName(PdVender pdVender) {
		return pdVenderDao.getByName(pdVender);
	}
	
	/**
	 * 校验生产厂家名称是否唯一
	 * @param pdVender
	 * @return
	 */
	public List<PdVender> verify(PdVender pdVender) {
		return pdVenderDao.verify(pdVender);
	}
	
	/**
	 * 批量导入数据
	 * @param dataList
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveBatchPdVender(List<PdVender> dataList) {
		PdVenderDao dao =  sqlSession.getMapper(PdVenderDao.class);
		try{
			for (PdVender pdVender : dataList) {
				pdVender.preInsert();
				dao.insert(pdVender);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 校验生产厂家是否输入正确
	 * @param pdVender
	 * @return
	 */
	public PdVender findByName(PdVender pdVender) {
		return pdVenderDao.findByName(pdVender);
	}

	/**
	 * 从SPD同步数据
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = false)
	public String synDataFromSpd(String param) {

		JSONObject resultJson = new JSONObject();
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		String hospitalNumber = null;

		try{
			//接收数据
			jsonObject = JSONObject.parseObject(param);
			jsonArray = jsonObject.getJSONArray("pdVenderBOList");
			hospitalNumber = jsonObject.getString("hospitalNumber");

			if(jsonArray == null || jsonArray.size() <=0){
				resultJson.put("statusCode", "500");
				resultJson.put("msg", "同步数据为空");
				return resultJson.toString();
			}

//			if(hospitalNumber == null || "".endsWith(hospitalNumber.trim())){
//				resultJson.put("statusCode", "500");
//				resultJson.put("msg", "医院代码为空");
//				return resultJson.toString();
//			}
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "数据格式异常");
			return resultJson.toString();
		}

		try{
			for(int i = 0; i < jsonArray.size(); i++ ){
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				PdVender pdVender = convertJsonToPdVender(jsonObj,hospitalNumber);
				PdVender vender = pdVenderDao.get(pdVender.getId());
				if(vender != null){
					this.update(pdVender);
				}else{
					pdVenderDao.insert(pdVender);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "保存失败");
			return resultJson.toString();
		}
		resultJson.put("statusCode", "200");
		resultJson.put("msg", "success");

		return resultJson.toString();
	}

	/**
	 * 同步产品厂家图片
	 * @param imageFile
	 * @return
	 */
	public String synFileFromSpd(MultipartFile imageFile){
		String url = "";
		if(imageFile != null){
			String fullPath = Global.getUserfilesBaseDir()+ UploadUtil.IMAGE_DIR +"spd/"+ imageFile.getOriginalFilename();
			url = UploadUtil.IMAGE_DIR+"spd/"+ imageFile.getOriginalFilename();//文件上传的url
			try {
				createDir(fullPath);
				imageFile.transferTo(new File(fullPath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return "";
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}

		}
		return url;
	}

	private PdVender convertJsonToPdVender(JSONObject jsonObj, String hospitalNumber){
		PdVender pdVender = null;//new ArrayList<PdVender>();
		if(jsonObj != null){
			pdVender = new PdVender();
			pdVender.setId(jsonObj.getString("id"));
			pdVender.setName(jsonObj.getString("name"));
			pdVender.setPinyin(jsonObj.getString("pinyin"));
			pdVender.setRemarks(jsonObj.getString("remarks"));
			pdVender.setDelFlag(jsonObj.getString("delFlag"));
			pdVender.setHospitalNumber(hospitalNumber);
			pdVender.setCreateDate(new Date());
			pdVender.setUpdateDate(new Date());

			String officeCode = jsonObj.getString("officeCode");
			if(StringUtils.isBlank(officeCode)){
				pdVender.setHospitalNumber(hospitalNumber);
			}else{
				pdVender.setHospitalNumber(officeCode);
			}

			pdVender.setImgLicense1Site(jsonObj.getString("licenceSite1"));
			pdVender.setImgLicense1Num(jsonObj.getString("licenceNum1"));
			pdVender.setImgLicense1Date((jsonObj.getString("licenceDate1") != null && !"".equals(jsonObj.getString("licenceDate1"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate1")) : null);
			pdVender.setImgLicense1Name(jsonObj.getString("licenceName1"));

			pdVender.setImgLicense2Site(jsonObj.getString("licenceSite2"));
			pdVender.setImgLicense2Num(jsonObj.getString("licenceNum2"));
			pdVender.setImgLicense2Date((jsonObj.getString("licenceDate2") != null && !"".equals(jsonObj.getString("licenceDate2"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate2")) : null);
			pdVender.setImgLicense2Name(jsonObj.getString("licenceName2"));

			pdVender.setImgLicense3Site(jsonObj.getString("licenceSite3"));
			pdVender.setImgLicense3Num(jsonObj.getString("licenceNum3"));
			pdVender.setImgLicense3Date((jsonObj.getString("licenceDate3") != null && !"".equals(jsonObj.getString("licenceDate3"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate3")) : null);
			pdVender.setImgLicense3Name(jsonObj.getString("licenceName3"));

			pdVender.setImgLicense4Site(jsonObj.getString("licenceSite4"));
			pdVender.setImgLicense4Num(jsonObj.getString("licenceNum4"));
			pdVender.setImgLicense4Date((jsonObj.getString("licenceDate4") != null && !"".equals(jsonObj.getString("licenceDate4"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate4")) : null);
			pdVender.setImgLicense4Name(jsonObj.getString("licenceName4"));

			pdVender.setImgLicense5Site(jsonObj.getString("licenceSite5"));
			pdVender.setImgLicense5Num(jsonObj.getString("licenceNum5"));
			pdVender.setImgLicense5Date((jsonObj.getString("licenceDate5") != null && !"".equals(jsonObj.getString("licenceDate5"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate5")) : null);
			pdVender.setImgLicense5Name(jsonObj.getString("licenceName5"));

			pdVender.setImgLicense6Site(jsonObj.getString("licenceSite6"));
			pdVender.setImgLicense6Num(jsonObj.getString("licenceNum6"));
			pdVender.setImgLicense6Date((jsonObj.getString("licenceDate6") != null && !"".equals(jsonObj.getString("licenceDate6"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate6")) : null);
			pdVender.setImgLicense6Name(jsonObj.getString("licenceName6"));

			pdVender.setImgLicense7Site(jsonObj.getString("licenceSite7"));
			pdVender.setImgLicense7Num(jsonObj.getString("licenceNum7"));
			pdVender.setImgLicense7Date((jsonObj.getString("licenceDate7") != null && !"".equals(jsonObj.getString("licenceDate7"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate7")) : null);
			pdVender.setImgLicense7Name(jsonObj.getString("licenceName7"));

			pdVender.setImgLicense8Site(jsonObj.getString("licenceSite8"));
			pdVender.setImgLicense8Num(jsonObj.getString("licenceNum8"));
			pdVender.setImgLicense8Date((jsonObj.getString("licenceDate8") != null && !"".equals(jsonObj.getString("licenceDate8"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate8")) : null);
			pdVender.setImgLicense8Name(jsonObj.getString("licenceName8"));

			pdVender.setImgLicense9Site(jsonObj.getString("licenceSite9"));
			pdVender.setImgLicense9Num(jsonObj.getString("licenceNum9"));
			pdVender.setImgLicense9Date((jsonObj.getString("licenceDate9") != null && !"".equals(jsonObj.getString("licenceDate9"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate9")) : null);
			pdVender.setImgLicense9Name(jsonObj.getString("licenceName9"));

			pdVender.setImgLicense10Site(jsonObj.getString("licenceSite10"));
			pdVender.setImgLicense10Num(jsonObj.getString("licenceNum10"));
			pdVender.setImgLicense10Date((jsonObj.getString("licenceDate10") != null && !"".equals(jsonObj.getString("licenceDate10"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate10")) : null);
			pdVender.setImgLicense10Name(jsonObj.getString("licenceName10"));

			pdVender.setImgLicense11Site(jsonObj.getString("licenceSite11"));
			pdVender.setImgLicense11Num(jsonObj.getString("licenceNum11"));
			pdVender.setImgLicense11Date((jsonObj.getString("licenceDate11") != null && !"".equals(jsonObj.getString("licenceDate11"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate11")) : null);
			pdVender.setImgLicense11Name(jsonObj.getString("licenceName11"));

			pdVender.setImgLicense12Site(jsonObj.getString("licenceSite12"));
			pdVender.setImgLicense12Num(jsonObj.getString("licenceNum12"));
			pdVender.setImgLicense12Date((jsonObj.getString("licenceDate12") != null && !"".equals(jsonObj.getString("licenceDate12"))) ? DateUtils.parseDate(jsonObj.getString("licenceDate12")) : null);
			pdVender.setImgLicense12Name(jsonObj.getString("licenceName12"));
		}
		return pdVender;
	}

	/**
	 * @功能描述:创建文件夹
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}

}