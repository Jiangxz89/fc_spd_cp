/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.MedstoYpkcmx;

import java.util.List;

/**
 * 药品库存明细信息DAO接口
 * @author zengyanlong
 * @version 2019-04-19
 */
@MyBatisDao
public interface MedstoYpkcmxDao extends CrudDao<MedstoYpkcmx> {

    public List<MedstoYpkcmx> findCensusQuery(MedstoYpkcmx medstoYpkcmx);
}