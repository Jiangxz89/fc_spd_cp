/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdSupplierMidHospital;

/**
 * 供应商医院关联表DAO接口
 * @author sutianqi
 * @version 2018-07-04
 */
@MyBatisDao
public interface PdSupplierMidHospitalDao extends CrudDao<PdSupplierMidHospital> {
	void save(PdSupplierMidHospital pdSupplierMidHospital);
	
	void deleteBySupplierId(PdSupplierMidHospital pdSupplierMidHospital);

	List<PdSupplierMidHospital> findListData(PdSupplierMidHospital pdSupplierMidHospital);

	void batchSave(List<PdSupplierMidHospital> pdSupplierMidHospitals);
}