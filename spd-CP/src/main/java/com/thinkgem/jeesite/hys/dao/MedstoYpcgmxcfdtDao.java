/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgmxcfdt;

/**
 * 药品订单拆分详情表DAO接口
 * @author zxh
 * @version 2018-10-10
 */
@MyBatisDao
public interface MedstoYpcgmxcfdtDao extends CrudDao<MedstoYpcgmxcfdt> {

	void batchSave(List<MedstoYpcgmxcfdt> medstoYpcgmxcfdtList);
	
}