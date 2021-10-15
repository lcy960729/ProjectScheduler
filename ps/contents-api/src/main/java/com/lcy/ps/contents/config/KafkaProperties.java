package com.lcy.ps.contents.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("/kafka.properties")
public class KafkaProperties {
}
