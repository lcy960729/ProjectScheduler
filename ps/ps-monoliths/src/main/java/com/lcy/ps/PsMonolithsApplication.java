package com.lcy.ps;

import com.lcy.ps.core.EnableCoreModule;
import com.lcy.ps.member.EnableMemberModule;
import com.lcy.ps.project.EnableProjectModule;
import com.lcy.ps.security.EnableSecurityModule;
import com.lcy.ps.user.EnableUserModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableCoreModule
@EnableUserModule
@EnableMemberModule
@EnableProjectModule
@EnableSecurityModule
@PropertySource("/datasource.properties")
public class PsMonolithsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsMonolithsApplication.class, args);
    }
}
