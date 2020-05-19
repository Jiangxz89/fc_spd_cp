/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrder;

/**
 * 耗材订单表DAO接口
 * @author zhangxiaohan
 * @version 2018-07-06
 */
@MyBatisDao
public interface PdConsumablesOrderDao extends CrudDao<PdConsumablesOrder> {

	int updateStatus(PdConsumablesOrder pdConsumablesOrder);

	List<PdConsumablesOrder> findDataList(PdConsumablesOrder pdConsumablesOrder);
	
	List<PdConsumablesOrder> findListByDate(PdConsumablesOrder pdConsumablesOrder);

	PdConsumablesOrder getConsumablesOrder(PdConsumablesOrder pdConsumablesOrder);
	
	PdConsumablesOrder getByNumber(PdConsumablesOrder pdConsumablesOrder);

	void batchSave(List<PdConsumablesOrder> pdConsumablesOrders);

	void batchUpdate(Map<String, Object> map);

	List<PdConsumablesOrder> findPurchaseAmount(PdConsumablesOrder pdConsumablesOrder);

	List<HashMap<String, String>> getOrderByHospAndDayTime(PdConsumablesOrder consumablesOrder);
	
}