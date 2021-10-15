package com.lcy.ps.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@ComponentScan(basePackageClasses = UserModuleConfiguration.class)
@EntityScan(basePackageClasses = UserModuleConfiguration.class)
@EnableJpaRepositories(basePackageClasses = UserModuleConfiguration.class)
@PropertySource("/datasource.properties")
public class UserModuleConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Started {}", "user module");
    }
}
