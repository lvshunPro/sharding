package com.lv.demo.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.lv.demo.sharding.algorithm.PreciseShardingDBAlogorithm;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import io.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
import io.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/***
 * @Title
 * @author lv
 * @Date 2020/3/11 17:41
 */
@Configuration
public class ShardingConfig {

    @Autowired
    public ApolloParam apolloParam;

    @Bean
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        ShardingRuleConfiguration config = new ShardingRuleConfiguration();
        //配置分表
        config.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        // 配置分库
        PreciseShardingDBAlogorithm preciseShardingDBAlogorithm = new PreciseShardingDBAlogorithm();
        preciseShardingDBAlogorithm.setApolloParam(apolloParam);
        config.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",
                preciseShardingDBAlogorithm));

        return config;
    }


    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order");
        result.setActualDataNodes("db${0..2}.t_order");
        return result;
    }

    @Bean
    public SpringBootPropertiesConfigurationProperties propMapProperties() {
        SpringBootPropertiesConfigurationProperties properties = new SpringBootPropertiesConfigurationProperties();
        Properties properties1 = new Properties();
        properties1.setProperty("sql.show","true");
        properties.setProps(properties1);
        return properties;
    }


//    private Map<String, DataSource> createDataSourceMap() {
//        // 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//
//        // 配置第一个数据源
//        DruidDataSource dataSource1 = new DruidDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/db0");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("123456");
//        dataSourceMap.put("db0", dataSource1);
//
//        // 配置第二个数据源
//        DruidDataSource dataSource2 = new DruidDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://localhost:3306/db1");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("123456");
//        dataSourceMap.put("db1", dataSource2);
//        // 配置第三个数据源
//        DruidDataSource dataSource3 = new DruidDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://localhost:3306/db2");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("123456");
//        dataSourceMap.put("db2", dataSource3);
//        return dataSourceMap;
//    }

//    class PreciseShardingDBAlogorithm implements PreciseShardingAlgorithm<Integer> {
//
//        public PreciseShardingDBAlogorithm() {
//            System.out.println("---------" + apolloParam.getBigTimeOut());
//        }
//
//         List<Integer> bs_db_0 = Arrays.asList(new Integer[]{1,2,3});
//         List<Integer> bs_db_1 = Arrays.asList(new Integer[]{4,5,6});
//         List<Integer> bs_db_2 = Arrays.asList(new Integer[]{7,8,9});
//
//
//
//        @Override
//        public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Integer> shardingValue) {
//        if(apolloParam ==null) {
//            System.out.println("********* apolloParam = null");
//        } else {
//            System.out.println("========= apolloParam = " + apolloParam.getBigTimeOut());
//        }
//
//            Integer value = shardingValue.getValue();
//            System.out.println("=====" + value);
//            Integer index = 0;
//            if(bs_db_0.contains(value)) {
//                index = 0;
//            } else if(bs_db_1.contains(value)) {
//                index = 1;
//            } else if(bs_db_2.contains(value)) {
//                index = 2;
//            }
//
//
//            Integer dbIndex = 0;
//            for (String databaseName : databaseNames) {
//                if(index.equals(dbIndex)) {
//                    System.out.println("====" + databaseName);
//                    return databaseName;
//                }
//                dbIndex ++;
//            }
//            throw new UnsupportedOperationException();
//        }
//    }
}
