package com.lcy.ps.member.config;

import com.lcy.ps.integrate.domain.user.GetUserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
@ConditionalOnClass
@ConditionalOnProperty(value = "ps.run.microService", havingValue = "true")
public class GetUserServiceClientConfig {

    @Bean
    public GetUserService getUserService() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceInterface(GetUserService.class);
        factoryBean.setServiceUrl("http://localhost:8081/httpInvoker/getUserService");
        factoryBean.afterPropertiesSet();
        return (GetUserService) factoryBean.getObject();
    }
}
