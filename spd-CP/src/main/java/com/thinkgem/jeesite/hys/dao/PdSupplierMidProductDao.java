/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdSupplierMidHospital;
import com.thinkgem.jeesite.hys.entity.PdSupplierMidProduct;

import java.util.List;

/**
 * 供应商医院关联表DAO接口
 * @author sutianqi
 * @version 2018-07-04
 */
@MyBatisDao
public interface PdSupplierMidProductDao extends CrudDao<PdSupplierMidProduct> {
	void save(PdSupplierMidProduct pdSupplierMidProduct);
	
	void deleteBySupplierId(PdSupplierMidProduct pdSupplierMidProduct);

	List<PdSupplierMidProduct> findListData(PdSupplierMidProduct pdSupplierMidProduct);

	void batchSave(List<PdSupplierMidProduct> pdSupplierMidProduct);

	List<PdSupplierMidProduct> findMidProductList(PdSupplierMidProduct midProduc);
}