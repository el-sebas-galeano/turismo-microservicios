package com.example.user_microservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_microservice.model.Usuario;
import com.example.user_microservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/listarUsuarios")
  public ResponseEntity<List<Usuario>> obtenerUsuarios() {
    List<Usuario> usuarios = userService.obtenerTodosLosUsuarios();
    return new ResponseEntity<>(usuarios, HttpStatus.OK);
  }

  @GetMapping("/findUsuario/{iduser}")
  public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long iduser) {
    Usuario usuario = userService.obtenerUsuario(iduser); // Asegúrate de usar iduser
    return new ResponseEntity<>(usuario, HttpStatus.OK);
  }

  @PostMapping("/crearUsuario")
  public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
    Usuario nuevoUsuario = userService.crearUsuario(usuario);
    return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
  }

  @DeleteMapping("/eliminarUsuario/{iduser}")
  public ResponseEntity<Void> eliminarUser(@PathVariable Long iduser) {
    boolean eliminado = userService.eliminarUsuario(iduser);

    if (eliminado) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/actualizarUsuario/{iduser}")
  public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long iduser, @RequestBody Usuario datosActualizados) {
    Usuario usuarioActualizado = userService.actualizarUsuario(iduser, datosActualizados);
    if (usuarioActualizado != null) {
      return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
