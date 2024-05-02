package me.scholagate.api.utils;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constans {


    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";

    // JWT
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    public static final String SUPER_SECRET_KEY = "ZnJhc2VzbGFyZ2FzcGFyYWNvbG9jYXJjb21vY2xhdmVlbnVucHJvamVjdG9kZWVtZXBsb3BhcmFqd3Rjb25zcHJpbmdzZWN1cml0eQ==bWlwcnVlYmFkZWVqbXBsb3BhcmFiYXNlNjQ=";

    public static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24  ; // 1 dia
    public static final long TOKEN_EXPIRATION_TIME_PASSWORD = 1000 * 60 * 60 * 2; // 1 hora


    public static Key getSigningKey(String secret) {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

    public static class URL {
        public static final String API = "/api/v1";
        public static final String UP = API+"/up";
        public static final String LOGIN= API+"/login";
        public static final String REGISTER = API+"/registerAdmin";
        public static final String PASSWD = API+"/passwd";

    }

    public static final class ENUM_ROLES {
        public static final String ADMIN = "Admin";
        public static final String  USER = "User";
    }
}
