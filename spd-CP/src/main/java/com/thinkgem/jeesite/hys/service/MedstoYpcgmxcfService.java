/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgmxcf;
import com.thinkgem.jeesite.hys.dao.MedstoYpcgmxcfDao;

/**
 * 药品订单拆分表Service
 * @author zxh
 * @version 2018-10-10
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpcgmxcfService extends CrudService<MedstoYpcgmxcfDao, MedstoYpcgmxcf> {

	public MedstoYpcgmxcf get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpcgmxcf> findList(MedstoYpcgmxcf medstoYpcgmxcf) {
		return super.findList(medstoYpcgmxcf);
	}
	
	public Page<MedstoYpcgmxcf> findPage(Page<MedstoYpcgmxcf> page, MedstoYpcgmxcf medstoYpcgmxcf) {
		return super.findPage(page, medstoYpcgmxcf);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpcgmxcf medstoYpcgmxcf) {
		super.save(medstoYpcgmxcf);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpcgmxcf medstoYpcgmxcf) {
		super.delete(medstoYpcgmxcf);
	}
	
	/**
	 * 批量添加药品订单拆分
	 * @param medstoYpcgmxcfs
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<MedstoYpcgmxcf> medstoYpcgmxcfs) {
		dao.batchSave(medstoYpcgmxcfs);
	}
	
	/**
	 * 获取添加药品订单详情
	 * @param medstoYpcgmxcf
	 * @return
	 */
	public MedstoYpcgmxcf getOne(MedstoYpcgmxcf medstoYpcgmxcf) {
		return dao.getOne(medstoYpcgmxcf);
	}
	
}