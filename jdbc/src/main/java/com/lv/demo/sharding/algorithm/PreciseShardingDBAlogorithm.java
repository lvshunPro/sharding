package com.lv.demo.sharding.algorithm;

import com.lv.demo.sharding.config.ApolloParam;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

/***
 * @Title   精准算法
 * @author lv
 * @Date 2020/3/11 14:18
 */

public class PreciseShardingDBAlogorithm implements PreciseShardingAlgorithm<Integer> {

    public PreciseShardingDBAlogorithm() {
        System.out.println("---------");
    }

    private ApolloParam apolloParam;

    public void setApolloParam(ApolloParam apolloParam) {
        this.apolloParam = apolloParam;
        this.bs_db_0 = apolloParam.getBsDB0();
        this.bs_db_1 = apolloParam.getBsDB1();
        this.bs_db_2 = apolloParam.getBsDB2();
    }

    public void refreshDB() {
        this.bs_db_0 = apolloParam.getBsDB0();
        this.bs_db_1 = apolloParam.getBsDB1();
        this.bs_db_2 = apolloParam.getBsDB2();
    }

     List<Integer> bs_db_0 = new ArrayList<>();
     List<Integer> bs_db_1 = new ArrayList<>();
     List<Integer> bs_db_2 = new ArrayList<>();



    @Override
    public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Integer> shardingValue) {

        Integer value = shardingValue.getValue();
        System.out.println("=====" + apolloParam.getBsDB0());
        System.out.println("=====" + apolloParam.getBsDB1());
        System.out.println("=====" + apolloParam.getBsDB2());
        System.out.println("=====" + databaseNames);
        refreshDB();
        Integer index = 0;
        if(bs_db_0.contains(value)) {
            index = 0;
        } else if(bs_db_1.contains(value)) {
            index = 1;
        } else if(bs_db_2.contains(value)) {
            index = 2;
        }



        for (String databaseName : databaseNames) {
            if(databaseName !=null && databaseName.endsWith(index.toString())) {
                System.out.println("====" + databaseName);
                return databaseName;
            }

        }
        throw new UnsupportedOperationException();
    }
}
