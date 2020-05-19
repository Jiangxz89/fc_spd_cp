/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdConsumablesApartRelevance;
import com.thinkgem.jeesite.hys.dao.PdConsumablesApartRelevanceDao;

/**
 * 耗材订单拆分关联耗材表Service
 * @author zxh
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class PdConsumablesApartRelevanceService extends CrudService<PdConsumablesApartRelevanceDao, PdConsumablesApartRelevance> {

	public PdConsumablesApartRelevance get(String id) {
		return super.get(id);
	}
	
	public List<PdConsumablesApartRelevance> findList(PdConsumablesApartRelevance pdConsumablesApartRelevance) {
		return super.findList(pdConsumablesApartRelevance);
	}
	
	public Page<PdConsumablesApartRelevance> findPage(Page<PdConsumablesApartRelevance> page, PdConsumablesApartRelevance pdConsumablesApartRelevance) {
		return super.findPage(page, pdConsumablesApartRelevance);
	}
	
	@Transactional(readOnly = false)
	public void save(PdConsumablesApartRelevance pdConsumablesApartRelevance) {
		super.save(pdConsumablesApartRelevance);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdConsumablesApartRelevance pdConsumablesApartRelevance) {
		super.delete(pdConsumablesApartRelevance);
	}
	
	/**
	 * 批量添加耗材订单拆分关联耗材
	 * @param pdConsumablesApartRelevanceList
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<PdConsumablesApartRelevance> pdConsumablesApartRelevanceList) {
		dao.batchSave(pdConsumablesApartRelevanceList);
	}
	
}