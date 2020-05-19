/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgzd;
import com.thinkgem.jeesite.hys.dao.MedstoYpcgzdDao;

/**
 * 药库采购单Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpcgzdService extends CrudService<MedstoYpcgzdDao, MedstoYpcgzd> {

	public MedstoYpcgzd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpcgzd> findList(MedstoYpcgzd medstoYpcgzd) {
		return super.findList(medstoYpcgzd);
	}
	
	public Page<MedstoYpcgzd> findPage(Page<MedstoYpcgzd> page, MedstoYpcgzd medstoYpcgzd) {
		return super.findPage(page, medstoYpcgzd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpcgzd medstoYpcgzd) {
		super.save(medstoYpcgzd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpcgzd medstoYpcgzd) {
		super.delete(medstoYpcgzd);
	}
	
	/**
	 * 更新药品采购单状态
	 */
	@Transactional(readOnly = false)
	public int updateStatus(MedstoYpcgzd medstoYpcgzd){
		return dao.updateStatus(medstoYpcgzd);
	}
	
	/**
	 * 查询某家医院某天的药品采购单的状态
	 * @param medstoYpcgzd
	 * @return
	 */
	public List<HashMap<String, String>> getYpcgzdByHospAndDayTime(MedstoYpcgzd medstoYpcgzd) {
		return dao.getYpcgzdByHospAndDayTime(medstoYpcgzd);
	}
	
	/**
	 * 获取药品订单信息
	 * @param medstoYpcgzd
	 * @return
	 */
	public MedstoYpcgzd getOne(MedstoYpcgzd medstoYpcgzd) {
		return dao.getOne(medstoYpcgzd);
	}
}