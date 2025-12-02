package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.oauth.kakao.KakaoAuthService;
import com.example.umc9th.global.oauth.kakao.KakaoOAuthProperties;
import com.example.umc9th.global.oauth.kakao.dto.KakaoTokenResponse;
import com.example.umc9th.global.oauth.kakao.dto.KakaoUserResponse;
import com.example.umc9th.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoOAuthProperties kakaoOAuthProperties;
    private final KakaoAuthService kakaoAuthService;

    // 1) 회원가입 (DB 저장)
    @PostMapping("/signup")
    public ApiResponse<MemberResponseDto.SignUpResponse> signup(
            @RequestBody MemberRequestDto.SignUpRequest request
    ) {
        MemberResponseDto.SignUpResponse response = memberService.signup(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 2) 로그인 (JWT 발급)
    @PostMapping("/login")
    public ApiResponse<MemberResponseDto.LoginResponse> login(
            @RequestBody MemberRequestDto.LoginRequest request
    ) {
        // 이메일 + 비밀번호 검증
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);

        // JWT 생성
        String accessToken = jwtTokenProvider.createAccessToken(authentication);

        // 사용자 정보 조회
        Member member = memberService.findByEmail(request.getEmail());

        // 응답 DTO
        MemberResponseDto.LoginResponse response =
                MemberConverter.toLoginResponse(member, accessToken);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }


    /**
     * 카카오 로그인 버튼 클릭 시 호출
     * 브라우저를 카카오 로그인 화면으로 리다이렉트
     */
    @GetMapping("/oauth/kakao")
    public void redirectToKakao(HttpServletResponse response) throws IOException {

        String url = "https://kauth.kakao.com/oauth/authorize"
                + "?response_type=code"
                + "&client_id=" + kakaoOAuthProperties.getClientId()
                + "&redirect_uri=" + kakaoOAuthProperties.getRedirectUri();

        response.sendRedirect(url);
    }
    /**
     * 카카오에서 인가코드(code)를 들고 돌아오는 콜백 엔드포인트
     */
    @GetMapping("/kakao/callback")
    public ApiResponse<MemberResponseDto.LoginResponse> kakaoCallback(
            @RequestParam("code") String code
    ) {

        // 1) 인가코드 -> AccessToken
        KakaoTokenResponse token = kakaoAuthService.getAccessToken(code);

        // 2) AccessToken -> 카카오 유저 정보
        KakaoUserResponse userInfo = kakaoAuthService.getUserInfo(token.getAccess_token());

        // 3) 우리 서비스 Member 조회 / 회원가입
        Member member = memberService.kakaoLogin(
                userInfo.getId(),
                userInfo.getKakao_account().getEmail(),
                userInfo.getKakao_account().getProfile().getNickname()
        );

        // 4) JWT 발급 (일반 로그인과 동일한 방식)
        String accessToken = jwtTokenProvider.createAccessToken(
                member.getEmail(),
                member.getRole().name()
        );

        // 5) 프론트에 내려줄 공통 로그인 응답
        MemberResponseDto.LoginResponse response =
                MemberConverter.toLoginResponse(member, accessToken);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

}
