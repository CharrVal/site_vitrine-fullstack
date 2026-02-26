package com.artisan.vitrine.security;

import com.artisan.vitrine.security.Jwt.JwtAuthenticationFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.Filter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class AppConfigSecurity {

    protected final Log logger = LogFactory.getLog(getClass());
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public AppConfigSecurity(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    // accueil (tout le monde y a accès)
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/accueil").permitAll()
                    .requestMatchers("/connexion").permitAll()
                    .requestMatchers("/error").permitAll()

                    // ACCÈS PUBLIC
                    .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/actualites/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/contact").permitAll()
                    .requestMatchers(HttpMethod.GET, "/images/**").permitAll()

                    // ACCÈS ADMIN
                    .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")

                    .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN")

                    .requestMatchers(HttpMethod.POST, "/api/actualites/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/actualites/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/actualites/**").hasRole("ADMIN")

                    .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

                    .anyRequest().authenticated();
        });

        // Désactivé Cross Site Request Forgery
        // Non préconisé pour les API REST en stateless.
        // Sauf pour POST, PUT, PATCH et DELETE
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.cors(Customizer.withDefaults());

        http.headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                        .policyDirectives(
                                "default-src 'self'; " +
                                        "script-src 'self'; " +
                                        "style-src 'self'; " +
                                        "img-src 'self' data:; " +
                                        "font-src 'self'; " +
                                        "connect-src 'self' http://localhost:4000 http://localhost:8080" // en prod : connect-src 'self' https://mon-domaine.com
                        )
                )
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
        );

        //Connexion de l'utilisateur
        http.authenticationProvider(authenticationProvider);

        //Activer le filtre JWT et l'authentication de l'utilisateur
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        //http.formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*")); // A remplacer en prod par : "https://ton-front.com"
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
