/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.mozilla.javascript.tools.idswitch.FileBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.ChineseCharToEnUtils;
import com.thinkgem.jeesite.hys.entity.PdProduct;
import com.thinkgem.jeesite.hys.utils.CommonUtils;
import com.thinkgem.jeesite.hys.dao.PdProductDao;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品表Service
 * @author sutianqi
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PdProductService extends CrudService<PdProductDao, PdProduct> {
	@Autowired
	PdProductDao pdProductDao;
	
	@Autowired
	private SqlSession sqlsession;

	public PdProduct get(String id) {
		return super.get(id);
	}
	
	public List<PdProduct> findList(PdProduct pdProduct) {
		return super.findList(pdProduct);
	}
	
	public Page<PdProduct> findPage(Page<PdProduct> page, PdProduct pdProduct) {
		return super.findPage(page, pdProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProduct pdProduct) {
		super.save(pdProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProduct pdProduct) {
		super.delete(pdProduct);
	}
	
	@Transactional(readOnly = false)
	public void update(PdProduct pdProduct) {
		pdProductDao.update(pdProduct);
	}
	
	public List<PdProduct> checkCorrelation(PdProduct pdProduct){
		return pdProductDao.checkCorrelation(pdProduct);
	}
	
	
	
	@Transactional(readOnly = false)
	public String batchSave(List<PdProduct> list){
		List<PdProduct> l = eliminateRepeating(list);
		String rowsKey = (l.get(l.size()-1)).getRowsKey();
		l.remove(l.size()-1);
		for(int i = 0 ; i < l.size() ; i ++){
			String id = CommonUtils.getRandomString(32);
			l.get(i).setId(id); 
			l.get(i).setUpdateDate(new Date());
			l.get(i).setUpdateBy(UserUtils.getUser());
			
		//	pdProductDao.importSave(pdProduct);
		}
		try{
			pdProductDao.importSave(l);
		}catch(Exception e){
			System.out.println(e);
		}
		return rowsKey;
	}
	
	//根据Number去重
		private List<PdProduct> eliminateRepeating(List<PdProduct> list){
			List<PdProduct> idList = pdProductDao.findAllList(new PdProduct());
			PdProduct rowsProd = new PdProduct();
			String rowsKey = "";
			for(int i = 0 ; i < list.size() ; i ++){
				for(int k = 0 ; k < list.size() ; k ++){
					if(k != i){
						boolean c = list.get(i).getNumber().equals(list.get(k).getNumber());
						if(c){
							rowsKey += list.get(i).getRows()+",";
							list.remove(i);
							i -= 1;
							break;
						}
					}
				}
				
			}
			for(int i = 0 ; i < list.size() ; i ++){
				for(int j = 0 ; j < idList.size() ; j ++){
					String num1 = list.get(i).getNumber();
					String num2 = idList.get(j).getNumber();
					boolean b = num2.equals(num1);
					if(b){
						rowsKey += list.get(i).getRows()+",";
						list.remove(i);
						i -= 1;
						break;
					}
				}
			}
			rowsProd.setRowsKey(rowsKey);
			list.add(rowsProd);
			return list;
		}
		
	/**
	 * 根据产品编号查询产品信息
	 * @param pdProduct
	 * @return
	 */
	public PdProduct getByNumber(PdProduct pdProduct) {
		return dao.getByNumber(pdProduct);
	}

	/**
	 * 批量导入
	 * @param dataList
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveBatchPdSupplier(List<PdProduct> dataList) {
		PdProductDao dao = sqlsession.getMapper(PdProductDao.class);
		try{
			for (PdProduct pdProduct : dataList) {
			pdProduct.setSpm(ChineseCharToEnUtils.getAllFirstLetter(pdProduct.getName()));
			pdProduct.preInsert();
			dao.insert(pdProduct);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
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
			jsonArray = jsonObject.getJSONArray("pdProductBOList");
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
				PdProduct pdProduct = convertJsonToPdProduct(jsonObj,hospitalNumber);
				PdProduct product = pdProductDao.get(pdProduct.getId());
				if(product != null){
					this.update(pdProduct);
				}else{
					pdProductDao.insert(pdProduct);
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

	private PdProduct convertJsonToPdProduct(JSONObject jsonObj, String hospitalNumber){
		PdProduct pdProduct = null;//new ArrayList<PdVender>();
		if(jsonObj != null){
			pdProduct = new PdProduct();
			pdProduct.setId(jsonObj.getString("id"));
			pdProduct.setNumber(jsonObj.getString("number"));
			pdProduct.setName(jsonObj.getString("name"));
			pdProduct.setSpm(jsonObj.getString("spm"));
			pdProduct.setSpec(jsonObj.getString("spec"));
			pdProduct.setVersion(jsonObj.getString("version"));
			pdProduct.setUnit(jsonObj.getString("unit"));
			pdProduct.setGroupId(jsonObj.getString("groupId"));
			pdProduct.setCategoryId(jsonObj.getString("categoryId"));
			pdProduct.setType(jsonObj.getString("type"));
			pdProduct.setVenderId(jsonObj.getString("venderId"));
			pdProduct.setSupplierId(jsonObj.getString("supplierId"));
			pdProduct.setPurPrice(StringUtils.isEmpty(jsonObj.getString("pruPrice")) ? null : Double.parseDouble(jsonObj.getString("pruPrice")));
			pdProduct.setSellingPrice(StringUtils.isEmpty(jsonObj.getString("sellingPrice")) ? null : Double.parseDouble(jsonObj.getString("sellingPrice")));
			pdProduct.setRegistration(jsonObj.getString("registration"));
			pdProduct.setDescription(jsonObj.getString("description"));
			pdProduct.setImgAuthSite(jsonObj.getString("imgAuthSite"));
			pdProduct.setImgAuthNum(jsonObj.getString("imgAuthNumber"));
			pdProduct.setImgAuthDate(StringUtils.isEmpty(jsonObj.getString("imgAuthDate")) ? null : DateUtils.parseDate(jsonObj.getString("imgAuthDate")));

			pdProduct.setImgRegister1Site(jsonObj.getString("imgRegisterSite1"));
			pdProduct.setImgRegister1Num(jsonObj.getString("imgRegisterNumber1"));
			pdProduct.setImgRegister1Date(StringUtils.isEmpty(jsonObj.getString("imgRegisterDate1")) ? null : DateUtils.parseDate(jsonObj.getString("imgRegisterDate1")));
			pdProduct.setImgRegister2Site(jsonObj.getString("imgRegisterSite2"));
			pdProduct.setImgRegister2Num(jsonObj.getString("imgRegisterNumber2"));
			pdProduct.setImgRegister2Date(StringUtils.isEmpty(jsonObj.getString("imgRegisterDate2")) ? null : DateUtils.parseDate(jsonObj.getString("imgRegisterDate2")));
			pdProduct.setImgRegister3Site(jsonObj.getString("imgRegisterSite3"));
			pdProduct.setImgRegister3Num(jsonObj.getString("imgRegisterNumber3"));
			pdProduct.setImgRegister3Date(StringUtils.isEmpty(jsonObj.getString("imgRegisterDate3")) ? null : DateUtils.parseDate(jsonObj.getString("imgRegisterDate3")));
			pdProduct.setImgLicense1Site(jsonObj.getString("imgLicenseSite1"));
			pdProduct.setImgLicense1Num(jsonObj.getString("imgLicenseNumber1"));
			pdProduct.setImgLicense1Date(StringUtils.isEmpty(jsonObj.getString("imgLicenseDate1")) ? null : DateUtils.parseDate(jsonObj.getString("imgLicenseDate1")));
			pdProduct.setImgLicense2Site(jsonObj.getString("imgLicenseSite2"));
			pdProduct.setImgLicense2Num(jsonObj.getString("imgLicenseNumber2"));
			pdProduct.setImgLicense2Date(StringUtils.isEmpty(jsonObj.getString("imgLicenseDate2")) ? null : DateUtils.parseDate(jsonObj.getString("imgLicenseDate2")));
			pdProduct.setImgLicense3Site(jsonObj.getString("imgLicenseSite3"));
			pdProduct.setImgLicense3Num(jsonObj.getString("imgLicenseNumber3"));
			pdProduct.setImgLicense3Date(StringUtils.isEmpty(jsonObj.getString("imgLicenseDate3")) ? null : DateUtils.parseDate(jsonObj.getString("imgLicenseDate3")));
			pdProduct.setImgLicense4Site(jsonObj.getString("imgLicenseSite4"));
			pdProduct.setImgLicense4Num(jsonObj.getString("imgLicenseNumber4"));
			pdProduct.setImgLicense4Date(StringUtils.isEmpty(jsonObj.getString("imgLicenseDate4")) ? null : DateUtils.parseDate(jsonObj.getString("imgLicenseDate4")));
			pdProduct.setImgProductSite1(jsonObj.getString("imgProduct1"));
			pdProduct.setImgProductSite2(jsonObj.getString("imgProduct2"));
			pdProduct.setImgProductSite3(jsonObj.getString("imgProduct3"));

			pdProduct.setRemarks(jsonObj.getString("remarks"));
			pdProduct.setDelFlag(jsonObj.getString("delFlag"));

			String officeCode = jsonObj.getString("officeCode");
			if(StringUtils.isBlank(officeCode)){
				pdProduct.setHospitalNumber(hospitalNumber);
			}else{
				pdProduct.setHospitalNumber(officeCode);
			}

			pdProduct.setCreateDate(new Date());
			pdProduct.setUpdateDate(new Date());

		}
		return pdProduct;
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

	/**
	 * 查询显示优化
	 * @param page
	 * @param pdProduct
	 * @return
	 */
	public Page<PdProduct> basicFindPage(Page<PdProduct> page, PdProduct pdProduct) {
		pdProduct.setPage(page);
		page.setList(pdProductDao.basicFindList(pdProduct));
		return page;
	}

	/**
	 * 提供select2 查询的方法
	 * @param pdProduct
	 * @return
	 */
	public List<PdProduct> findSelectList(PdProduct pdProduct) {
		return  pdProductDao.findSelectList(pdProduct);
	}
}