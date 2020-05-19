/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdSupplier;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 供应商表DAO接口
 * @author sutianqi
 * @version 2018-07-04
 */
@MyBatisDao
public interface PdSupplierDao extends CrudDao<PdSupplier> {
	PdSupplier getByName(PdSupplier pdSupplier);

	List<PdSupplier> verify(PdSupplier pdSupplier);

	PdSupplier findByAccount(User user);

}