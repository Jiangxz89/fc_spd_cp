/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdConsumablesApart;
import com.thinkgem.jeesite.hys.dao.PdConsumablesApartDao;

/**
 * 耗材订单拆分表Service
 * @author zxh
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class PdConsumablesApartService extends CrudService<PdConsumablesApartDao, PdConsumablesApart> {

	public PdConsumablesApart get(String id) {
		return super.get(id);
	}
	
	public List<PdConsumablesApart> findList(PdConsumablesApart pdConsumablesApart) {
		return super.findList(pdConsumablesApart);
	}
	
	public Page<PdConsumablesApart> findPage(Page<PdConsumablesApart> page, PdConsumablesApart pdConsumablesApart) {
		return super.findPage(page, pdConsumablesApart);
	}
	
	@Transactional(readOnly = false)
	public void save(PdConsumablesApart pdConsumablesApart) {
		super.save(pdConsumablesApart);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdConsumablesApart pdConsumablesApart) {
		super.delete(pdConsumablesApart);
	}
	
	/**
	 * 批量添加耗材订单拆分
	 * @param pdConsumablesAparts
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<PdConsumablesApart> pdConsumablesAparts) {
		dao.batchSave(pdConsumablesAparts);
	}

	/**
	 * 获取拆分对象
	 * @param pdConsumablesApart
	 * @return
	 */
	public PdConsumablesApart getDetail(PdConsumablesApart pdConsumablesApart) {
		return dao.getDetail(pdConsumablesApart);
	}
}