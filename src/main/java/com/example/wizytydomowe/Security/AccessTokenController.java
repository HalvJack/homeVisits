package com.example.wizytydomowe.Security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class AccessTokenController {
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("Refresh-Token");
        try {
            String username = JwtUtil.verifyToken(refreshToken).getSubject();
            // Sprawdź czy refresh token jest ważny, np. czy istnieje w bazie danych i nie wygasł

            String newAccessToken = JwtUtil.generateToken(username);
            String newRefreshToken = JwtUtil.generateRefreshToken(username);

            // Zaktualizuj refresh token w bazie danych, jeśli generujesz nowy

            return ResponseEntity.ok(Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken));
        } catch (JWTVerificationException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid refresh token");
        }
    }
}
