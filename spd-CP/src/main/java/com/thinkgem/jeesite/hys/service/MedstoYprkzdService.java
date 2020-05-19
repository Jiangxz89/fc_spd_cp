/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.MedstoYprkzd;
import com.thinkgem.jeesite.hys.dao.MedstoYprkzdDao;

/**
 * 药库入库单Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYprkzdService extends CrudService<MedstoYprkzdDao, MedstoYprkzd> {

	public MedstoYprkzd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYprkzd> findList(MedstoYprkzd medstoYprkzd) {
		return super.findList(medstoYprkzd);
	}
	
	public Page<MedstoYprkzd> findPage(Page<MedstoYprkzd> page, MedstoYprkzd medstoYprkzd) {
		return super.findPage(page, medstoYprkzd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYprkzd medstoYprkzd) {
		super.save(medstoYprkzd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYprkzd medstoYprkzd) {
		super.delete(medstoYprkzd);
	}
	
	@Transactional(readOnly = false)
	public int batchInsert(List<MedstoYprkzd> list) {
		return dao.batchInsert(list);
	}
	
}