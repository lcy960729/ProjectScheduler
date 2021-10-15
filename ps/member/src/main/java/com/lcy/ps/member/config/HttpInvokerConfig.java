package com.lcy.ps.member.config;

import com.lcy.ps.integrate.domain.member.GetMemberService;
import com.lcy.ps.integrate.domain.member.AuthMemberService;
import com.lcy.ps.integrate.domain.member.RegisterMemberService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@ConditionalOnProperty(value = "ps.run.microService",havingValue = "true")
public class HttpInvokerConfig {

    @Bean(name = "/httpInvoker/memberAuthService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(AuthMemberService memberAuthService) {
        HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(memberAuthService);
        invokerService.setServiceInterface(AuthMemberService.class);

        return invokerService;
    }

    @Bean(name = "/httpInvoker/getMemberService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(GetMemberService getMemberService) {
        HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(getMemberService);
        invokerService.setServiceInterface(GetMemberService.class);

        return invokerService;
    }

    @Bean(name = "/httpInvoker/registerMemberService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(RegisterMemberService registerMemberService) {
        HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(registerMemberService);
        invokerService.setServiceInterface(RegisterMemberService.class);

        return invokerService;
    }
}
