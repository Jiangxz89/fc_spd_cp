/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdSupplierMidHospital;
import com.thinkgem.jeesite.hys.dao.PdSupplierMidHospitalDao;

/**
 * 供应商医院关联表Service
 * @author sutianqi
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PdSupplierMidHospitalService extends CrudService<PdSupplierMidHospitalDao, PdSupplierMidHospital> {

	public PdSupplierMidHospital get(String id) {
		return super.get(id);
	}
	
	public List<PdSupplierMidHospital> findList(PdSupplierMidHospital pdSupplierMidHospital) {
		return super.findList(pdSupplierMidHospital);
	}
	
	public Page<PdSupplierMidHospital> findPage(Page<PdSupplierMidHospital> page, PdSupplierMidHospital pdSupplierMidHospital) {
		return super.findPage(page, pdSupplierMidHospital);
	}
	
	@Transactional(readOnly = false)
	public void save(PdSupplierMidHospital pdSupplierMidHospital) {
		super.save(pdSupplierMidHospital);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdSupplierMidHospital pdSupplierMidHospital) {
		super.delete(pdSupplierMidHospital);
	}
	
	/**
	 * 查询供应商关联的医院(带医院编号)
	 * @param pdSupplierMidHospital
	 * @return
	 */
	public List<PdSupplierMidHospital> findListData(PdSupplierMidHospital pdSupplierMidHospital) {
		return dao.findListData(pdSupplierMidHospital);
	}
	
}