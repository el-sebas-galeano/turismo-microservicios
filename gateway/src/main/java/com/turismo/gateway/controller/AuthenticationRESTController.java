package com.turismo.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turismo.gateway.dto.UsuarioRegisterDTO;
import com.turismo.gateway.service.KeycloakAdminService;
import com.turismo.gateway.service.UsuarioService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthenticationRESTController {

    @Autowired
    private KeycloakAdminService keycloakAdminService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public Mono<ResponseEntity<UsuarioRegisterDTO>> registerUser(@RequestParam String role,
            @RequestBody UsuarioRegisterDTO userDTO) {

        System.out.println("Inicio de registro de usuario");
        System.out.println("Rol recibido: " + role);
        System.out.println("Datos del usuario recibidos: " + userDTO);

        String adminToken = keycloakAdminService.getAdminToken();
        System.out.println("Token de administrador obtenido: " + adminToken);

        return keycloakAdminService
                .createUserInKeycloak(adminToken, userDTO.getCredencial().getUsername(),
                        userDTO.getCredencial().getPassword(), role)
                .flatMap(keycloakUserId -> {
                    System.out.println("Usuario creado en Keycloak con ID: " + keycloakUserId);

                    return keycloakAdminService
                            .loginUser(userDTO.getCredencial().getUsername(),
                                    userDTO.getCredencial().getPassword())
                            .flatMap(userToken -> {
                                System.out.println(
                                        "Token de usuario obtenido después de login: " + userToken);

                                System.out.println(
                                        "Datos del usuario a enviar al microservicio de usuarios: " + userDTO);

                                return usuarioService
                                        .crearUsuario(userDTO, userToken)
                                        .map(usuarioCreado -> {
                                            System.out.println(
                                                    "Usuario creado en el microservicio de usuarios: " + usuarioCreado);

                                            System.out.println(
                                                    "Registro de usuario completo: " + usuarioCreado);
                                            return ResponseEntity
                                                    .ok()
                                                    .header("Authorization", "Bearer " + userToken)
                                                    .body(usuarioCreado);
                                        });
                            });
                })
                .onErrorResume(e -> {
                    System.out.println("Error durante el registro del usuario: " + e.getMessage());
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<UsuarioRegisterDTO>> loginUser(@RequestParam String username,
            @RequestParam String password) {

        return keycloakAdminService.loginUser(username, password)
                .flatMap((String userToken) -> {
                    // Obtener el usuario directamente desde el microservicio de usuarios
                    return usuarioService.getUsuarioByUsername(username, userToken)
                            .map((UsuarioRegisterDTO usuario) -> {

                                System.out.println("Usuario obtenido: " + usuario);

                                // Retornar respuesta con el token en el header y el usuario en el cuerpo
                                return ResponseEntity.ok()
                                        .header("Authorization", "Bearer " + userToken)
                                        .body(usuario);
                            });
                })
                .onErrorResume(e -> {
                    System.out.println("Error durante el inicio de sesión: " + e.getMessage());
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
                });
    }
}
