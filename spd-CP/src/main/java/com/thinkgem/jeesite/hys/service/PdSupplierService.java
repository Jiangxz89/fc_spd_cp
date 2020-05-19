/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.hys.dao.*;
import com.thinkgem.jeesite.hys.entity.*;
import org.activiti.engine.IdentityService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.utils.CommonUtils;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 供应商表Service
 * @author sutianqi
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PdSupplierService extends CrudService<PdSupplierDao, PdSupplier> {
	
	@Autowired
	private PdSupplierMidHospitalDao pdSupplierMidHospitalDao;
	@Autowired
	private PdSupplierMidProductDao pdSupplierMidProductDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PdHospitalDao pdHospitalDao;
	@Autowired
	private PdSupplierDao pdSupplierDao;
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private IdentityService identityService;
	@Autowired
    private PdProductDao pdProductDao;

    public PdSupplier get(String id) {
		return super.get(id);
	}
	
	
	@Transactional(readOnly = false)
	public boolean saveBatchPdSupplier(List<PdSupplier> pdss){
		PdSupplierDao dao = sqlSession.getMapper(PdSupplierDao.class);
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		
		try {
			for (PdSupplier p : pdss) {
				//创建用户
				String id = CommonUtils.getRandomString(32);
				User user = new User();
				Office office = new Office("1");
				user.setId(id);
				user.setName(p.getName());
				user.setCompany(office);
				user.setOffice(office);
				user.setLoginName(p.getAccount());
				user.setPassword(SystemService.entryptPassword(Global.getConfig("supplier.init.password")));
				user.setEmail(p.getMail());
				user.setMobile(p.getTel());
				user.setCreateBy(UserUtils.getUser());
				user.setCreateDate(new Date());
				user.setUpdateBy(UserUtils.getUser());
				user.setUpdateDate(new Date());
				userDao.insert(user);
				//关联角色
				Role role = new Role();
				String supplierRole = Global.getConfig("supplier.role");
				role.setId(supplierRole);
				user.setRole(role);
				userDao.insertUserRole2(user);
				p.preInsert();
				dao.insert(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public PdSupplier get(PdSupplier pdSupplier) {
		PdSupplierMidHospital midHospital = new PdSupplierMidHospital();
		midHospital.setSupplierId(pdSupplier.getId());
		List<PdSupplierMidHospital> findList = pdSupplierMidHospitalDao.findList(midHospital);
		
		List<PdHospital> hosList = new ArrayList<PdHospital>();
		
		for(PdSupplierMidHospital mid : findList){
			PdHospital hos = new PdHospital();
			hos.setId(mid.getHospitalId());
			hos.setMidId(mid.getId());
			PdHospital pdHospital = pdHospitalDao.getOne(hos);
			hosList.add(pdHospital);
		}

        PdSupplierMidProduct pdSupplierMidProduct = new PdSupplierMidProduct();
        pdSupplierMidProduct.setSupplierId(pdSupplier.getId());
        List<PdSupplierMidProduct> findProductList = pdSupplierMidProductDao.findList(pdSupplierMidProduct);
        
        PdProduct pdProduct = new PdProduct();
        List<String> productIdList = new ArrayList<String>();
        for (PdSupplierMidProduct temp : findProductList) {
            if(StringUtils.isNotEmpty(temp.getProductId())){
                productIdList.add(temp.getProductId());
            }
        }

        pdSupplier = super.get(pdSupplier.getId());
        pdSupplier.setHospitalList(hosList);

        if(productIdList != null && productIdList.size() > 0){
            pdProduct.setProductIdList(productIdList);
            List<PdProduct> pdProductList = pdProductDao.basicFindList(pdProduct);
            pdSupplier.setPdProductList(pdProductList);
        }

		return pdSupplier;
	}
	
	public List<PdSupplier> findList(PdSupplier pdSupplier) {
		return super.findList(pdSupplier);
	}
	
	public Page<PdSupplier> findPage(Page<PdSupplier> page, PdSupplier pdSupplier) {
		return super.findPage(page, pdSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(PdSupplier pdSupplier) {
		String generateSupplierCode = CommonUtils.generateSupplierCode();//生成供应商代码
		pdSupplier.setCode(generateSupplierCode);
		pdSupplier.setId(CommonUtils.getRandomString(32));
		pdSupplier.setAccount(pdSupplier.getLoginName());//账号
		List<PdHospital> hospitalList = pdSupplier.getHospitalList();
		List<PdProduct> pdProductList = pdSupplier.getPdProductList();
		if(hospitalList != null && hospitalList.size() > 0){
			//供应商关联医院的集合
			List<PdSupplierMidHospital> pdSupplierMidHospitals = new ArrayList<PdSupplierMidHospital>();
			for(PdHospital p : hospitalList){//添加中间表数据
				PdSupplierMidHospital pdSupplierMidHospital = new PdSupplierMidHospital();
				pdSupplierMidHospital.setId(CommonUtils.getRandomString(32));
				pdSupplierMidHospital.setSupplierId(pdSupplier.getId());
				pdSupplierMidHospital.setHospitalId(p.getId());
				pdSupplierMidHospital.setMidCode(p.getMidCode());
				//pdSupplierMidHospitalDao.insert(pdSupplierMidHospital);
				pdSupplierMidHospital.preInsert();
				pdSupplierMidHospitals.add(pdSupplierMidHospital);
			}
			//批量保存供应商关联医院信息
			if(pdSupplierMidHospitals.size()>0){

				pdSupplierMidHospitalDao.batchSave(pdSupplierMidHospitals);
			}
		}

		// add by jiangxz 20191101 增加供应商与产品关联关系
		if(pdProductList != null && pdProductList.size() > 0){
			List<PdSupplierMidProduct> pdSupplierMidProductList = new ArrayList<PdSupplierMidProduct>();
			for(PdProduct p : pdProductList){//添加中间表数据
				PdSupplierMidProduct pdSupplierMidProduct = new PdSupplierMidProduct();
				pdSupplierMidProduct.setId(CommonUtils.getRandomString(32));
				pdSupplierMidProduct.setSupplierId(pdSupplier.getId());
				pdSupplierMidProduct.setProductId(p.getId());
				pdSupplierMidProduct.preInsert();
				pdSupplierMidProductList.add(pdSupplierMidProduct);
			}
			//批量保存供应商关联医院信息
			if(pdSupplierMidProductList.size()>0){
				pdSupplierMidProductDao.batchSave(pdSupplierMidProductList);
			}
		}

		
		//创建用户
		String id = CommonUtils.getRandomString(32);
		User user = new User();
		Office office = new Office("1");
		user.setId(id);
		user.setName(pdSupplier.getName());
		user.setCompany(office);
		user.setOffice(office);
		user.setLoginName(pdSupplier.getLoginName());
		user.setPassword(SystemService.entryptPassword(Global.getConfig("supplier.init.password")));
		user.setEmail(pdSupplier.getMail());
		user.setMobile(pdSupplier.getTel());
		user.setCreateBy(UserUtils.getUser());
		user.setCreateDate(new Date());
		user.setUpdateBy(UserUtils.getUser());
		user.setUpdateDate(new Date());
		userDao.insert(user);
		//关联角色
		Role role = new Role();
		String supplierRole = Global.getConfig("supplier.role");
		role.setId(supplierRole);
		user.setRole(role);
		userDao.insertUserRole2(user);

        pdSupplier.setIsNewRecord(true);
		pdSupplier.preInsert();
		dao.insert(pdSupplier);
	}
	
	//更新
	@Transactional(readOnly = false)
	public void update(PdSupplier pdSupplier) {
		pdSupplier.setAccount(pdSupplier.getLoginName());//账号
		PdSupplierMidHospital mid = new PdSupplierMidHospital();
		mid.setSupplierId(pdSupplier.getId());
		List<PdHospital> hosList = pdSupplier.getHospitalList();
		
		pdSupplierMidHospitalDao.deleteBySupplierId(mid);
		if(hosList != null && hosList.size() > 0){
			//供应商关联医院的集合
			List<PdSupplierMidHospital> pdSupplierMidHospitals = new ArrayList<PdSupplierMidHospital>();
			for(int i = 0 ; i < hosList.size() ; i ++){
				PdSupplierMidHospital m = new PdSupplierMidHospital();
				m.setSupplierId(pdSupplier.getId());
				m.setHospitalId(hosList.get(i).getId());
				m.setMidCode(hosList.get(i).getMidCode());
				String id = CommonUtils.getRandomString(32);
				m.setId(id);
				m.preInsert();
				if(m.getSupplierId() != null && !m.getSupplierId().equals("") && m.getHospitalId() != null && !m.getHospitalId().equals("")){
					pdSupplierMidHospitals.add(m);
				}
			}
			//批量保存供应商关联医院信息
			if(pdSupplierMidHospitals.size()>0){
				pdSupplierMidHospitalDao.batchSave(pdSupplierMidHospitals);
			}
		}

        PdSupplierMidProduct pro = new PdSupplierMidProduct();
        pro.setSupplierId(pdSupplier.getId());
        List<PdProduct> proList = pdSupplier.getPdProductList();

        pdSupplierMidProductDao.deleteBySupplierId(pro);
        // add by jiangxz 20191101 增加供应商与产品关联关系
        if(proList != null && proList.size() > 0){
            List<PdSupplierMidProduct> pdSupplierMidProductList = new ArrayList<PdSupplierMidProduct>();
            for(PdProduct p : proList){//添加中间表数据
                if(p.getId() != null && !p.getId().equals("")){
                    PdSupplierMidProduct pdSupplierMidProduct = new PdSupplierMidProduct();
                    pdSupplierMidProduct.setId(CommonUtils.getRandomString(32));
                    pdSupplierMidProduct.setSupplierId(pdSupplier.getId());
                    pdSupplierMidProduct.setProductId(p.getId());
                    pdSupplierMidProduct.preInsert();
                    pdSupplierMidProductList.add(pdSupplierMidProduct);
                }
            }
            //批量保存供应商关联医院信息
            if(pdSupplierMidProductList.size()>0){
                pdSupplierMidProductDao.batchSave(pdSupplierMidProductList);
            }
        }

		pdSupplier.preUpdate();
		pdSupplierDao.update(pdSupplier);
		//如果新账号和老账号一致不用更新user表信息如果不一致需要更新
		if(!pdSupplier.getAccount().equals(pdSupplier.getOldLoginName())){
			User user = new User();
			//先根据老账号查出用户信息
			user.setLoginName(pdSupplier.getOldLoginName());
			user = userDao.getByLoginName(user);
			user.setLoginName(pdSupplier.getAccount());
			userDao.update(user);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(PdSupplier pdSupplier) {
		pdSupplier = pdSupplierDao.get(pdSupplier);
		User user = new User();
		user.setLoginName(pdSupplier.getAccount());
		user = userDao.getByLoginName(user);
		userDao.delete(user);
		// 同步到Activiti
		deleteActivitiUser(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
		super.delete(pdSupplier);
	}
	
	public PdSupplier getByName(PdSupplier pdSupplier){
		return pdSupplierDao.getByName(pdSupplier);
	}
	
	/**
	 * 校验供应商是否存在
	 * @param pdSupplier
	 * @return
	 */
	public List<PdSupplier> verify(PdSupplier pdSupplier) {
		return dao.verify(pdSupplier);
	}
	
	/**
	 * 根据账号查询供应商的信息
	 * @param user
	 * @return
	 */
	public PdSupplier findByAccount(User user) {
		return dao.findByAccount(user);
	}
	
	private void deleteActivitiUser(User user) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if(user!=null) {
			String userId = user.getLoginName();//ObjectUtils.toString(user.getId());
			identityService.deleteUser(userId);
		}
	}

}