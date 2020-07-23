package com.yuschool.config;

import com.yuschool.utils.interceptor.GenerateTimeInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer () {
        return configuration -> {
            configuration.setMapUnderscoreToCamelCase(true); // 这一个配置组件用来开启数据库的下划线命名法到驼峰命名法的转换
            configuration.getTypeAliasRegistry().registerAliases("java.lang");
            configuration.getTypeAliasRegistry().registerAliases("java.util");
        };
    }

    @Bean
    public GenerateTimeInterceptor generateTimeInterceptor() {
        return new GenerateTimeInterceptor();
    }
}
