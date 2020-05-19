/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import com.thinkgem.jeesite.hys.dao.MedstoRkzjlDao;
import com.thinkgem.jeesite.hys.entity.MedstoRkzjl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;


/**
 * 入库主记录Service
 * @author zxh
 * @version 2019-05-14
 */
@Service
@Transactional(readOnly = true)
public class MedstoRkzjlService extends CrudService<MedstoRkzjlDao, MedstoRkzjl> {

	public MedstoRkzjl get(String id) {
		return super.get(id);
	}
	
	public List<MedstoRkzjl> findList(MedstoRkzjl medstoRkzjl) {
		return super.findList(medstoRkzjl);
	}
	
	public Page<MedstoRkzjl> findPage(Page<MedstoRkzjl> page, MedstoRkzjl medstoRkzjl) {
		return super.findPage(page, medstoRkzjl);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoRkzjl medstoRkzjl) {
		super.save(medstoRkzjl);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoRkzjl medstoRkzjl) {
		super.delete(medstoRkzjl);
	}

	/**
	 * 药品入库明细查询
	 * @param page
	 * @param medstoRkzjl
	 * @return
	 */
    public Page<MedstoRkzjl> findDrugInDetaile(Page<MedstoRkzjl> page, MedstoRkzjl medstoRkzjl) {
		medstoRkzjl.setPage(page);
		page.setList(dao.findDrugInDetaile(medstoRkzjl));
		return page;
    }

	/**
	 * 药品入库明细查询
	 * @param medstoYpckzd
	 * @return
	 */
	public List<MedstoRkzjl> findDrugInDetaile(MedstoRkzjl medstoYpckzd) {
		return dao.findDrugInDetaile(medstoYpckzd);
	}
}