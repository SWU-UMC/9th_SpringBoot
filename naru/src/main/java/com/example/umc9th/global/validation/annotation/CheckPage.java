package com.example.umc9th.global.validation.annotation;

import com.example.umc9th.global.validation.validator.CheckPageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckPageValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD}) // 파라미터에도 적용 가능하게 설정
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "페이지 번호는 1 이상이어야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}