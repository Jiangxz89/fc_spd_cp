/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.entity.PdCategory;
import com.thinkgem.jeesite.hys.entity.PdProduct;
import com.thinkgem.jeesite.hys.dao.PdCategoryDao;
import com.thinkgem.jeesite.hys.dao.PdProductDao;

/**
 * 药材汇总统计
 * @author sutianqi
 * @version 2018-07-23
 */
@Service
@Transactional(readOnly = true)
public class PdDrugGatherService extends CrudService<PdCategoryDao, PdCategory> {

	
}