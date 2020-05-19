/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.HisPurchaseOrderItem;

import java.util.List;

/**
 * his接口过来的采购订单详情DAO接口
 * @author jiangxz
 * @version 2019-05-06
 */
@MyBatisDao
public interface HisPurchaseOrderItemDao extends CrudDao<HisPurchaseOrderItem> {

    int deleteByPid(String pId);
}