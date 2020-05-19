/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import com.thinkgem.jeesite.hys.dao.MedstoYpckmxDao;
import com.thinkgem.jeesite.hys.entity.MedstoYpckmx;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 药品出库明细Service
 * @author zxh
 * @version 2019-05-07
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpckmxService extends CrudService<MedstoYpckmxDao, MedstoYpckmx> {

	public MedstoYpckmx get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpckmx> findList(MedstoYpckmx medstoYpckmx) {
		return super.findList(medstoYpckmx);
	}
	
	public Page<MedstoYpckmx> findPage(Page<MedstoYpckmx> page, MedstoYpckmx medstoYpckmx) {
		return super.findPage(page, medstoYpckmx);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpckmx medstoYpckmx) {
		super.save(medstoYpckmx);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpckmx medstoYpckmx) {
		super.delete(medstoYpckmx);
	}
	
}