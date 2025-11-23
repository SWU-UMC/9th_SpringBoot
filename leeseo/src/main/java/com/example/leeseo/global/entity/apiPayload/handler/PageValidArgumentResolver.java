package com.example.leeseo.global.entity.apiPayload.handler;

import com.example.leeseo.global.annotation.PageValid;
import com.example.leeseo.global.entity.apiPayload.exception.PageValidateException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@Component
public class PageValidArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageValid.class)
                && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        String paramName = parameter.getParameterName();
        String pageStr = webRequest.getParameter(paramName);
        Integer page;

        try {
            page = pageStr != null ? Integer.valueOf(pageStr) : null;
        } catch (NumberFormatException e) {
            throw new PageValidateException(
                    Map.of(paramName, "Page는 숫자여야 합니다.")
            );
        }

        if (page == null || page <= 0) {
            throw new PageValidateException(
                    Map.of(paramName, "Page 번호는 1 이상이어야 합니다.")
            );
        }

        return page;
    }
}
