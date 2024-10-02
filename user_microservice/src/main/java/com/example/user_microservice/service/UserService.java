package com.example.user_microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_microservice.model.Usuario;
import com.example.user_microservice.repository.UserRepository;

@Service
public class UserService {

    
    @Autowired
    private UserRepository userRepository;

   
    public List<Usuario> obtenerTodosLosUsuarios() {
        return userRepository.findAll();
    }

    public Usuario obtenerUsuario(Long idUser) {
        return userRepository.findById(idUser).orElseThrow();
    }

    public Usuario crearUsuario(Usuario usuario) {
        return userRepository.save(usuario);
    }

    public boolean eliminarUsuario(Long id) {
        Optional<Usuario> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Usuario actualizarUsuario(Long iduser, Usuario datosActualizados) {
        Optional<Usuario> usuarioExistente = userRepository.findById(iduser);
    
        if (usuarioExistente.isPresent()) {
            Usuario userExistente = usuarioExistente.get();
    
            if (datosActualizados.getNombre() != null && !datosActualizados.getNombre().isEmpty()) {
                userExistente.setNombre(datosActualizados.getNombre());
            }
            if (datosActualizados.getEdad() != 0) {
                userExistente.setEdad(datosActualizados.getEdad());
            }
            if (datosActualizados.getFoto() != null && !datosActualizados.getFoto().isEmpty()) {
                userExistente.setFoto(datosActualizados.getFoto());
            }
            if (datosActualizados.getDescripcion() != null && !datosActualizados.getDescripcion().isEmpty()) {
                userExistente.setDescripcion(datosActualizados.getDescripcion());
            }
            if (datosActualizados.getEmail() != null && !datosActualizados.getEmail().isEmpty()) {
                userExistente.setEmail(datosActualizados.getEmail());
            }
            if (datosActualizados.getPassword() != null && !datosActualizados.getPassword().isEmpty()) {
                userExistente.setPassword(datosActualizados.getPassword());
            }
            if (datosActualizados.getRol() != null && !datosActualizados.getRol().isEmpty()) {
                userExistente.setRol(datosActualizados.getRol());
            }
    
            
            return userRepository.save(userExistente);
        } else {
            return null; 
        }
    }
    
    
    
}
