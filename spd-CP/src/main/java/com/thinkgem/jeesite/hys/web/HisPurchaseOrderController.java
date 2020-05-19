package com.thinkgem.jeesite.hys.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.hys.entity.HisPurchaseOrder;
import com.thinkgem.jeesite.hys.service.HisPurchaseOrderService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiangxz
 * @description 采购订单Controller
 * @date 2019-5-7
 */
@Controller
@RequestMapping(value = "${adminPath}/hys/hisPurchaseOrder")
public class HisPurchaseOrderController {

    @Autowired
    private HisPurchaseOrderService hisPurchaseOrderService;

    @ModelAttribute
    public HisPurchaseOrder get(@RequestParam(required=false) String id) {
        HisPurchaseOrder entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = hisPurchaseOrderService.get(id);
        }
        if (entity == null){
            entity = new HisPurchaseOrder();
        }
        return entity;
    }

    @ResponseBody
    @RequestMapping(value = "synDataFromSpd")
    public String synDataFromSpd(@RequestBody String param, HttpServletRequest request, HttpServletResponse response) {
        return hisPurchaseOrderService.synDataFromSpd(param);
    }

//    @RequiresPermissions("pd:hisPurchaseOrder:view")
    @RequestMapping(value = {"list", ""})
    public String list(HisPurchaseOrder hisPurchaseOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
        //如果是供应商角色
        User user = UserUtils.getUser();
        if(user.getSupplierId()!=null){
            hisPurchaseOrder.setProducerName(user.getSupplierName());
        }
        Page<HisPurchaseOrder> page = new Page<HisPurchaseOrder>(request, response);
        page.setOrderBy("create_date");
        Page<HisPurchaseOrder> list = hisPurchaseOrderService.findPage(page, hisPurchaseOrder);
        model.addAttribute("page", list);
        return "hys/pd/hisPurchaseOrderList";
    }
}
