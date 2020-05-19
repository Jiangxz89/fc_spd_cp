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
import com.thinkgem.jeesite.hys.entity.PdGroup;
import com.thinkgem.jeesite.hys.dao.PdGroupDao;

import static com.thinkgem.jeesite.common.persistence.BaseEntity.DEL_FLAG_NORMAL;

/**
 * 耗材组别表Service
 * @author sutianqi
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PdGroupService extends CrudService<PdGroupDao, PdGroup> {
	@Autowired
	PdGroupDao pdGroupDao;

	public PdGroup get(String id) {
		return super.get(id);
	}
	
	public List<PdGroup> findList(PdGroup pdGroup) {
		return super.findList(pdGroup);
	}
	
	public Page<PdGroup> findPage(Page<PdGroup> page, PdGroup pdGroup) {
		return super.findPage(page, pdGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(PdGroup pdGroup) {
		super.save(pdGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdGroup pdGroup) {
		super.delete(pdGroup);
	}
	
	@Transactional(readOnly = false)
	public void update(PdGroup pdGroup) {
		pdGroupDao.update(pdGroup);
	}
	
	public PdGroup getByName(PdGroup pdGroup) {
		return pdGroupDao.getByName(pdGroup);
	}
	
	/**
	 * 产品组别
	 * @param pdGroup
	 * @return
	 */
	public PdGroup findByName(PdGroup pdGroup) {
		return pdGroupDao.findByName(pdGroup);
	}
	
	/**
	 * 校验组别是否唯一
	 * @param pdGroup
	 * @return
	 */
	public List<PdGroup> verify(PdGroup pdGroup) {
		return dao.verify(pdGroup);
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
			jsonArray = jsonObject.getJSONArray("pdGroupBOList");
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
			List<PdGroup> pdGroupList = JSONObject.parseArray(jsonArray.toJSONString(),PdGroup.class);
			if(pdGroupList != null && pdGroupList.size() > 0){
				for (PdGroup pdGroup:pdGroupList) {
	//						pdGroup.setDelFlag(DEL_FLAG_NORMAL);
					pdGroup.setHospitalNumber(hospitalNumber);
					PdGroup group = pdGroupDao.get(pdGroup.getId());

					if(group != null){
	//							pdGroupDao.deleteById(pdGroup.getId());
						this.update(pdGroup);
					}else{
						pdGroupDao.insert(pdGroup);
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