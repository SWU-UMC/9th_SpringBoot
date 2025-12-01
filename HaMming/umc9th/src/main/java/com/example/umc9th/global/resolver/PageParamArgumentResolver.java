package com.example.umc9th.global.resolver;

import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PageParamArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageParam.class)
                && Integer.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {

        String pageStr = webRequest.getParameter("page");

        // page 파라미터가 없는 경우도 잘못된 요청으로 처리
        if (pageStr == null) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }

        try {
            int page = Integer.parseInt(pageStr);

            if (page <= 0) {
                throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
            }

            return page;
        } catch (NumberFormatException e) {
            // 숫자가 아닌 값이 들어온 경우
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }
    }
}
