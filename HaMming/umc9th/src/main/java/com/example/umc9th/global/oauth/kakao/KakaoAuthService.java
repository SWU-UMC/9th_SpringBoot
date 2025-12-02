package com.example.umc9th.global.oauth.kakao;

import com.example.umc9th.global.oauth.kakao.dto.KakaoTokenResponse;
import com.example.umc9th.global.oauth.kakao.dto.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final KakaoOAuthProperties properties;

    /**
     * 인가코드 -> 액세스 토큰
     */
    public KakaoTokenResponse getAccessToken(String code) {

        return WebClient.create()
                .post()
                .uri(properties.getTokenUri())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters
                        .fromFormData("grant_type", "authorization_code")
                        .with("client_id", properties.getClientId())
                        .with("redirect_uri", properties.getRedirectUri())
                        .with("code", code)
                        .with("client_secret", properties.getClientSecret()))
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .block();
    }

    /**
     * 액세스 토큰 -> 카카오 유저 정보
     */
    public KakaoUserResponse getUserInfo(String accessToken) {

        return WebClient.create()
                .get()
                .uri(properties.getUserInfoUri())
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoUserResponse.class)
                .block();
    }
}
