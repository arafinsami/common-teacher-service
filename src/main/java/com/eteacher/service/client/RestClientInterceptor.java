package com.eteacher.service.client;

import com.eteacher.service.security.ActiveUserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestClientInterceptor implements RequestInterceptor {
    @Autowired
    private ActiveUserContext context;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer " + context.getLoggedInUserToken());
    }
}
