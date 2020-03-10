package com.lv.demo.sharding.service;

import com.lv.demo.sharding.mapper.OrderMapper;
import com.lv.demo.sharding.model.Order;
import com.lv.demo.sharding.model.OrderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *@Title
 *@author shunlv
 *@Date 2020/3/11 1:03 上午
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderMapper orderMapper;

    @Override
    public void addOrder() {
        Order order = OrderGenerator.generate();

        orderMapper.insertSelective(order);
    }

    @Override
    public List<Order> listOrder(Integer orderId) {
        return orderMapper.listOrder(orderId);
    }

    @Override
    public Order getOrder(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
}
