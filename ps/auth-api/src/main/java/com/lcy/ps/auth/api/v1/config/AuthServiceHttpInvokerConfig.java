package com.lcy.ps.auth.api.v1.config;

import com.lcy.ps.auth.api.v1.domain.user.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@ComponentScan(basePackageClasses = {AuthService.class})
public class AuthServiceHttpInvokerConfig {

    @Autowired
    private AuthService authService;

    @Bean(name = "/httpInvoker/authService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(authService);
        invokerService.setServiceInterface(AuthService.class);
        return invokerService;
    }
}
