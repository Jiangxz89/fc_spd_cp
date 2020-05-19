/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.MedstoYpcgmxcfdt;
import com.thinkgem.jeesite.hys.dao.MedstoYpcgmxcfdtDao;

/**
 * 药品订单拆分详情表Service
 * @author zxh
 * @version 2018-10-10
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpcgmxcfdtService extends CrudService<MedstoYpcgmxcfdtDao, MedstoYpcgmxcfdt> {

	public MedstoYpcgmxcfdt get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpcgmxcfdt> findList(MedstoYpcgmxcfdt medstoYpcgmxcfdt) {
		return super.findList(medstoYpcgmxcfdt);
	}
	
	public Page<MedstoYpcgmxcfdt> findPage(Page<MedstoYpcgmxcfdt> page, MedstoYpcgmxcfdt medstoYpcgmxcfdt) {
		return super.findPage(page, medstoYpcgmxcfdt);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpcgmxcfdt medstoYpcgmxcfdt) {
		super.save(medstoYpcgmxcfdt);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpcgmxcfdt medstoYpcgmxcfdt) {
		super.delete(medstoYpcgmxcfdt);
	}
	
	/**
	 * 批量添加药品订单拆分关联药品
	 * @param medstoYpcgmxcfdtList
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<MedstoYpcgmxcfdt> medstoYpcgmxcfdtList) {
		dao.batchSave(medstoYpcgmxcfdtList);
	}
	
}