/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdVender;

/**
 * 生产厂家表DAO接口
 * @author sutianqi
 * @version 2018-07-04
 */
@MyBatisDao
public interface PdVenderDao extends CrudDao<PdVender> {
	
	void deleteById(String id);
	
	PdVender getByName(PdVender pdVender);

	List<PdVender> verify(PdVender pdVender);

	PdVender findByName(PdVender pdVender);
}