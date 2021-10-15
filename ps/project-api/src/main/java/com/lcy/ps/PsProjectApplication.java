package com.lcy.ps;

import com.lcy.ps.core.EnableCoreModule;
import com.lcy.ps.project.EnableProjectModule;
import com.lcy.ps.security.EnableSecurityModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootConfiguration
@EnableAutoConfiguration
@EnableCoreModule
@EnableProjectModule
@EnableSecurityModule
public class PsProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsProjectApplication.class, args);
    }
}
