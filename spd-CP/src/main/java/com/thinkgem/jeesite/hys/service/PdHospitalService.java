/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdHospital;
import com.thinkgem.jeesite.hys.dao.PdHospitalDao;

/**
 * 医院表Service
 * @author sutianqi
 * @version 2018-07-13
 */
@Service
@Transactional(readOnly = true)
public class PdHospitalService extends CrudService<PdHospitalDao, PdHospital> {
	@Autowired
	PdHospitalDao pdHospitalDao;
	
	@Autowired
	private SqlSession sqlSession;

	public PdHospital get(String id) {
		return super.get(id);
	}
	
	public List<PdHospital> findList(PdHospital pdHospital) {
		return super.findList(pdHospital);
	}
	
	public Page<PdHospital> findPage(Page<PdHospital> page, PdHospital pdHospital) {
		return super.findPage(page, pdHospital);
	}
	
	@Transactional(readOnly = false)
	public void save(PdHospital pdHospital) {
		super.save(pdHospital);
	}
	
	@Transactional(readOnly = false)
	public void update(PdHospital pdHospital) {
		pdHospitalDao.update(pdHospital);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdHospital pdHospital) {
		super.delete(pdHospital);
	}
	
	/**
	 * 校验医院信息
	 * @param hospital
	 * @return
	 */
	public List<PdHospital> verify(PdHospital hospital) {
		return dao.verify(hospital);
	}
	
	/**
	 * 获取医院信息
	 * @param id
	 * @return
	 */
	public PdHospital getOne(PdHospital hos) {
		return dao.getOne(hos);
	}
	
	/**
	 * 区域采购金额情况分析
	 * @param hospital
	 * @return
	 */
	public List<PdHospital> findCgAmountSituation(PdHospital hospital) {
		return dao.findCgAmountSituation(hospital);
	}
	
	/**
	 * 批量保存
	 * @param dataList
	 */
	@Transactional(readOnly = false)
	public boolean saveBatchHospital(List<PdHospital> dataList) {
		PdHospitalDao dao = sqlSession.getMapper(PdHospitalDao.class);
		try{
			for (PdHospital pdHospital : dataList) {
				pdHospital.preInsert();
				dao.insert(pdHospital);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}