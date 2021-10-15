package com.lcy.ps.project.config;

import com.lcy.ps.integrate.domain.member.GetMemberService;
import com.lcy.ps.integrate.domain.member.AuthMemberService;
import com.lcy.ps.integrate.domain.member.RegisterMemberService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
@ConditionalOnProperty(value = "ps.run.microService",havingValue = "true")
public class MemberDomainServiceClientConfig {

    @Bean
    public AuthMemberService memberAuthService() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceInterface(AuthMemberService.class);
        factoryBean.setServiceUrl("http://localhost:8083/httpInvoker/memberAuthService");
        factoryBean.afterPropertiesSet();
        return (AuthMemberService) factoryBean.getObject();
    }

    @Bean
    public GetMemberService getMemberService() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceInterface(GetMemberService.class);
        factoryBean.setServiceUrl("http://localhost:8083/httpInvoker/getMemberService");
        factoryBean.afterPropertiesSet();
        return (GetMemberService) factoryBean.getObject();
    }

    @Bean
    public RegisterMemberService registerMemberService() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceInterface(RegisterMemberService.class);
        factoryBean.setServiceUrl("http://localhost:8083/httpInvoker/registerMemberService");
        factoryBean.afterPropertiesSet();
        return (RegisterMemberService) factoryBean.getObject();
    }
}

