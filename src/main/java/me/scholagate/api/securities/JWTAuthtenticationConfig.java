package me.scholagate.api.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static me.scholagate.api.security.Constans.*;


@Configuration
public class JWTAuthtenticationConfig {

    public static String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("chatappJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(SUPER_SECRET_KEY),  SignatureAlgorithm.HS512).compact();

        return TOKEN_BEARER_PREFIX + token;
    }

    public static String getUsernameFromToken(String token) {
        // Remove the "Bearer " prefix
        if (token.startsWith(TOKEN_BEARER_PREFIX)) {
            token = token.substring(TOKEN_BEARER_PREFIX.length());
        }

        // Parse the token
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SUPER_SECRET_KEY))
                .build()
                .parseClaimsJws(token);

        // Get the username
        return jws.getBody().getSubject();
    }
}
