package com.lcy.ps.project;

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
@ComponentScan(basePackageClasses = ProjectModuleConfiguration.class)
@EntityScan(basePackageClasses = ProjectModuleConfiguration.class)
@EnableJpaRepositories(basePackageClasses = ProjectModuleConfiguration.class)
@PropertySource("/datasource.properties")
public class ProjectModuleConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Started {}", "Project Module");
    }
}
