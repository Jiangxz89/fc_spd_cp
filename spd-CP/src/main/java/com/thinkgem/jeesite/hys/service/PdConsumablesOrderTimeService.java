/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.dao.PdConsumablesOrderTimeDao;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrderTime;

/**
 * 耗材订单及配送查询表Service
 * @author zhangxiaohan
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly = true)
public class PdConsumablesOrderTimeService extends CrudService<PdConsumablesOrderTimeDao, PdConsumablesOrderTime> {

	public PdConsumablesOrderTime get(String id) {
		return super.get(id);
	}
	
	public List<PdConsumablesOrderTime> findList(PdConsumablesOrderTime pdConsumablesOrderTime) {
		return super.findList(pdConsumablesOrderTime);
	}
	
	public Page<PdConsumablesOrderTime> findPage(Page<PdConsumablesOrderTime> page, PdConsumablesOrderTime pdConsumablesOrderTime) {
		return super.findPage(page, pdConsumablesOrderTime);
	}
	
	@Transactional(readOnly = false)
	public void save(PdConsumablesOrderTime pdConsumablesOrderTime) {
		super.save(pdConsumablesOrderTime);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdConsumablesOrderTime pdConsumablesOrderTime) {
		super.delete(pdConsumablesOrderTime);
	}
	
	/**
	 * 保存耗材订单及配送
	 * @param pdConsumablesOrderTimes
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<PdConsumablesOrderTime> pdConsumablesOrderTimes) {
		dao.batchSave(pdConsumablesOrderTimes);
	}
	
	/**
	 * 根据时间查询一段时间内所有的供应商
	 * @param pdConsumablesOrderTime
	 * @return
	 */
	public List<PdConsumablesOrderTime> findSupplierList(PdConsumablesOrderTime pdConsumablesOrderTime) {
		return dao.findSupplierList(pdConsumablesOrderTime);
	}
	
	/**
	 * 企业配送金额比率
	 * @param pdConsumablesOrderTime
	 * @return
	 */
	public List<PdConsumablesOrderTime> findPsAmount(PdConsumablesOrderTime pdConsumablesOrderTime) {
		return dao.findPsAmount(pdConsumablesOrderTime);
	}
	
}