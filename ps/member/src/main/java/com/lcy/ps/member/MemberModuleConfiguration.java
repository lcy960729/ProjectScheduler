package com.lcy.ps.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = MemberModuleConfiguration.class)
@EntityScan(basePackageClasses = MemberModuleConfiguration.class)
@EnableJpaRepositories(basePackageClasses = MemberModuleConfiguration.class)
@PropertySource("/datasource.properties")
public class MemberModuleConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Started {}", "Member Module");
    }
}
