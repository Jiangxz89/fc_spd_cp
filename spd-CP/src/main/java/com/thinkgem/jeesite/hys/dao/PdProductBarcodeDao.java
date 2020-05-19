/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdProductBarcode;

/**
 * 条形码表DAO接口
 * @author sutianqi
 * @version 2018-07-04
 */
@MyBatisDao
public interface PdProductBarcodeDao extends CrudDao<PdProductBarcode> {
	
}