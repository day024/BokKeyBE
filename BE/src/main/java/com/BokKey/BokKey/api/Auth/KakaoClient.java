package com.BokKey.BokKey.api.Auth;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoClient {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://kapi.kakao.com")
            .build();

    public KakaoProfile getUserProfile(String accessToken) {
        return webClient.get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoProfile.class)
                .block();
    }
}
