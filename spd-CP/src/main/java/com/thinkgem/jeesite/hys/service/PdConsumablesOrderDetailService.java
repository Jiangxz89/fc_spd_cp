/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.dao.PdConsumablesOrderDetailDao;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrder;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrderDetail;


/**
 * 耗材订单详情表Service
 * @author zhangxiaohan
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class PdConsumablesOrderDetailService extends CrudService<PdConsumablesOrderDetailDao, PdConsumablesOrderDetail> {
	
	public PdConsumablesOrderDetail get(String id) {
		return super.get(id);
	}
	
	public List<PdConsumablesOrderDetail> findList(PdConsumablesOrderDetail pdConsumablesOrderDetail) {
		return super.findList(pdConsumablesOrderDetail);
	}
	
	public Page<PdConsumablesOrderDetail> findPage(Page<PdConsumablesOrderDetail> page, PdConsumablesOrderDetail pdConsumablesOrderDetail) {
		return super.findPage(page, pdConsumablesOrderDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PdConsumablesOrderDetail pdConsumablesOrderDetail) {
		super.save(pdConsumablesOrderDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdConsumablesOrderDetail pdConsumablesOrderDetail) {
		super.delete(pdConsumablesOrderDetail);
	}
	
	/**
	 * 批量保存耗材订单详情
	 * @param pdConsumablesOrderDetails
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<PdConsumablesOrderDetail> pdConsumablesOrderDetails) {
		dao.batchSave(pdConsumablesOrderDetails);
	}
	
	/**
	 * 查询耗材种类个数
	 * @param pdConsumablesOrder
	 * @return
	 */
	public int findOrderKindSize(PdConsumablesOrder pdConsumablesOrder){
		return dao.findOrderKindSize(pdConsumablesOrder);
	}
	
}