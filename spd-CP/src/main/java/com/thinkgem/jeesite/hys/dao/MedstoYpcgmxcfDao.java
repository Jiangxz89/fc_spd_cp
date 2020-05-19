/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgmxcf;

/**
 * 药品订单拆分表DAO接口
 * @author zxh
 * @version 2018-10-10
 */
@MyBatisDao
public interface MedstoYpcgmxcfDao extends CrudDao<MedstoYpcgmxcf> {

	void batchSave(List<MedstoYpcgmxcf> medstoYpcgmxcfs);

	MedstoYpcgmxcf getOne(MedstoYpcgmxcf medstoYpcgmxcf);
	
}