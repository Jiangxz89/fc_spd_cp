/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrder;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrderDetail;


/**
 * 耗材订单详情表DAO接口
 * @author zhangxiaohan
 * @version 2018-07-06
 */
@MyBatisDao
public interface PdConsumablesOrderDetailDao extends CrudDao<PdConsumablesOrderDetail> {

	void batchSave(List<PdConsumablesOrderDetail> pdConsumablesOrderDetails);

	int findOrderKindSize(PdConsumablesOrder pdConsumablesOrder);
	
}