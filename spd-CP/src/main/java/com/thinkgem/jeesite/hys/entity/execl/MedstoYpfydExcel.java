package com.thinkgem.jeesite.hys.entity.execl;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * @Auther: zxh
 * @Date: 2019/5/10 0010
 * @Description: com.thinkgem.hys.pd.entity.excel
 * @version: 1.0
 */
public class MedstoYpfydExcel {



    @ExcelField(title="药品编码",sort=10,align=2)
    private String drugId;		// 药品编码
    @ExcelField(title="药品名称",sort=20,align=2)
    private String drugName;		// 药品名称
    @ExcelField(title="药品规格",sort=30,align=2)
    private String drugSpec;		// 药品规格
    @ExcelField(title="药品默认扣率",sort=40,align=2)
    private String ypmrkl;
    @ExcelField(title="药库名称",sort=50,align=2)
    private String storeName;		// 药库名称
    @ExcelField(title="医保统一编码",sort=60,align=2)
    private String ybtybm;		// 医保统一编码
    @ExcelField(title="是否基药",sort=70,align=2)
    private String isjbyw;		// 是否基药
    @ExcelField(title="是否抗生素药品",sort=80,align=2)
    private String kss;		// 是否抗生素药品
    @ExcelField(title="中药使用类别代码",sort=90,align=2)
    private String cmedCode;		// 中药使用类别代码
    @ExcelField(title="药物类型",sort=100,align=2)
    private String drugDetailType;		// 药物类型
    @ExcelField(title="药物剂型代码",sort=110,align=2)
    private String doseCode;		// 药物剂型代码
    @ExcelField(title="大包装单位",sort=120,align=2)
    private String packageUnit;		// 大包装单位
    @ExcelField(title="大包装数量",sort=130,align=2)
    private String packageQty;		// 大包装数量
    @ExcelField(title="小包装单位",sort=140,align=2)
    private String salesUnit;		// 小包装单位
    @ExcelField(title="小包装数量",sort=150,align=2)
    private String salesQty;		// 小包装数量
    @ExcelField(title="包装单位数量关系",sort=160,align=2)
    private String salesRelation;		// 包装单位数量关系
    @ExcelField(title="供货商名称",sort=170,align=2)
    private String producerName;		// 供货商名称
    @ExcelField(title="进价单价",sort=180,align=2)
    private String costPrice;		// 进价单价
    @ExcelField(title="零售单价",sort=190,align=2)
    private String salePrice;		// 零售单价
    @ExcelField(title="进价金额合计",sort=200,align=2)
    private String costAmt;		// 进价金额合计
    @ExcelField(title="零售金额合计",sort=210,align=2)
    private String saleAmt;		// 零售金额合计
    @ExcelField(title="生产批号",sort=220,align=2)
    private String batchNo;		// 生产批号
    @ExcelField(title="药品生产企业",sort=230,align=2)
    private String sdrugmanufacturers;		// 药品生产企业
    @ExcelField(title="药品产地",sort=240,align=2)
    private String sdrughabitat;		// 药品产地
    @ExcelField(title="批准文号",sort=250,align=2)
    private String sratificationno;		// 批准文号
    @ExcelField(title="生产日期",sort=290,align=2)
    private String ddateproduce;		// 生产日期
    @ExcelField(title="失效日期",sort=300,align=2)
    private String expiry;		// 失效日期
    @ExcelField(title="患者姓名",sort=310,align=2)
    private String patientname;		// 患者姓名
    @ExcelField(title="患者年龄",sort=320,align=2)
    private String patientage;		// 患者年龄
    @ExcelField(title="患者性别",sort=330,align=2)
    private String patientsex;		// 患者性别
    @ExcelField(title="发药数量",sort=340,align=2)
    private String number;		// 发药数量
    @ExcelField(title="病人身份证号",sort=350,align=2)
    private String patientid;		// 病人身份证号
    @ExcelField(title="门诊号",sort=360,align=2)
    private String outpatientid;		// 门诊号
    @ExcelField(title="主诊疗医师",sort=370,align=2)
    private String outpatientdoctor;		// 主诊疗医师
    @ExcelField(title="发药医师",sort=380,align=2)
    private String pharmacist;		// 发药医师
    @ExcelField(title="发药时间",sort=390,align=2)
    private String fydatetime;		// 发药时间
    @ExcelField(title="上传日期",sort=400,align=2)
    private String scrq;		// 上传日期


    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugSpec() {
        return drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getYbtybm() {
        return ybtybm;
    }

    public void setYbtybm(String ybtybm) {
        this.ybtybm = ybtybm;
    }

    public String getIsjbyw() {
        return isjbyw;
    }

    public void setIsjbyw(String isjbyw) {
        this.isjbyw = isjbyw;
    }

    public String getKss() {
        return kss;
    }

    public void setKss(String kss) {
        this.kss = kss;
    }

    public String getCmedCode() {
        return cmedCode;
    }

    public void setCmedCode(String cmedCode) {
        this.cmedCode = cmedCode;
    }

    public String getDrugDetailType() {
        return drugDetailType;
    }

    public void setDrugDetailType(String drugDetailType) {
        this.drugDetailType = drugDetailType;
    }

    public String getDoseCode() {
        return doseCode;
    }

    public void setDoseCode(String doseCode) {
        this.doseCode = doseCode;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public String getPackageQty() {
        return packageQty;
    }

    public void setPackageQty(String packageQty) {
        this.packageQty = packageQty;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getSalesQty() {
        return salesQty;
    }

    public void setSalesQty(String salesQty) {
        this.salesQty = salesQty;
    }

    public String getSalesRelation() {
        return salesRelation;
    }

    public void setSalesRelation(String salesRelation) {
        this.salesRelation = salesRelation;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getCostAmt() {
        return costAmt;
    }

    public void setCostAmt(String costAmt) {
        this.costAmt = costAmt;
    }

    public String getSaleAmt() {
        return saleAmt;
    }

    public void setSaleAmt(String saleAmt) {
        this.saleAmt = saleAmt;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSdrugmanufacturers() {
        return sdrugmanufacturers;
    }

    public void setSdrugmanufacturers(String sdrugmanufacturers) {
        this.sdrugmanufacturers = sdrugmanufacturers;
    }

    public String getSdrughabitat() {
        return sdrughabitat;
    }

    public void setSdrughabitat(String sdrughabitat) {
        this.sdrughabitat = sdrughabitat;
    }

    public String getSratificationno() {
        return sratificationno;
    }

    public void setSratificationno(String sratificationno) {
        this.sratificationno = sratificationno;
    }

    public String getDdateproduce() {
        return ddateproduce;
    }

    public void setDdateproduce(String ddateproduce) {
        this.ddateproduce = ddateproduce;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getPatientage() {
        return patientage;
    }

    public void setPatientage(String patientage) {
        this.patientage = patientage;
    }

    public String getPatientsex() {
        return patientsex;
    }

    public void setPatientsex(String patientsex) {
        this.patientsex = patientsex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getOutpatientid() {
        return outpatientid;
    }

    public void setOutpatientid(String outpatientid) {
        this.outpatientid = outpatientid;
    }

    public String getOutpatientdoctor() {
        return outpatientdoctor;
    }

    public void setOutpatientdoctor(String outpatientdoctor) {
        this.outpatientdoctor = outpatientdoctor;
    }

    public String getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(String pharmacist) {
        this.pharmacist = pharmacist;
    }

    public String getFydatetime() {
        return fydatetime;
    }

    public void setFydatetime(String fydatetime) {
        this.fydatetime = fydatetime;
    }

    public String getScrq() {
        return scrq;
    }

    public void setScrq(String scrq) {
        this.scrq = scrq;
    }

    public String getYpmrkl() {
        return ypmrkl;
    }

    public void setYpmrkl(String ypmrkl) {
        this.ypmrkl = ypmrkl;
    }
}
