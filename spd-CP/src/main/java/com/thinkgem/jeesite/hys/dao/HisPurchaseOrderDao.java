package com.thinkgem.jeesite.hys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.HisPurchaseOrder;

import java.util.List;

/**
 * his_purchase_orderDAO接口
 * @author jiangxz
 * @version 2019-05-06
 */
@MyBatisDao
public interface HisPurchaseOrderDao extends CrudDao<HisPurchaseOrder> {

	
}