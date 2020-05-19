/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdConsumablesRt;

/**
 * 耗材退货查询表DAO接口
 * @author zhangxiaohan
 * @version 2018-07-17
 */
@MyBatisDao
public interface PdConsumablesRtDao extends CrudDao<PdConsumablesRt> {

	void batchSave(List<PdConsumablesRt> pdConsumablesRts);
	
}