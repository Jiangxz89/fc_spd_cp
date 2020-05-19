/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdConsumablesApartRelevance;

/**
 * 耗材订单拆分关联耗材表DAO接口
 * @author zxh
 * @version 2018-07-24
 */
@MyBatisDao
public interface PdConsumablesApartRelevanceDao extends CrudDao<PdConsumablesApartRelevance> {

	void batchSave(List<PdConsumablesApartRelevance> pdConsumablesApartRelevanceList);
	
}