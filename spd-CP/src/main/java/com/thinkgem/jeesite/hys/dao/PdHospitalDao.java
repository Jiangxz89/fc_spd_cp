/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdHospital;

/**
 * 医院表DAO接口
 * @author sutianqi
 * @version 2018-07-13
 */
@MyBatisDao
public interface PdHospitalDao extends CrudDao<PdHospital> {

	List<PdHospital> verify(PdHospital hospital);

	PdHospital getOne(PdHospital hos);

	List<PdHospital> findCgAmountSituation(PdHospital hospital);
	
}