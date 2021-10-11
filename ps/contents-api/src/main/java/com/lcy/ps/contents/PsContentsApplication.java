package com.lcy.ps.contents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@EnableJpaAuditing
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.lcy.ps.core", "com.lcy.ps.contents"})
@EntityScan(basePackages = {"com.lcy.ps.core", "com.lcy.ps.contents"})
@EnableJpaRepositories(basePackages = {"com.lcy.ps.core", "com.lcy.ps.contents"})
@EnableKafka
public class PsContentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsContentsApplication.class, args);
    }
}
