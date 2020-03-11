package com.lv.demo.sharding.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @Title
 * @author lv
 * @Date 2020/3/11 15:48
 */
@Configuration
@EnableApolloConfig
public class ApolloConfig {

    @Bean
    public ApolloParam apolloParam() {
        return new ApolloParam();
    }
}
