package com.example.wizytydomowe.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(JwtUtil.generateRefreshToken(username)); // Assuming generateRefreshToken returns a String token
        refreshToken.setUsername(username);
        refreshToken.setExpirationDate(new Date(System.currentTimeMillis() + JwtUtil.REFRESH_EXPIRATION_TIME));
        return refreshTokenRepository.save(refreshToken);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupExpiredTokens() {
        refreshTokenRepository.deleteAllExpiredTokens(new Date());
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteById(token);
    }
}
