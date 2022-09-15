package ru.grabovsky.productback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.mvcMatcher("/prod-service/api/v1/**")
                .authorizeRequests()
                .mvcMatchers("/prod-service/api/v1/**")
                .access("hasAuthority('SCOPE_read')")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
