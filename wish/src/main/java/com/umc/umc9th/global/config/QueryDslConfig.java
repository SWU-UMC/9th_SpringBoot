package com.umc.umc9th.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QueryDSL 설정 파일
 * JPAQueryFactory를 Bean으로 등록하여
 * RepositoryImpl 클래스에서 주입받을 수 있게 함.
 */
@Configuration
public class QueryDslConfig {

  @Bean
  public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
    return new JPAQueryFactory(entityManager);
  }
}