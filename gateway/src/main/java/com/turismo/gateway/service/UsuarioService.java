package com.turismo.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.turismo.gateway.dto.UsuarioRegisterDTO;

import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final WebClient webClient;

    @Value("${USUARIOS_URL}")
    private String usuariosUrl;

    public UsuarioService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<UsuarioRegisterDTO> getUsuarioById(Long usuarioId, String token) {
        return webClient.get()
                .uri(usuariosUrl + "/usuarios/{id}", usuarioId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(UsuarioRegisterDTO.class);
    }

    public Mono<UsuarioRegisterDTO> crearUsuario(UsuarioRegisterDTO usuarioRequest, String bearerToken) {
        return webClient.post()
                .uri(usuariosUrl + "/usuarios")
                .header("Authorization", "Bearer " + bearerToken)
                .body(Mono.just(usuarioRequest), Object.class)
                .retrieve()
                .bodyToMono(UsuarioRegisterDTO.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    return Mono.error(new RuntimeException("Error al crear Usuario: " + e.getMessage()));
                });
    }

    public Mono<UsuarioRegisterDTO> getUsuarioByUsername(String username, String token){
        return webClient.get()
                .uri(usuariosUrl + "/usuarios/username/{username}", username)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(UsuarioRegisterDTO.class);
    }
}