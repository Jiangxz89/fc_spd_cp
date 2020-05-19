/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdCategory;
import com.thinkgem.jeesite.hys.entity.PdProduct;
import com.thinkgem.jeesite.hys.dao.PdCategoryDao;
import com.thinkgem.jeesite.hys.dao.PdProductDao;

import static com.thinkgem.jeesite.common.persistence.BaseEntity.DEL_FLAG_NORMAL;

/**
 * 耗材分类表Service
 * @author sutianqi
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PdCategoryService extends CrudService<PdCategoryDao, PdCategory> {

	@Autowired
	PdCategoryDao pdCategoryDao;
	@Autowired
	PdProductDao pdProductDao;
	
	public PdCategory get(String id) {
		return super.get(id);
	}
	
	public List<PdCategory> findList(PdCategory pdCategory) {
		return super.findList(pdCategory);
	}
	
	public Page<PdCategory> findPage(Page<PdCategory> page, PdCategory pdCategory) {
		return super.findPage(page, pdCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(PdCategory pdCategory) {
		super.save(pdCategory);
	}
	
	public boolean delVerify(PdCategory pdCategory){
		PdProduct product = new PdProduct();
		product.setCategoryId(pdCategory.getId());
		
		List<PdProduct> prodList = pdProductDao.checkCorrelation(product);
		if(prodList.size() > 0){
			return false;
		}
		return true;
		
	}
	
	@Transactional(readOnly = false)
	public void delete(PdCategory pdCategory) {
		super.delete(pdCategory);
	}
	
	@Transactional(readOnly = false)
	public void update(PdCategory pdCategory) {
		pdCategoryDao.update(pdCategory);
	}
	
	public PdCategory getByName(PdCategory pdCategory) {
		return pdCategoryDao.getByName(pdCategory);
	}
	
	/**
	 * 校验分类是否重复
	 * @param pdCategory
	 * @return
	 */
	public List<PdCategory> verify(PdCategory pdCategory) {
		return pdCategoryDao.verify(pdCategory);
	}
	
	/**
	 * 校验产品分类是否输入正确
	 * @param pdCategory
	 * @return
	 */
	public PdCategory findByName(PdCategory pdCategory) {
		return pdCategoryDao.findByName(pdCategory);
	}

	/**
	 * 从SPD同步数据
	 * @param param
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
			jsonArray = jsonObject.getJSONArray("pdCategoryBOList");
			hospitalNumber = jsonObject.getString("hospitalNumber");

			if(jsonArray == null || jsonArray.size() <=0){
				resultJson.put("statusCode", "500");
				resultJson.put("msg", "同步数据为空");
				return resultJson.toString();
			}else{
				for(int i = 0; i < jsonArray.size(); i++ ){
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					jsonObj.remove("createBy");
					jsonObj.remove("updateBy");
				}
			}

			if(hospitalNumber == null || "".endsWith(hospitalNumber.trim())){
				resultJson.put("statusCode", "500");
				resultJson.put("msg", "医院代码为空");
				return resultJson.toString();
			}
		}catch(Exception e){
			e.printStackTrace();
			resultJson.put("statusCode", "500");
			resultJson.put("msg", "数据格式异常");
			return resultJson.toString();
		}

		try{
			List<PdCategory> pdCategoryList = JSONObject.parseArray(jsonArray.toJSONString(),PdCategory.class);
			if(pdCategoryList != null && pdCategoryList.size() > 0){
				for (PdCategory pdCategory:pdCategoryList) {
//						pdCategory.setDelFlag(DEL_FLAG_NORMAL);
					pdCategory.setHospitalNumber(hospitalNumber);
					PdCategory category = pdCategoryDao.get(pdCategory.getId());

					if(category != null){
//							pdCategoryDao.deleteById(pdCategory.getId());
						this.update(pdCategory);
					}else {
						pdCategoryDao.insert(pdCategory);
					}
				}
			}else{
				resultJson.put("statusCode", "500");
				resultJson.put("msg", "数据格式异常");
				return resultJson.toString();
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
}