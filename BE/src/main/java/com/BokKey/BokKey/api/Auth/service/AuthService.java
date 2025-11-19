package com.BokKey.BokKey.api.Auth.service;

import com.BokKey.BokKey.api.Auth.KakaoClient;
import com.BokKey.BokKey.api.Auth.KakaoProfile;
import com.BokKey.BokKey.api.Auth.Member;
import com.BokKey.BokKey.api.Auth.MemberRepository;
import com.BokKey.BokKey.api.Auth.dto.LoginRequest;
import com.BokKey.BokKey.api.Auth.dto.LoginResponse;
import com.BokKey.BokKey.api.Auth.dto.SignupRequest;
import com.BokKey.BokKey.api.Auth.dto.SignupResponse;

import com.BokKey.BokKey.global.Config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final KakaoClient kakaoClient;
    private final JwtTokenProvider jwtTokenProvider;

    public SignupResponse signup(SignupRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = Member.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .birthDate(request.birthDate())
                .phoneNumber(request.phoneNumber())
                .build();

        memberRepository.save(member);

        return new SignupResponse(member.getId(), member.getEmail(), member.getName());
    }

    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtTokenProvider.createToken(member.getId());

        return new LoginResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                token
        );
    }

    public LoginResponse kakaoLogin(String kakaoAccessToken) {
        KakaoProfile profile = kakaoClient.getUserProfile(kakaoAccessToken);

        String email = profile.getKakao_account().getEmail();
        String nickname = profile.getProperties().getNickname();

        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> {
                    Member newMember = Member.builder()
                            .email(email)
                            .password("")
                            .name(nickname)
                            .birthDate("")
                            .phoneNumber("")
                            .build();
                    return memberRepository.save(newMember);
                });

        String token = jwtTokenProvider.createToken(member.getId());

        return new LoginResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                token
        );
    }
}
