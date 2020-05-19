/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.hys.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.hys.utils.BarcodeUtils;
import com.thinkgem.jeesite.hys.utils.CommonUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.CheckUtils;
import com.thinkgem.jeesite.common.utils.HysFileUploadUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.UploadUtil;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.hys.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.hys.entity.PdCategory;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrderDetail;
import com.thinkgem.jeesite.hys.entity.PdConsumablesOrderTime;
import com.thinkgem.jeesite.hys.entity.PdGroup;
import com.thinkgem.jeesite.hys.entity.PdProduct;
import com.thinkgem.jeesite.hys.entity.PdProductBarcode;
import com.thinkgem.jeesite.hys.entity.PdSupplier;
import com.thinkgem.jeesite.hys.entity.PdVender;
import com.thinkgem.jeesite.hys.service.PdCategoryService;
import com.thinkgem.jeesite.hys.service.PdConsumablesOrderDetailService;
import com.thinkgem.jeesite.hys.service.PdConsumablesOrderTimeService;
import com.thinkgem.jeesite.hys.service.PdGroupService;
import com.thinkgem.jeesite.hys.service.PdProductBarcodeService;
import com.thinkgem.jeesite.hys.service.PdProductService;
import com.thinkgem.jeesite.hys.service.PdSupplierService;
import com.thinkgem.jeesite.hys.service.PdVenderService;
import com.thinkgem.jeesite.modules.sys.utils.SnoGerUtil;

