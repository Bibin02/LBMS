package com.project.lbms.util;

import java.time.Instant;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class JwtUtil {

    private JwtEncoder jwtEncoder;

    public String generateToken(User user) {
        log.info("Generating token for user {}", user.getUsername());
        Instant now = Instant.now();
        var headers = JwsHeader.with(MacAlgorithm.HS256).build();
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .subject(user.getUsername())
                .claim("role", getRoles(user))
                .build();

        return AesUtil.encrypt(jwtEncoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue());
    }

    private List<String> getRoles(User user) {
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }

}
