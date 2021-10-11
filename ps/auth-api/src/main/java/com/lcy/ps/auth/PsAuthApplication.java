package com.lcy.ps.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaAuditing
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.lcy.ps.core", "com.lcy.ps.auth"})
@EntityScan(basePackages = {"com.lcy.ps.core"})
@EnableJpaRepositories(basePackages = {"com.lcy.ps.core"})
public class PsAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsAuthApplication.class, args);
    }
}
