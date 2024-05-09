package me.scholagate.api.securities;

import me.scholagate.api.utils.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig{

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( authz -> authz

                        // Swagger and OpenAPI endpoints abierto para todos
                        .requestMatchers("/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                                .permitAll()

                        // EndPoint de Autenticación abierta para anónimos
                        .requestMatchers(HttpMethod.GET, Constans.URL.UP, "api/v1/token").anonymous()
                        .requestMatchers(HttpMethod.POST,
                                            Constans.URL.LOGIN,
                                            Constans.URL.REGISTER
                                ).anonymous()

                        // Cuando se requiera autenticación para cualquier otro EndPoint se requiere que el usuario tenga el rol de Admin
                        .anyRequest().authenticated()

                )
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
