package com.thinkgem.jeesite.hys.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.dao.HisPurchaseOrderDao;
import com.thinkgem.jeesite.hys.dao.HisPurchaseOrderItemDao;
import com.thinkgem.jeesite.hys.entity.HisPurchaseOrder;
import com.thinkgem.jeesite.hys.entity.HisPurchaseOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jiangxz
 * @description
 * @date 2019-5-6
 */
@Service
@Transactional(readOnly = true)
public class HisPurchaseOrderService extends CrudService<HisPurchaseOrderDao, HisPurchaseOrder> {

    @Autowired
    private HisPurchaseOrderItemDao hisPurchaseOrderItemDao;

    @Autowired
    private HisPurchaseOrderDao hisPurchaseOrderDao;

    @Transactional(readOnly = false)
    public void saveList(List<HisPurchaseOrder> orderList){
        for (HisPurchaseOrder order : orderList) {
            super.save(order);
            List<HisPurchaseOrderItem> itemList = order.getItemList();
            if(itemList != null && itemList.size() > 0){
                for (HisPurchaseOrderItem item : itemList){
                    item.setPId(order.getId());
                    hisPurchaseOrderItemDao.insert(item);
                }
            }
        }
    }

    /**
     * 从SPD同步数据
     * @param param
     * @return
     */
    @Transactional(readOnly = false)
    public String synDataFromSpd(String param) {

        JSONObject resultJson = new JSONObject();
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        String hospitalNumber = null;

        try{
            //接收数据
            jsonObject = JSONObject.parseObject(param);
            jsonArray = jsonObject.getJSONArray("orderList");
            hospitalNumber = jsonObject.getString("hospitalNumber");

            if(jsonArray == null || jsonArray.size() <=0){
                resultJson.put("statusCode", "500");
                resultJson.put("msg", "同步数据为空");
                return resultJson.toString();
            }else{
                for(int i = 0; i < jsonArray.size(); i++ ){
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    jsonObj.remove("createBy");
                    jsonObj.remove("updateBy");
                }
            }

            if(hospitalNumber == null || "".endsWith(hospitalNumber.trim())){
                resultJson.put("statusCode", "500");
                resultJson.put("msg", "医院代码为空");
                return resultJson.toString();
            }
        }catch(Exception e){
            e.printStackTrace();
            resultJson.put("statusCode", "500");
            resultJson.put("msg", "数据格式异常");
            return resultJson.toString();
        }

        try{
            List<HisPurchaseOrder> orderList = JSONObject.parseArray(jsonArray.toJSONString(),HisPurchaseOrder.class);
            if(orderList != null && orderList.size() > 0){
                for (HisPurchaseOrder purchaseOrder:orderList) {
                    //						pdGroup.setDelFlag(DEL_FLAG_NORMAL);
                    purchaseOrder.setHospitalNumber(hospitalNumber);
                    HisPurchaseOrder order = hisPurchaseOrderDao.get(purchaseOrder);
                    List<HisPurchaseOrderItem> itemList = purchaseOrder.getItemList();
                    if(order != null){
                        hisPurchaseOrderDao.update(purchaseOrder);
                        hisPurchaseOrderItemDao.deleteByPid(purchaseOrder.getId());
                    }else{
                        hisPurchaseOrderDao.insert(purchaseOrder);
                    }
                    if(itemList != null && itemList.size() > 0){
                        for(HisPurchaseOrderItem item : itemList){
                            hisPurchaseOrderItemDao.insert(item);
                        }
                    }
                }
            }else{
                resultJson.put("statusCode", "500");
                resultJson.put("msg", "数据格式异常");
                return resultJson.toString();
            }
        }catch(Exception e){
            e.printStackTrace();
            resultJson.put("statusCode", "500");
            resultJson.put("msg", "保存失败");
            return resultJson.toString();
        }
        resultJson.put("statusCode", "200");
        resultJson.put("msg", "success");
        return resultJson.toString();
    }
}
