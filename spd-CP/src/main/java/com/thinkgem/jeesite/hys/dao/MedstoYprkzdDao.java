/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.MedstoYprkzd;

/**
 * 药库入库单DAO接口
 * @author sutianqi
 * @version 2018-07-31
 */
@MyBatisDao
public interface MedstoYprkzdDao extends CrudDao<MedstoYprkzd> {
	public int batchInsert(List<MedstoYprkzd> list);
}