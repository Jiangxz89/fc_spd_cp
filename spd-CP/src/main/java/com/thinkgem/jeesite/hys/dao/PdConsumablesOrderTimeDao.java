/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrderTime;

/**
 * 耗材订单及配送查询表DAO接口
 * @author zhangxiaohan
 * @version 2018-07-12
 */
@MyBatisDao
public interface PdConsumablesOrderTimeDao extends CrudDao<PdConsumablesOrderTime> {

	void batchSave(List<PdConsumablesOrderTime> pdConsumablesOrderTimes);

	List<PdConsumablesOrderTime> findSupplierList(PdConsumablesOrderTime pdConsumablesOrderTime);

	List<PdConsumablesOrderTime> findPsAmount(PdConsumablesOrderTime pdConsumablesOrderTime);
	
}