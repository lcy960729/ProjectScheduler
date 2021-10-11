package com.lcy.ps.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.lcy.ps.core", "com.lcy.ps.main"})
@EntityScan(basePackages = {"com.lcy.ps.core", "com.lcy.ps.main"})
@EnableJpaRepositories(basePackages = {"com.lcy.ps.core", "com.lcy.ps.main"})
@EnableAspectJAutoProxy
public class PsMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsMainApplication.class, args);
    }
}
