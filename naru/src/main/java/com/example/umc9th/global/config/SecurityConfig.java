package com.example.umc9th.global.config;

import com.example.umc9th.domain.auth.principal.PrincipalDetailsService;
import com.example.umc9th.global.jwt.JwtAuthenticationFilter;
import com.example.umc9th.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF, Form Login, HTTP Basic 비활성화 (JWT 사용 시 필수)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable) // <-- 이 줄이 있어야 로그인 페이지가 안 뜹니다.
                .httpBasic(AbstractHttpConfigurer::disable)

                // 2. 세션 미사용 (Stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. 경로별 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // Swagger UI 관련 경로 허용
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()

                        // 로그인, 회원가입 허용
                        .requestMatchers("/api/users/join", "/api/auth/login").permitAll()

                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )

                // 4. JWT 필터 추가
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, principalDetailsService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}