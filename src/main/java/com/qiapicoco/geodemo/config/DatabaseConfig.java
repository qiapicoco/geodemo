package com.qiapicoco.geodemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.qiapicoco.geodemo.repository")
public class DatabaseConfig {
    // 这里可以添加更多数据库相关的配置，如数据源、事务管理等
}