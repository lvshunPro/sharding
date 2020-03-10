package com.lv.demo.sharding.model;

import java.util.Random;

/***
 *@Title
 *@author shunlv
 *@Date 2020/3/11 1:04 上午
 */
public class OrderGenerator {
    public static Order generate() {
        Order order = new Order();
        int orderId = random(1, 100);
        order.setOrderId(orderId);
        return order;
    }

    private static int random(int min, int max) {
        Random random = new Random();

        return random.nextInt(max) % (max - min + 1) + min;
    }
}
