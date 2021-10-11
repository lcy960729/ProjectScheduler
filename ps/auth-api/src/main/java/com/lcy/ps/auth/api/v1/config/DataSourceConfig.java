package com.lcy.ps.auth.api.v1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("/datasource.properties")
public class DataSourceConfig {
}
