package me.chohyeonjae.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.chohyeonjae.springbootdeveloper.domain.RefreshToken;
import me.chohyeonjae.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
