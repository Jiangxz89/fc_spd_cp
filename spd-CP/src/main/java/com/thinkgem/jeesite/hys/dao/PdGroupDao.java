/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdGroup;
import org.apache.ibatis.annotations.Param;

/**
 * 耗材组别表DAO接口
 * @author sutianqi
 * @version 2018-07-04
 */
@MyBatisDao
public interface PdGroupDao extends CrudDao<PdGroup> {
	PdGroup getByName(PdGroup pdGroup);

	List<PdGroup> verify(PdGroup pdGroup);

	PdGroup findByName(PdGroup pdGroup);
}