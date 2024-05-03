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

import static me.scholagate.api.utils.Constans.URL.API;

@EnableWebSecurity
@Configuration
class WebSecurityConfig{

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( authz -> authz
                        // Swagger and OpenAPI endpoints abierto para todos
                        .requestMatchers("/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                                .permitAll()
                        // EndPoint de Autenticación abierto para anónimos
                        .requestMatchers(HttpMethod.GET, Constans.URL.UP).anonymous()
                        .requestMatchers(HttpMethod.POST, Constans.URL.LOGIN).anonymous()
                        .requestMatchers(HttpMethod.POST, Constans.URL.REGISTER).anonymous()
                        .requestMatchers(HttpMethod.POST, Constans.URL.PASSWD).anonymous()
                        // EndPoint Cambio de Contraseña Capado por Rol
                        .requestMatchers(HttpMethod.GET, Constans.URL.PASSWD).hasRole(Constans.ENUM_ROLES.Passwd)


                        .anyRequest().authenticated())

                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
