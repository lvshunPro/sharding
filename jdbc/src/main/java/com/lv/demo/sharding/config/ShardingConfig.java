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
    @Autowired
    public DB0Config db0Config;
    @Autowired
    public DB1Config db1Config;
    @Autowired
    public DB2Config db2Config;



    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        ShardingRuleConfiguration config = new ShardingRuleConfiguration();
        //数据节点定义
        config.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        // 配置分库
        config.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",
                getPreciseShardingDBAlogorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), config,
                new HashMap<String, Object>(), getProperties());

    }

    //分库策略获取
    private PreciseShardingDBAlogorithm getPreciseShardingDBAlogorithm() {
        PreciseShardingDBAlogorithm dbAlogorithm = new PreciseShardingDBAlogorithm();
        dbAlogorithm.setApolloParam(apolloParam);
        return dbAlogorithm;
    }

    //设置参数
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("sql.show","true");
        return properties;
    }

    //真实数据源
    private Map<String, DataSource> createDataSourceMap() {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        dataSourceMap.put(db0Config.getDatabaseName(), db0Config.createDataSource());
        dataSourceMap.put(db1Config.getDatabaseName(), db1Config.createDataSource());
        dataSourceMap.put(db2Config.getDatabaseName(), db2Config.createDataSource());
        return dataSourceMap;
    }

    private TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order");
        result.setActualDataNodes("db${0..2}.t_order");
        return result;
    }
}
