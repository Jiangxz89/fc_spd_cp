package com.thinkgem.jeesite.hys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.hys.entity.PdCategory;
import com.thinkgem.jeesite.hys.entity.PdGroup;
import com.thinkgem.jeesite.hys.entity.PdHospital;
import com.thinkgem.jeesite.hys.entity.PdSupplier;
import com.thinkgem.jeesite.hys.entity.PdVender;
import com.thinkgem.jeesite.hys.service.PdCategoryService;
import com.thinkgem.jeesite.hys.service.PdGroupService;
import com.thinkgem.jeesite.hys.service.PdHospitalService;
import com.thinkgem.jeesite.hys.service.PdSupplierService;
import com.thinkgem.jeesite.hys.service.PdVenderService;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class SpdUtils {

	private static PdSupplierService pdSupplierService = SpringContextHolder.getBean(PdSupplierService.class); 
	private static PdHospitalService pdHospitalService = SpringContextHolder.getBean(PdHospitalService.class); 
	private static PdVenderService pdVenderService= SpringContextHolder.getBean(PdVenderService.class); 
	private static PdGroupService pdGroupService=SpringContextHolder.getBean(PdGroupService.class);
	private static PdCategoryService pdCategoryService=SpringContextHolder.getBean(PdCategoryService.class);
	private static AreaService areaService=SpringContextHolder.getBean(AreaService.class);
	public static final String CACHE_SUPPLIER_MAP = "supplierMap";
	public static final String CACHE_HOSPITAL_MAP = "hospitalMap";
	public static final String CACHE_VENDER_MAP = "venderMap";

	
	/**
	 * 返回供应商信息列表
	 * @return
	 */
	public static List<PdSupplier> findSupplierList(){
		List<PdSupplier> supList = null;
		Map<String, PdSupplier> supplierMap = (Map<String, PdSupplier>)CacheUtils.get(CACHE_SUPPLIER_MAP);
		if(supplierMap == null){
			supplierMap = Maps.newHashMap();
			supList = pdSupplierService.findList(new PdSupplier());
			for (PdSupplier pdSupplier:supList){
				supplierMap.put(pdSupplier.getId(), pdSupplier);
			}
			CacheUtils.put(CACHE_SUPPLIER_MAP, supplierMap);
		}else{
			supList = new ArrayList<PdSupplier>(supplierMap.values());
		}

		return supList;
	}
	/**
	 * 返回生产厂家信息列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<PdVender> getVenderList(){
		List<PdVender> supList = null;
		Map<String, PdVender> supplierMap = (Map<String, PdVender>)CacheUtils.get(CACHE_VENDER_MAP);
		if(supplierMap == null){
			supplierMap = Maps.newHashMap();
			supList = pdVenderService.findList(new PdVender());
			for (PdVender pdSupplier:supList){
				supplierMap.put(pdSupplier.getId(), pdSupplier);
			}
			CacheUtils.put(CACHE_VENDER_MAP, supplierMap);
		}else{
			supList = new ArrayList<PdVender>(supplierMap.values());
		}
		
		return supList;
	}
	
	/**
	 * 返回医院信息列表
	 * @return
	 */
	public static List<PdHospital> findHospitalList(){
		List<PdHospital> htList = null;
		Map<String, PdHospital> hospitalMap = (Map<String, PdHospital>)CacheUtils.get(CACHE_HOSPITAL_MAP);
		if(hospitalMap == null){
			hospitalMap = Maps.newHashMap();
			htList = pdHospitalService.findList(new PdHospital());
			for (PdHospital pdHospital:htList){
				hospitalMap.put(pdHospital.getId(), pdHospital);
			}
			CacheUtils.put(CACHE_HOSPITAL_MAP, hospitalMap);
		}else{
			htList = new ArrayList<PdHospital>(hospitalMap.values());
		}

		return htList;
	}

	

	
	/**
	 * 返回生产厂家信息列表
	 * @return
	 */
	public static List<PdVender> findPdVenderList(){
		PdVender pdVender = new PdVender();
		List<PdVender> list = pdVenderService.findList(pdVender);
		return list;
	}
	
	/**
	 * 返回产品组别列表
	 * @return
	 */
	public static List<PdGroup> findPdGroupList(){
		PdGroup pdGroup = new PdGroup();
		List<PdGroup> list = pdGroupService.findList(pdGroup);
		return list;
	}
	/**
	 * 返回产品分类列表
	 * @return
	 */
	public static List<PdCategory> findPdCategoryList(){
		PdCategory pdCategory = new PdCategory();
		List<PdCategory> list = pdCategoryService.findList(pdCategory);
		return list;
	}
	
	
	/**
	 * 根据用户id返回用户name
	 * */
	public static String getUserNameById(String id){
		User user = UserUtils.get(id);
		return user.getName();
	}
	
	/**
	 * 根据组别id返回组别name
	 * */
	public static String getGroupName(String id){
		PdGroup pdGroup = pdGroupService.get(id);
		if(pdGroup != null){
			return pdGroup.getName();
		}else{
			return "";
		}
	}
	
	/**
	 * 根据类别id返回类别name
	 * */
	public static String getCategoryName(String id){
		PdCategory pdCategory = pdCategoryService.get(id);
		if(pdCategory != null){
			return pdCategory.getName();
		}else{
			return "";
		}
	}
	
	/**
	 * 根据厂家id返回厂家name
	 * */
	public static String getVenderName(String id){
		PdVender pdVender = pdVenderService.get(id);
		if(pdVender != null){
			return pdVender.getName();
		}else{
			return "";
		}
	}
	
	/**
	 * 根据供应商id返回供应商name
	 * */
	public static String getSupplierName(String id){
		PdSupplier pdSupplier = pdSupplierService.get(id);
		if(pdSupplier != null){
			return pdSupplier.getName();
		}else{
			return "";
		}
	}
	
	/**
	 * 根据供应商id返回供应商name
	 * */
	public static String getSupplierNames(String id){
		String[] ids = id.split(",");
		String names = "";
		for(int i = 0 ; i < ids.length ; i++){
			if(!ids[i].equals("") && ids[i] != null){
				PdSupplier pdSupplier = pdSupplierService.get(ids[i]);
				if(pdSupplier != null){
					names += pdSupplier.getName()+",";
				}
			}
		}
		if(names.length() >=1){
			return names.substring(0, names.length()-1);
		}else{
			return null;
		}
		
	}
	
	/**
	 * 根据供应商names返回供应商ids
	 * */
	public static String getSupplierIds(String names){
		names = names.replace("，", ",");
		String[] name = names.split(",");
		String ids = "";
		for(int i = 0 ; i < name.length ; i++){
			if(!name[i].equals("") && name[i] != null){
				PdSupplier pdSupplier2 = new PdSupplier();
				pdSupplier2.setName(name[i]);
				PdSupplier pdSupplier = pdSupplierService.getByName(pdSupplier2);
				if(pdSupplier != null){
					ids += pdSupplier.getId()+",";
				}
			}
		}
		return ids;
	}
	
	/**
	 * 根据id返回区域name
	 * */
	public static String getAreaName(String id){
		Area area = areaService.get(id);
		if(area != null){
			return area.getName();
		}else{
			return "";
		}
	}
	
	/**
	 * 更新缓存供应商信息
	 */
	public static void updateSupploerInfo(){
		List<PdSupplier> suppliers = pdSupplierService.findList(new PdSupplier());
		Map<String, PdSupplier> srMap = new HashMap<String, PdSupplier>();
		for (PdSupplier pdSupplier:suppliers){
			srMap.put(pdSupplier.getId(), pdSupplier);
		}
		CacheUtils.put(CACHE_SUPPLIER_MAP, srMap);
	}
}	

