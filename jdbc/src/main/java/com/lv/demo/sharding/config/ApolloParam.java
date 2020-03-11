package com.lv.demo.sharding.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by wanchao on 2018/10/16.
 */
@Data
public class ApolloParam {

    @Value("${bs_db_0}")
    private List<Integer> bsDB0;
    @Value("${bs_db_1}")
    private List<Integer> bsDB1;
    @Value("${bs_db_2}")
    private List<Integer> bsDB2;
}
