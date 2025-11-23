package com.example.leeseo.global.entity.config;

import com.example.leeseo.global.entity.apiPayload.handler.PageValidArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PageValidArgumentResolver pageValidArgumentResolver;

    public WebConfig(PageValidArgumentResolver pageValidArgumentResolver) {
        this.pageValidArgumentResolver = pageValidArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageValidArgumentResolver);
    }
}