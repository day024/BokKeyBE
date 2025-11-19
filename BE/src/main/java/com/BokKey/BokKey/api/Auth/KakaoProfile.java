package com.BokKey.BokKey.api.Auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter  //
@NoArgsConstructor
public class KakaoProfile {
    private Long id;
    private KakaoAccount kakao_account;
    private Properties properties;

    @Getter
    @NoArgsConstructor
    public static class KakaoAccount {
        private String email;
    }

    @Getter
    @NoArgsConstructor
    public static class Properties {
        private String nickname;
    }
}

