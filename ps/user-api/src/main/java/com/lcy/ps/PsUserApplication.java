package com.lcy.ps;

import com.lcy.ps.core.EnableCoreModule;
import com.lcy.ps.user.EnableUserModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableCoreModule
@EnableUserModule
public class PsUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsUserApplication.class, args);
    }
}

