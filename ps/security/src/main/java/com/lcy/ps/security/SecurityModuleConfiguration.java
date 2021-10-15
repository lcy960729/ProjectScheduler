package com.lcy.ps.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = SecurityModuleConfiguration.class)
public class SecurityModuleConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.info("Started {}", "Security Module");
    }
}
