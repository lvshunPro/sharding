package com.lv.demo.sharding.service;

import com.lv.demo.sharding.model.Order;

import java.util.List;

/***
 *@Title
 *@author shunlv
 *@Date 2020/3/11 1:03 上午
 */
public interface OrderService {

    void addOrder();

    List<Order> listOrder(Integer orderId);

    Order getOrder(Integer orderId);
}
