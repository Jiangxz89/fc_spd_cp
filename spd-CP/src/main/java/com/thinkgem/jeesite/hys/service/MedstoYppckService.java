/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.MedstoYppck;
import com.thinkgem.jeesite.hys.dao.MedstoYppckDao;

/**
 * 药库批次库Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYppckService extends CrudService<MedstoYppckDao, MedstoYppck> {

	public MedstoYppck get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYppck> findList(MedstoYppck medstoYppck) {
		return super.findList(medstoYppck);
	}
	
	public Page<MedstoYppck> findPage(Page<MedstoYppck> page, MedstoYppck medstoYppck) {
		return super.findPage(page, medstoYppck);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYppck medstoYppck) {
		super.save(medstoYppck);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYppck medstoYppck) {
		super.delete(medstoYppck);
	}
	
	@Transactional(readOnly = false)
	public int batchInsert(List<MedstoYppck> list){
		return dao.batchInsert(list);
	}
}