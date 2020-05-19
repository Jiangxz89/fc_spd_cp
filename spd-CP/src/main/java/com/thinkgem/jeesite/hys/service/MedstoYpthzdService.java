/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.MedstoYpthzd;
import com.thinkgem.jeesite.hys.dao.MedstoYpthzdDao;

/**
 * 药品退货账单Service
 * @author wg
 * @version 2018-08-14
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpthzdService extends CrudService<MedstoYpthzdDao, MedstoYpthzd> {

	public MedstoYpthzd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpthzd> findList(MedstoYpthzd medstoYpthzd) {
		return super.findList(medstoYpthzd);
	}
	
	public Page<MedstoYpthzd> findPage(Page<MedstoYpthzd> page, MedstoYpthzd medstoYpthzd) {
		return super.findPage(page, medstoYpthzd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpthzd medstoYpthzd) {
		super.save(medstoYpthzd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpthzd medstoYpthzd) {
		super.delete(medstoYpthzd);
	}
	
	@Transactional(readOnly = false)
	public int batchInsert(List<MedstoYpthzd> list) {
		return dao.batchInsert(list);
	}
	
}