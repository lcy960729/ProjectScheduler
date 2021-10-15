package com.lcy.ps;

import com.lcy.ps.core.EnableCoreModule;
import com.lcy.ps.member.EnableMemberModule;
import com.lcy.ps.security.EnableSecurityModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableCoreModule
@EnableMemberModule
@EnableSecurityModule
public class PsMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsMemberApplication.class, args);
    }
}
