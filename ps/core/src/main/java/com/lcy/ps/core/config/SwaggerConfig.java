package com.lcy.ps.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@PropertySource("/swagger.properties")
public class SwaggerConfig {
}