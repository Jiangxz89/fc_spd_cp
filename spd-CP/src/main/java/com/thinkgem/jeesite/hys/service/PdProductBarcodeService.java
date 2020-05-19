/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdProductBarcode;
import com.thinkgem.jeesite.hys.dao.PdProductBarcodeDao;

/**
 * 条形码表Service
 * @author sutianqi
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PdProductBarcodeService extends CrudService<PdProductBarcodeDao, PdProductBarcode> {

	public PdProductBarcode get(String id) {
		return super.get(id);
	}
	
	public List<PdProductBarcode> findList(PdProductBarcode pdProductBarcode) {
		return super.findList(pdProductBarcode);
	}
	
	public Page<PdProductBarcode> findPage(Page<PdProductBarcode> page, PdProductBarcode pdProductBarcode) {
		return super.findPage(page, pdProductBarcode);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductBarcode pdProductBarcode) {
		super.save(pdProductBarcode);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductBarcode pdProductBarcode) {
		super.delete(pdProductBarcode);
	}
	
}