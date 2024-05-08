package me.scholagate.api.utils;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constans {


    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";

    // JWT
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    public static String SUPER_SECRET_KEY = "e5790a7d5f3bf4f50965b9276eb3763abac0d38a6e19d1bb552dadfd1414b3198b5cf7bd71a5caf322f9eeb0b957cc58337613b959f02a7c2a8a3b16d6ab7ebf";

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

        //Rutas para Reportes y Adjuntos Post para Usuarios con Rol User
        public static final String REPORTE = API+"/reporte";
        public static final String ADJUNTO = API+"/adjunto";

    }

    public static final class ENUM_ROLES {
        public static final String ADMIN = "ROLE_Admin";
        public static final String  USER = "ROLE_User";
        public static final String  Passwd = "ROLE_PassWd";
    }
}
