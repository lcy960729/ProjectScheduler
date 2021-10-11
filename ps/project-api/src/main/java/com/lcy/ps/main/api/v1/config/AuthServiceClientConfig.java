package com.lcy.ps.main.api.v1.config;

import com.lcy.ps.main.api.v1.domain.user.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class AuthServiceClientConfig {

    @Bean
    public AuthService authService() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceInterface(AuthService.class);
        factoryBean.setServiceUrl("http://localhost:8081/httpInvoker/authService");
        factoryBean.afterPropertiesSet();
        return (AuthService) factoryBean.getObject();
    }
}
