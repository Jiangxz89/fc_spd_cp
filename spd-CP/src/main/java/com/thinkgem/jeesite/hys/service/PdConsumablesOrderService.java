/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrder;
import com.thinkgem.jeesite.hys.dao.PdConsumablesOrderDao;

/**
 * 耗材订单表Service
 * @author zhangxiaohan
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class PdConsumablesOrderService extends CrudService<PdConsumablesOrderDao, PdConsumablesOrder> {

	public PdConsumablesOrder get(String id) {
		return super.get(id);
	}
	
	public List<PdConsumablesOrder> findList(PdConsumablesOrder pdConsumablesOrder) {
		return super.findList(pdConsumablesOrder);
	}
	
	public Page<PdConsumablesOrder> findPage(Page<PdConsumablesOrder> page, PdConsumablesOrder pdConsumablesOrder) {
		return super.findPage(page, pdConsumablesOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(PdConsumablesOrder pdConsumablesOrder) {
		super.save(pdConsumablesOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdConsumablesOrder pdConsumablesOrder) {
		super.delete(pdConsumablesOrder);
	}
	
	/**
	 * 修改耗材订单的状态
	 * @param pdConsumablesOrder
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateStatus(PdConsumablesOrder pdConsumablesOrder) {
		return dao.updateStatus(pdConsumablesOrder);
	}
	
	/**
	 * 查询耗材订单列表
	 * @param page
	 * @param pdConsumablesOrder
	 * @return
	 */
	public Page<PdConsumablesOrder> findDataList(Page<PdConsumablesOrder> page, PdConsumablesOrder pdConsumablesOrder) {
		pdConsumablesOrder.setPage(page);
		page.setList(dao.findDataList(pdConsumablesOrder));
		return page;
	}
	
	/**
	 * 获取订单详细信息
	 * @param pdConsumablesOrder
	 * @return
	 */
	public PdConsumablesOrder getConsumablesOrder(PdConsumablesOrder pdConsumablesOrder) {
		return dao.getConsumablesOrder(pdConsumablesOrder);
	}
	
	/**
	 * 批量保存耗材订单
	 * @param pdConsumablesOrders
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<PdConsumablesOrder> pdConsumablesOrders) {
		dao.batchSave(pdConsumablesOrders);
	}
	
	/**
	 * 批量更新耗材订单状态
	 * @param map
	 */
	@Transactional(readOnly = false)
	public void batchUpdate(Map<String, Object> map) {
		dao.batchUpdate(map);
	}
	
	/**
	 * 根据订单日期查询
	 * @param pdConsumablesOrder
	 * @return
	 */
	public List<PdConsumablesOrder> findListByOrderDate(PdConsumablesOrder pdConsumablesOrder) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 采购金额排名TOP10
	 * @return
	 */
	public List<PdConsumablesOrder> findPurchaseAmount(PdConsumablesOrder pdConsumablesOrder) {
		return dao.findPurchaseAmount(pdConsumablesOrder);
	}

	/**
	 * 为了同步spd的产品采购订单的状态(中心平台对订单进行操作改变了订单的状态)
	 * @param consumablesOrder
	 * @return
	 */
	public List<HashMap<String, String>> getOrderByHospAndDayTime(PdConsumablesOrder consumablesOrder) {
		return dao.getOrderByHospAndDayTime(consumablesOrder);
	}
}