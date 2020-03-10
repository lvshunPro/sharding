package com.lv.demo.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 *@Title
 *@author shunlv
 *@Date 2020/3/11 1:07 上午
 */
@SpringBootApplication
@MapperScan("com.lv.demo.sharding.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
