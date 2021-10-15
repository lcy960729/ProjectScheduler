package com.lcy.ps.security.config;

import com.lcy.ps.integrate.domain.user.UserAuthService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
@ConditionalOnProperty(value = "ps.run.microService", havingValue = "true")
public class UserAuthServiceClientConfig {

    @Bean
    public UserAuthService userAuthService() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceInterface(UserAuthService.class);
        factoryBean.setServiceUrl("http://localhost:8081/httpInvoker/userAuthService");
        factoryBean.afterPropertiesSet();
        return (UserAuthService) factoryBean.getObject();
    }
}
