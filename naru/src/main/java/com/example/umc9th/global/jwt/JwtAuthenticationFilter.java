package com.example.umc9th.global.jwt;

import com.example.umc9th.domain.auth.principal.PrincipalDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Request Header에서 토큰 추출
        String token = jwtTokenProvider.resolveToken(request);

        // 2. 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 3. 토큰이 유효하면 이메일을 뽑아서 DB에서 유저 정보를 가져옴
            String email = jwtTokenProvider.getEmail(token);
            UserDetails userDetails = principalDetailsService.loadUserByUsername(email);

            // 4. SecurityContext에 인증 객체 저장 (이게 되어야 @AuthenticationPrincipal 사용 가능)
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 5. 다음 필터로 넘김
        filterChain.doFilter(request, response);
    }
}