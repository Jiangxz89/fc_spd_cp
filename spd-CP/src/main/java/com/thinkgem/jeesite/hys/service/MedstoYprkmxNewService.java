/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import com.thinkgem.jeesite.hys.dao.MedstoYprkmxNewDao;
import com.thinkgem.jeesite.hys.entity.MedstoYprkmxNew;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 药品入库明细Service
 * @author zxh
 * @version 2019-05-14
 */
@Service
@Transactional(readOnly = true)
public class MedstoYprkmxNewService extends CrudService<MedstoYprkmxNewDao, MedstoYprkmxNew> {

	public MedstoYprkmxNew get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYprkmxNew> findList(MedstoYprkmxNew medstoYprkmxNew) {
		return super.findList(medstoYprkmxNew);
	}
	
	public Page<MedstoYprkmxNew> findPage(Page<MedstoYprkmxNew> page, MedstoYprkmxNew medstoYprkmxNew) {
		return super.findPage(page, medstoYprkmxNew);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYprkmxNew medstoYprkmxNew) {
		super.save(medstoYprkmxNew);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYprkmxNew medstoYprkmxNew) {
		super.delete(medstoYprkmxNew);
	}
	
}