package com.thinkgem.jeesite.hys.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.hys.dao.HisPurchaseOrderItemDao;
import com.thinkgem.jeesite.hys.entity.HisPurchaseOrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiangxz
 * @description
 * @date 2019-5-6
 */
@Service
@Transactional(readOnly = true)
public class HisPurchaseOrderItemService extends CrudService<HisPurchaseOrderItemDao, HisPurchaseOrderItem> {
}
