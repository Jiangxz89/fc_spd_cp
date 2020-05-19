/**

 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.hys.entity.PdProduct;

/**
 * 产品表DAO接口
 * @author sutianqi
 * @version 2018-07-04
 */
@MyBatisDao
public interface PdProductDao extends CrudDao<PdProduct> {
	int update(PdProduct pdProduct);
	
	List<PdProduct> checkCorrelation(PdProduct pdProduct);
	
	void importSave(List<PdProduct> pdProduct);	//导入插入

	PdProduct getByNumber(PdProduct pdProduct);

	List<PdProduct> basicFindList(PdProduct pdProduct);

	List<PdProduct> findSelectList(PdProduct pdProduct);
}