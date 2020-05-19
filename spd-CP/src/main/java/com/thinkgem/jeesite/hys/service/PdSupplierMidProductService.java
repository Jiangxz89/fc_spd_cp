/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.dao.PdSupplierMidProductDao;
import com.thinkgem.jeesite.hys.entity.PdSupplierMidProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 供应商医院关联表Service
 * @author sutianqi
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PdSupplierMidProductService extends CrudService<PdSupplierMidProductDao, PdSupplierMidProduct> {

	public PdSupplierMidProduct get(String id) {
		return super.get(id);
	}
	
	public List<PdSupplierMidProduct> findList(PdSupplierMidProduct pdSupplierMidProduct) {
		return super.findList(pdSupplierMidProduct);
	}
	
	public Page<PdSupplierMidProduct> findPage(Page<PdSupplierMidProduct> page, PdSupplierMidProduct pdSupplierMidProduct) {
		return super.findPage(page, pdSupplierMidProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(PdSupplierMidProduct pdSupplierMidProduct) {
		super.save(pdSupplierMidProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdSupplierMidProduct pdSupplierMidProduct) {
		super.delete(pdSupplierMidProduct);
	}
	
	/**
	 * 查询供应商关联的产品
	 * @param pdSupplierMidProduct
	 * @return
	 */
	public List<PdSupplierMidProduct> findListData(PdSupplierMidProduct pdSupplierMidProduct) {
		return dao.findListData(pdSupplierMidProduct);
	}

	public List<PdSupplierMidProduct> findMidProductList(PdSupplierMidProduct midProduct){
		return dao.findMidProductList(midProduct);
	}
}