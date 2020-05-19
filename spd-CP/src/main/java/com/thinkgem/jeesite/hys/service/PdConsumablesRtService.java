/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdConsumablesRt;
import com.thinkgem.jeesite.hys.dao.PdConsumablesRtDao;

/**
 * 耗材退货查询表Service
 * @author zhangxiaohan
 * @version 2018-07-17
 */
@Service
@Transactional(readOnly = true)
public class PdConsumablesRtService extends CrudService<PdConsumablesRtDao, PdConsumablesRt> {

	public PdConsumablesRt get(String id) {
		return super.get(id);
	}
	
	public List<PdConsumablesRt> findList(PdConsumablesRt pdConsumablesRt) {
		return super.findList(pdConsumablesRt);
	}
	
	public Page<PdConsumablesRt> findPage(Page<PdConsumablesRt> page, PdConsumablesRt pdConsumablesRt) {
		return super.findPage(page, pdConsumablesRt);
	}
	
	@Transactional(readOnly = false)
	public void save(PdConsumablesRt pdConsumablesRt) {
		super.save(pdConsumablesRt);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdConsumablesRt pdConsumablesRt) {
		super.delete(pdConsumablesRt);
	}
	
	/**
	 * 批量保存退货详情
	 * @param pdConsumablesRts
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<PdConsumablesRt> pdConsumablesRts) {
		dao.batchSave(pdConsumablesRts);
	}
	
}