package com.lcy.ps.contents.api.v1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("/kafka.properties")
public class KafkaProperties {
}
