/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.MedstoYpbsd;

import java.util.List;

/**
 * 医院药品报损丢失单DAO接口
 * @author zxh
 * @version 2019-05-09
 */
@MyBatisDao
public interface MedstoYpbsdDao extends CrudDao<MedstoYpbsd> {

    List<MedstoYpbsd> findDrugLostOrderDetaile(MedstoYpbsd medstoYpbsd);
}