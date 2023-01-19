package com.eteacher.service.client;

import org.springframework.context.annotation.Bean;

public class RestClientConfiguration {

    @Bean
    public RestClientInterceptor fooRequestInterceptor() {
        return new RestClientInterceptor();
    }
}
