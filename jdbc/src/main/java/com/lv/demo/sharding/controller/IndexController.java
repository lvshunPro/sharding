package com.lv.demo.sharding.controller;

import com.lv.demo.sharding.model.Order;
import com.lv.demo.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @Title
 * @author lv
 * @Date 2020/3/12 13:06
 */
@RestController
public class IndexController {
    @Autowired
    public OrderService orderService;

    @RequestMapping("/order/get")
    public Order getOrder(Integer orderId) {
        return orderService.getOrder(orderId);
    }
}
