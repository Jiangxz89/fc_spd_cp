/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.MedstoYpcdmlk;
import com.thinkgem.jeesite.hys.dao.MedstoYpcdmlkDao;

/**
 * 药品厂家库Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpcdmlkService extends CrudService<MedstoYpcdmlkDao, MedstoYpcdmlk> {

	public MedstoYpcdmlk get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpcdmlk> findList(MedstoYpcdmlk medstoYpcdmlk) {
		return super.findList(medstoYpcdmlk);
	}
	
	public Page<MedstoYpcdmlk> findPage(Page<MedstoYpcdmlk> page, MedstoYpcdmlk medstoYpcdmlk) {
		return super.findPage(page, medstoYpcdmlk);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpcdmlk medstoYpcdmlk) {
		super.save(medstoYpcdmlk);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpcdmlk medstoYpcdmlk) {
		super.delete(medstoYpcdmlk);
	}
	
	@Transactional(readOnly = false)
	public int batchInsert(List<MedstoYpcdmlk> list) {
		return dao.batchInsert(list);
	}
	
	@Transactional(readOnly = false)
	public int clearData() {
		return dao.clearData();
	}
}