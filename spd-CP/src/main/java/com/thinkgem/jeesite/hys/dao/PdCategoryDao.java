/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdCategory;

/**
 * 耗材分类表DAO接口
 * @author sutianqi
 * @version 2018-07-04
 */
@MyBatisDao
public interface PdCategoryDao extends CrudDao<PdCategory> {
	PdCategory getByName(PdCategory pdCategory);
	
	PdCategory deleteById(@Param(value = "id")String id);

	List<PdCategory> verify(PdCategory pdCategory);

	PdCategory findByName(PdCategory pdCategory);
}