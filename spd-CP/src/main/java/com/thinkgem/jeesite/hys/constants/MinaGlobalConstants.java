package com.thinkgem.jeesite.hys.constants;

public class MinaGlobalConstants {
	public final static String CONSUMABLES_ORDER_STATE= "order_state";//耗材订单状态
	public final static String CONSUMABLES_ORDER_STATE_0 = "0";	//待接收
	public final static String CONSUMABLES_ORDER_STATE_1 = "1";	//已接收
	public final static String CONSUMABLES_ORDER_STATE_2 = "2";	//已拒绝
	public final static String CONSUMABLES_ORDER_STATE_3 = "3"; //已收货
	
	//产品分类类型
	public final static String CATEGORY_TYPE = "categroy_type";
	public final static String CATEGORY_TYPE_HIGH = "1";//高值耗材
	public final static String CATEGORY_TYPE_LOW = "0";//低值耗材
	public final static String CATEGORY_TYPE_NAME_HIGH = "高值耗材";//高值耗材
	public final static String CATEGORY_TYPE_NAME_LOW = "低值耗材";//低值耗材
	
	//area类型
	public final static String AREA_TYPE = "AREA_type";
	public final static String AREA_TYPE_1 = "1";//国家
	public final static String AREA_TYPE_2 = "2";//省
	public final static String AREA_TYPE_3 = "3";//市
	public final static String AREA_TYPE_4 = "4";//区县
	
	//药品订单状态
	public final static String DRUG_ORDER_STATUS = "drug_order_status";
	public final static String DRUG_ORDER_STATUS_WAITING_ACCEPT = "0";//待接收
	public final static String DRUG_ORDER_STATUS_ALREADY_ACCEPT = "1";//已接收
	public final static String DRUG_ORDER_STATUS_ALREADY_REFUSE = "2";//已拒绝
	public final static String DRUG_ORDER_STATUS_ALREADY_INROOM = "3";//已入库
	
	//国家城市类型
	public final static String AREA_1 = "1";//国家
	public final static String AREA_2 = "2";//省份
	public final static String AREA_3 = "3";//市
	public final static String AREA_4 = "4";//县区域
	
	public final static String ADMIN_ROLE = "1";
}	