/**
 * 产品表Controller
 * @author sutianqi
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/pdProduct")
public class PdProductController extends BaseController {

	@Autowired
	private PdProductService pdProductService;
	@Autowired
	private PdProductBarcodeService pdProductBarcodeService;
	@Autowired
	private PdCategoryService pdCategoryService;
	@Autowired
	private PdGroupService pdGroupService;
	@Autowired
	private PdVenderService pdVenderService;
	@Autowired
	private PdSupplierService pdSupplierService;
	@Autowired
	private PdConsumablesOrderDetailService pdConsumablesOrderDetailService;
	@Autowired
	private PdConsumablesOrderTimeService pdConsumablesOrderTimeService;
	
	
	@ModelAttribute
	public PdProduct get(@RequestParam(required=false) String id) {
		PdProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductService.get(id);
		}
		if (entity == null){
			entity = new PdProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("hys:pdProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProduct pdProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProduct> page = pdProductService.basicFindPage(new Page<PdProduct>(request, response), pdProduct);
		List<PdCategory> categoryList = pdCategoryService.findList(new PdCategory());
		List<PdGroup> groupList = pdGroupService.findList(new PdGroup());
		List<PdVender> venderList = pdVenderService.findList(new PdVender());
		model.addAttribute("pdProduct", pdProduct);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("groupList", groupList);
		model.addAttribute("venderList", venderList);
		
		model.addAttribute("page", page);
		return "hys/pd/pdProductList";
	}
    /**
     * 产品模糊查询
     * @param pdProduct
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "findList")
    public List<PdProduct> findList(PdProduct pdProduct ,HttpServletRequest request , HttpServletResponse response, Model model){
        List<PdProduct> result = pdProductService.findSelectList(pdProduct);
        return result;
    }

	@RequiresPermissions("hys:pdProduct:view")
	@RequestMapping(value = "form")
	public String form(PdProduct pdProduct, Model model, HttpServletRequest request) {
		String flag = request.getParameter("flag");
		
		List<PdCategory> categoryList = pdCategoryService.findList(new PdCategory());
		List<PdGroup> groupList = pdGroupService.findList(new PdGroup());
		List<PdVender> venderList = pdVenderService.findList(new PdVender());
		List<PdSupplier> supplierList = pdSupplierService.findList(new PdSupplier());
		model.addAttribute("pdProduct", pdProduct);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("groupList", groupList);
		model.addAttribute("supplierList", supplierList);
		model.addAttribute("venderList", venderList);
		model.addAttribute("flag", flag);
		
		if(flag.equals("see") || flag.equals("update")){
			String id = request.getParameter("id");
			PdProduct pdProduct2 = pdProductService.get(id);
			model.addAttribute("prod", pdProduct2);
		}
		
		
		
		return "hys/pd/pdProductForm";
	}

	
	//跳转到修改页面
	@RequestMapping(value = "toUpdate")
	public String toUpdate(PdProduct pdProduct, Model model , HttpServletRequest request){
		
		String flag = request.getParameter("flag");
		//获取产品分类
		List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
		//获取产品组别
		List<PdGroup> pdGroupList = pdGroupService.findList(new PdGroup());
		//获取生产厂家
		List<PdVender> pdVenderList = pdVenderService.findList(new PdVender());
				
		pdProduct = pdProductService.get(pdProduct.getId());
		model.addAttribute(pdProduct);
		model.addAttribute("pdCategoryList", pdCategoryList);
		model.addAttribute("pdGroupList", pdGroupList);
		model.addAttribute("pdVenderList", pdVenderList);
		request.setAttribute("flag",flag);
		return "hys/pd/pdProductUpdate";
	}

	/**
	 * 添加产品
	 * */
	@RequiresPermissions("hys:pdProduct:edit")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public String save(PdProduct pdProduct,@RequestParam MultipartFile[] imgAuthSiteUp,
			@RequestParam MultipartFile[] imgRegisterSite1Up,@RequestParam MultipartFile[] imgRegisterSite2Up,
			@RequestParam MultipartFile[] imgRegisterSite3Up,@RequestParam MultipartFile[] imgLicenseSite1Up,
			@RequestParam MultipartFile[] imgLicenseSite2Up,@RequestParam MultipartFile[] imgLicenseSite3Up,
			@RequestParam MultipartFile[] imgLicenseSite4Up,@RequestParam MultipartFile[] imgProduct1Up,
			@RequestParam MultipartFile[] imgProduct2Up,@RequestParam MultipartFile[] imgProduct3Up,Model model, RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {
		if (!beanValidator(model, pdProduct)){
			return form(pdProduct, model, request);
		}
		
		
		
		//存入图片
		if(!isImgEmpty(imgAuthSiteUp)){//产品授权书图片
			pdProduct.setImgAuthSite(saveFile(imgAuthSiteUp));
		}
		if(!isImgEmpty(imgRegisterSite1Up)){//产品注册证图片
			pdProduct.setImgRegister1Site(saveFile(imgRegisterSite1Up));
		}
		if(!isImgEmpty(imgRegisterSite2Up)){//产品注册证图片
			pdProduct.setImgRegister2Site(saveFile(imgRegisterSite2Up));
		}
		if(!isImgEmpty(imgRegisterSite3Up)){//产品注册证图片
			pdProduct.setImgRegister3Site(saveFile(imgRegisterSite3Up));
		}
		if(!isImgEmpty(imgLicenseSite1Up)){//供应商营业执照图片
			pdProduct.setImgLicense1Site(saveFile(imgLicenseSite1Up));
		}
		if(!isImgEmpty(imgLicenseSite2Up)){//供应商经营许可证图片
			pdProduct.setImgLicense2Site(saveFile(imgLicenseSite2Up));
		}
		if(!isImgEmpty(imgLicenseSite3Up)){//生产企业营业执照图片
			pdProduct.setImgLicense3Site(saveFile(imgLicenseSite3Up));
		}
		if(!isImgEmpty(imgLicenseSite4Up)){//生产企业生产许可证图片
			pdProduct.setImgLicense4Site(saveFile(imgLicenseSite4Up));
		}
		if(!isImgEmpty(imgProduct1Up)){//产品图片
			pdProduct.setImgProductSite1(saveFile(imgProduct1Up));
		}
		if(!isImgEmpty(imgProduct2Up)){//产品图片
			pdProduct.setImgProductSite2(saveFile(imgProduct2Up));
		}
		if(!isImgEmpty(imgProduct3Up)){//产品图片
			pdProduct.setImgProductSite3(saveFile(imgProduct3Up));
		}
		
		pdProductService.save(pdProduct);
		addMessage(redirectAttributes, "保存产品表成功");
		
		return "redirect:"+Global.getAdminPath()+"/hys/pdProduct/?repage";
	}
	
	@RequiresPermissions("hys:pdProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProduct pdProduct, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String id = request.getParameter("id");
		String number = request.getParameter("number");
		pdProduct.setId(id);
		
		PdConsumablesOrderDetail pdConsumablesOrderDetail = new PdConsumablesOrderDetail();
		pdConsumablesOrderDetail.setNumber(number);
		List<PdConsumablesOrderDetail> list1 = pdConsumablesOrderDetailService.findList(pdConsumablesOrderDetail);
		
		if(list1.size() > 0){
			addMessage(redirectAttributes, "删除耗材失败,耗材已被使用不能删除!");
			return "redirect:"+Global.getAdminPath()+"/hys/pdProduct/?repage";
		}
		
		
		PdConsumablesOrderTime pdConsumablesOrderTime = new PdConsumablesOrderTime();
		pdConsumablesOrderTime.setNumber(number);
		List<PdConsumablesOrderTime> list2 = pdConsumablesOrderTimeService.findList(pdConsumablesOrderTime);
		
		if(list2.size() > 0){
			addMessage(redirectAttributes, "删除耗材失败,耗材已被使用不能删除!");
			return "redirect:"+Global.getAdminPath()+"/hys/pdProduct/?repage";
		}
		
		pdProductService.delete(pdProduct);
		addMessage(redirectAttributes, "删除产品表成功");
		return "redirect:"+Global.getAdminPath()+"/hys/pdProduct/?repage";
	}
	
	
	@RequestMapping(value = "update")
	public String update(PdProduct pdProduct,@RequestParam MultipartFile[] imgAuthSiteUp,
						@RequestParam MultipartFile[] imgRegisterSite1Up,@RequestParam MultipartFile[] imgRegisterSite2Up,
						@RequestParam MultipartFile[] imgRegisterSite3Up,@RequestParam MultipartFile[] imgLicenseSite1Up,
						@RequestParam MultipartFile[] imgLicenseSite2Up,@RequestParam MultipartFile[] imgLicenseSite3Up,
						@RequestParam MultipartFile[] imgLicenseSite4Up,@RequestParam MultipartFile[] imgProduct1Up,
						@RequestParam MultipartFile[] imgProduct2Up,@RequestParam MultipartFile[] imgProduct3Up,Model model,MultipartHttpServletRequest request){
		
		
		
		//存入图片
				if(!isImgEmpty(imgAuthSiteUp)){//产品授权书图片
					pdProduct.setImgAuthSite(saveFile(imgAuthSiteUp));
				}
				if(!isImgEmpty(imgRegisterSite1Up)){//产品注册证图片
					pdProduct.setImgRegister1Site(saveFile(imgRegisterSite1Up));
				}
				if(!isImgEmpty(imgRegisterSite2Up)){//产品注册证图片
					pdProduct.setImgRegister2Site(saveFile(imgRegisterSite2Up));
				}
				if(!isImgEmpty(imgRegisterSite3Up)){//产品注册证图片
					pdProduct.setImgRegister3Site(saveFile(imgRegisterSite3Up));
				}
				if(!isImgEmpty(imgLicenseSite1Up)){//供应商营业执照图片
					pdProduct.setImgLicense1Site(saveFile(imgLicenseSite1Up));
				}
				if(!isImgEmpty(imgLicenseSite2Up)){//供应商经营许可证图片
					pdProduct.setImgLicense2Site(saveFile(imgLicenseSite2Up));
				}
				if(!isImgEmpty(imgLicenseSite3Up)){//生产企业营业执照图片
					pdProduct.setImgLicense3Site(saveFile(imgLicenseSite3Up));
				}
				if(!isImgEmpty(imgLicenseSite4Up)){//生产企业生产许可证图片
					pdProduct.setImgLicense4Site(saveFile(imgLicenseSite4Up));
				}
				if(!isImgEmpty(imgProduct1Up)){//产品图片
					pdProduct.setImgProductSite1(saveFile(imgProduct1Up));
				}
				if(!isImgEmpty(imgProduct2Up)){//产品图片
					pdProduct.setImgProductSite2(saveFile(imgProduct2Up));
				}
				if(!isImgEmpty(imgProduct3Up)){//产品图片
					pdProduct.setImgProductSite3(saveFile(imgProduct3Up));
				}
				
				pdProductService.update(pdProduct);
				model.addAttribute(pdProduct);
		
		return "redirect:"+Global.getAdminPath()+"/pd/pdProduct/?list";
	}

	/**
	 * 产品选择框
	 * @param pdProduct
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "chooseProductList")
	public String chooseProductList(PdProduct pdProduct, HttpServletRequest request, HttpServletResponse response, Model model) {

		pdProduct.setChooseFlag("1");
		Page<PdProduct> page = pdProductService.findPage(new Page<PdProduct>(request, response), pdProduct);
		model.addAttribute("page", page);
		model.addAttribute("pdProduct", pdProduct);

		return "hys/pd/box/pdAddProBox";
	}
	
	/**
	 * 判断图片非空
	 * @param imgs
	 * @return
	 */
	private boolean isImgEmpty(MultipartFile[] imgs){
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			if(img.isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	// 保存多个图片
	private String saveFile(MultipartFile[] imgs) {
		StringBuilder urls = new StringBuilder("");
		for (int i=0; i<imgs.length; i++) {
			MultipartFile img = imgs[i];
			String extName = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
			Long longTime = new Date().getTime();// 获取随机名称// 取时间长整型值
			String serialNumber = SnoGerUtil.getSerialNumber(5);
			String fullName = longTime + serialNumber + extName;
			String fullPath = Global.getUserfilesBaseDir()+UploadUtil.IMAGE_DIR +"spd/"+ fullName;
			String  url = UploadUtil.IMAGE_DIR+"spd/"+ fullName;//文件上传的url
			try {
				createDir(fullPath);
				img.transferTo(new File(fullPath));
				if(0==i){
					urls.append(url);
				}else{
					urls.append("&&&"+url);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return  null;
			} catch (IOException e) {
				e.printStackTrace();
				return  null;
			}
		}
		return urls.toString();
	}
	
	/**
	 * @功能描述:创建文件夹
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}
	
	
	/**
	 * AJAX响应方法：
	 * 生成编码
	 * 生成条形码 Code93 系统生成
	 * */
	@RequestMapping(value = "generateNumber")
	public void generateNumber(HttpServletRequest request , HttpServletResponse response){
		
		
		String generateNumber = CommonUtils.generateNumber("93");//生成编码
		String fullPath = Global.getUserfilesBaseDir()+UploadUtil.IMAGE_DIR+"spd/barcode/"+generateNumber+".jpg";//图片生成地址
		String path = UploadUtil.IMAGE_DIR+"spd/barcode/"+generateNumber+".jpg";
		createDir(fullPath);
		File generateFile = BarcodeUtils.generateFile(generateNumber, fullPath);//条形码生成
		
	/*	FileWriter fw;
		try {
			fw = new FileWriter(generateFile.getAbsoluteFile());
			 BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(path);
			   bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		  
		
		//封装对象
		PdProductBarcode barcodeBean = new PdProductBarcode();
		barcodeBean.setNumber(generateNumber);
		barcodeBean.setSite(path.replace("\\","/"));
		
		pdProductBarcodeService.save(barcodeBean);//写入数据库
		
		String json = JSONObject.toJSONString(barcodeBean);//转换json
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * AJAX响应方法：
	 * 生成模板
	 * */
	@RequestMapping(value = "generateTemplate")
	public void generateTemplate(HttpServletRequest request , HttpServletResponse response){
		String [] head = {"产品编号","产品名称","规格","型号","单位","产品组别","产品类型","产品分类","生产厂家","产品进价","产品出价","注册证","备注"};
		ExportExcel exportExcel = new ExportExcel("耗材",head);
		try {
			response.setCharacterEncoding("utf-8");
			exportExcel.write(response, "耗材列表.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * AJAX响应方法：
	 * 产品分类列表
	 * */
	@RequestMapping(value="categoryList")
	public void categoryList(HttpServletRequest request , HttpServletResponse response){
		//获取产品分类
		List<PdCategory> pdCategoryList = pdCategoryService.findList(new PdCategory());
		
		String json = JSONObject.toJSONString(pdCategoryList);
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * AJAX响应方法：
	 * 导入数据
	 * */
	@RequestMapping(value = "importData",method=RequestMethod.POST)
	public String importData(@RequestParam MultipartFile[] file, RedirectAttributes redirectAttributes,MultipartHttpServletRequest request , HttpServletResponse response){

	//	MultipartFile multipartFile = request.getFile("file");
		
		Map<String, MultipartFile> fileMap = request.getFileMap();
		MultipartFile multipartFile = fileMap.get("file");
		
		String originalFilename = multipartFile.getOriginalFilename();
		String fullUrl = "";
		try {
			fullUrl = HysFileUploadUtils.uploadAndReturnFullUrl(multipartFile, "excel");
			
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(StringUtils.isBlank(originalFilename) || !originalFilename.endsWith(".xls") && !originalFilename.endsWith(".xlsx")){
			
		}else{
			try {
				ImportExcel ei = new ImportExcel(fullUrl, 1, 0);
				List<PdProduct> dataList = ei.getDataList(PdProduct.class);
				if(dataList!=null && dataList.size()>0){
					//查询所有的产品
					List<PdProduct> pdProducts = pdProductService.findList(new PdProduct());
					boolean bl = true;
					for(int i=0;i<dataList.size();i++){
						PdProduct pdProduct = dataList.get(i);
						//校验产品名称
						if(StringUtils.isEmpty(pdProduct.getNumber())){
							pdProduct.setNumber(CommonUtils.generateNumber("93"));//生成编码
						}else{
							if(checkNumber(pdProducts,pdProduct)){
								pdProducts.add(pdProduct);
							}else{
								addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行产品编号不能重复");
								bl = false;
								break;
							}
						}
						//校验产品名称
						if(StringUtils.isEmpty(pdProduct.getName())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行产品名称不能为空");
							bl = false;
							break;
						}
						//校验规格
						if(StringUtils.isEmpty(pdProduct.getSpec())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行规格不能为空");
							bl = false;
							break;
						}
						//校验型号
						if(StringUtils.isEmpty(pdProduct.getVersion())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行型号不能为空");
							bl = false;
							break;
						}
						//校验单位
						if(StringUtils.isEmpty(pdProduct.getUnit())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行单位不能为空");
							bl = false;
							break;
						}
						//校验产品组别
						String gropBl = checkGroup(pdProduct);
						if("".equals(gropBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行产品组别填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setGroupId(gropBl);
						}
						//校验产品类型
						String typeBl = checkType(pdProduct);
						if("".equals(typeBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行产品类型填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setType(typeBl);
						}
						//校验产品分类
						String categoryBl = checkCategory(pdProduct);
						if("".equals(categoryBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行产品分类填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setCategoryId(categoryBl);
						}
						//校验生产厂家
						String venderBl = checkVender(pdProduct);
						if("".equals(venderBl)){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"生产厂家填写不正确");
							bl = false;
							break;
						}else{
							pdProduct.setVenderId(venderBl);
						}
						//校验产品进价
						if(!CheckUtils.isMoney(pdProduct.getPurPrice())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行产品进价格式不正确");
							bl = false;
							break;
						}
						//校验产品出价
						if(!CheckUtils.isMoney(pdProduct.getSellingPrice())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行产品出价格式不正确");
							bl = false;
							break;
						}
						//校验注册证
						if(StringUtils.isEmpty(pdProduct.getRegistration())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行注册证不能为空");
							bl = false;
							break;
						}
						//校验备注
						if(!CheckUtils.checkRemark(pdProduct.getDescription())){
							addMessage(redirectAttributes, "导入失败,第"+(i+1)+"行备注超过字数限制");
							bl = false;
							break;
						}
					}
					if(bl){
						//批量添加耗材
						if(pdProductService.saveBatchPdSupplier(dataList)){
							addMessage(redirectAttributes, "批量导入成功");	
						};
							
						
					}
				}else{
					addMessage(redirectAttributes, "批量导入失败,没有数据");
				}
			} catch (Exception e) {
				addMessage(redirectAttributes, "批量导入失败");
				e.printStackTrace();
			}
			
		}
		return "redirect:"+Global.getAdminPath()+"/hys/pdProduct/?repage";
	}
	
	/**
	 * 校验名称是否重复
	 * @param pdProducts
	 * @param pdProduct
	 * @return
	 */
	private boolean checkNumber(List<PdProduct> pdProducts, PdProduct pdProduct) {
		for (PdProduct p : pdProducts) {
			if(p.getNumber().equals(pdProduct.getNumber())){
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验生产厂家是否输入正确
	 * @param pdProduct
	 * @return
	 */
	private String checkVender(PdProduct pdProduct) {
		PdVender pdVender = new PdVender();
		pdVender.setName(pdProduct.getVenderId());
		try{
			pdVender = pdVenderService.findByName(pdVender);
		}catch(Exception e){
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
		if(pdVender!=null){
			return pdVender.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}

	/**
	 * 校验产品分类是否输入正确
	 * @param pdProduct
	 * @return
	 */
	private String checkCategory(PdProduct pdProduct) {
		PdCategory pdCategory = new PdCategory();
		pdCategory.setName(pdProduct.getCategoryId());
		try{
			pdCategory = pdCategoryService.findByName(pdCategory);
		}catch(Exception e){
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
		if(pdCategory!=null){
			return pdCategory.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}

	/**
	 * 校验产品类型
	 * @param pdProduct
	 * @return
	 */
	private String checkType(PdProduct pdProduct) {
		if(MinaGlobalConstants.CATEGORY_TYPE_NAME_HIGH.equals(pdProduct.getType())){//高值耗材
			return MinaGlobalConstants.CATEGORY_TYPE_HIGH;
		}else if(MinaGlobalConstants.CATEGORY_TYPE_NAME_LOW.equals(pdProduct.getType())){
			return MinaGlobalConstants.CATEGORY_TYPE_LOW;
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 校验产品组别
	 * @param pdProduct
	 * @return
	 */
	private String checkGroup(PdProduct pdProduct) {
		PdGroup pdGroup = new PdGroup();
		pdGroup.setName(pdProduct.getGroupId());
		try{
			pdGroup = pdGroupService.findByName(pdGroup);
		}catch(Exception e){
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
		if(pdGroup!=null){
			return pdGroup.getId();
		}else{
			return StringUtils.EMPTY;
		}
	}

	@ResponseBody
	@RequestMapping(value = "synDataFromSpd")
	public String synDataFromSpd(@RequestBody String param, HttpServletRequest request, HttpServletResponse response) {
		return pdProductService.synDataFromSpd(param);
	}

	@ResponseBody
	@RequestMapping(value = "synFileFromSpd")
	public String synFileFromSpd(@RequestParam(value = "imageFile", required = false) MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) {
		return pdProductService.synFileFromSpd(imageFile);
	}
}