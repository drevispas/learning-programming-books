package org.demo.carbe.web;

import lombok.*;
import org.demo.carbe.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/token")
@RestController
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/create")
    public TokenResponse createToken(@RequestBody TokenRequest tokenRequest) {
        return TokenResponse.builder()
                .accessToken(tokenService.generateToken(tokenRequest.username, tokenRequest.password))
                .build();
    }

    @Data
    @Builder
    public static class TokenRequest {
        private String username;
        private String password;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class TokenResponse {
        private String accessToken;
    }
}