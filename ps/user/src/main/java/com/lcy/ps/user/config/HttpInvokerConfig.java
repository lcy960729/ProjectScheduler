package com.lcy.ps.user.config;

import com.lcy.ps.integrate.domain.user.UserAuthService;
import com.lcy.ps.integrate.domain.user.GetUserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@ConditionalOnProperty(value = "ps.run.microService", havingValue = "true")
public class HttpInvokerConfig {

    @Bean(name = "/httpInvoker/userAuthService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(UserAuthService userAuthService) {
        HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(userAuthService);
        invokerService.setServiceInterface(UserAuthService.class);

        return invokerService;
    }

    @Bean(name = "/httpInvoker/getUserService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(GetUserService getUserService) {
        HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(getUserService);
        invokerService.setServiceInterface(GetUserService.class);

        return invokerService;
    }
}
