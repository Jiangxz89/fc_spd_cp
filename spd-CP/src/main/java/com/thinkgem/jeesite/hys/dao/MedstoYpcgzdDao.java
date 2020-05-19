/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.HashMap;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgzd;

/**
 * 药库采购单DAO接口
 * @author sutianqi
 * @version 2018-07-31
 */
@MyBatisDao
public interface MedstoYpcgzdDao extends CrudDao<MedstoYpcgzd> {
	public int updateStatus(MedstoYpcgzd medstoYpcgzd);
	public List<HashMap<String, String>> getYpcgzdByHospAndDayTime(MedstoYpcgzd medstoYpcgzd);
	public MedstoYpcgzd getOne(MedstoYpcgzd medstoYpcgzd);
}