package com.turismo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .cors(cors -> cors.and()) // Habilitar CORS
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF si no es necesario
                .authorizeExchange(authorize -> authorize
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permitir solicitudes OPTIONS
                        .pathMatchers(HttpMethod.POST, "/keycloak-server/realms/myrealm/protocol/openid-connect/token")
                        .permitAll()
                        .pathMatchers("/auth/register", "/auth/login", "/actuator/health", "/public/")
                        .permitAll()
                        // Configurar rutas de "user" y "purchase" para los roles "PROVEEDOR",
                        // "COMPRADOR" y "ADMINISTRADOR"
                        .pathMatchers("/usuarios/**", "/credenciales/**", "/carrito/**")
                        .hasAnyRole("PROVEEDOR", "COMPRADOR", "ADMINISTRADOR")

                        // Configurar rutas de "servicio": GET permitido para los 3 roles, otros métodos
                        // solo para "PROVEEDOR" y "COMPRADOR"
                        .pathMatchers(HttpMethod.GET, "/servicios/**", "/actividad/**", "/alimentacion/**",
                                "/calificacion/**", "/hospedaje/**", "/transporte/**", "/voluntariado/**")
                        .hasAnyRole("PROVEEDOR", "COMPRADOR", "ADMINISTRADOR")
                        .pathMatchers("/servicios/**", "/actividad/**", "/alimentacion/**", "/hospedaje/**",
                                "/transporte/**", "/voluntariado/**")
                        .hasAnyRole("PROVEEDOR", "COMPRADOR")

                        // Excepción: la ruta de "Calificacion" permitida para los 3 roles en todos los
                        // métodos
                        .pathMatchers("/calificacion/**").hasAnyRole("PROVEEDOR", "COMPRADOR", "ADMINISTRADOR")

                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(
                                new KeycloakJwtAuthenticationConverter())))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200"); // Permitir el origen de Angular
        configuration.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, etc.)
        configuration.addAllowedHeader("*"); // Permitir todos los encabezados
        configuration.addExposedHeader("Authorization"); // Exponer el encabezado Authorization
        configuration.setAllowCredentials(true); // Permitir cookies y credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplicar configuración de CORS a todas las rutas
        return new CorsWebFilter(source);
    }
}
