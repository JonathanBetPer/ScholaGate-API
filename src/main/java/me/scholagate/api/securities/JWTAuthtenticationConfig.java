package me.scholagate.api.securities;

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
import static me.scholagate.api.utils.Constans.*;

@Configuration
public class JWTAuthtenticationConfig {
    //TODO: Cambiar setId a Env Var
    public static String getJWTToken(long timeExp, String username, String rol) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setId("scholagateJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + timeExp))
                .signWith(getSigningKey(SUPER_SECRET_KEY),  SignatureAlgorithm.HS512).compact();
        return token;
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

    public static List<String> getRolesFromToken(String token) {
        // Remove the "Bearer " prefix
        if (token.startsWith(TOKEN_BEARER_PREFIX)) {
            token = token.substring(TOKEN_BEARER_PREFIX.length());
        }

        // Parse the token
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SUPER_SECRET_KEY))
                .build()
                .parseClaimsJws(token);

        // Get the roles
        return jws.getBody().get("authorities", List.class);
    }
}
