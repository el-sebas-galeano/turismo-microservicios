package com.example.user_microservice.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.user_microservice.model.Usuario;
import com.example.user_microservice.repository.UserRepository;

import java.util.List;
import java.util.Random;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        generarUsuarios(10);
    }

    private void generarUsuarios(int numero) {
        List<String> nombres = List.of("Juan", "Ana", "Pedro", "Luisa", "Carlos", "Marta", "Sofía", "Miguel", "Camila", "Jorge");
        List<String> descripciones = List.of("Usuario enfocado en compras", "Proveedor especializado en productos", 
                                             "Comprador frecuente", "Vendedor de servicios", "Nuevo usuario en la plataforma",
                                             "Proveedor con experiencia", "Cliente leal", "Usuario innovador", 
                                             "Proveedor confiable", "Cliente ocasional");
        List<String> emails = List.of("juan@mail.com", "ana@mail.com", "pedro@mail.com", "luisa@mail.com", "carlos@mail.com", 
                                      "marta@mail.com", "sofia@mail.com", "miguel@mail.com", "camila@mail.com", "jorge@mail.com");
        List<String> fotos = List.of("foto1.jpg", "foto2.jpg", "foto3.jpg", "foto4.jpg", "foto5.jpg", "foto6.jpg", 
                                     "foto7.jpg", "foto8.jpg", "foto9.jpg", "foto10.jpg");
        List<String> roles = List.of("Cliente", "Proveedor");

        Random random = new Random();

        for (int i = 0; i < numero; i++) {
            String nombre = nombres.get(random.nextInt(nombres.size()));
            int edad = random.nextInt(50) + 18; 
            String descripcion = descripciones.get(random.nextInt(descripciones.size()));
            String email = emails.get(i);
            String foto = fotos.get(random.nextInt(fotos.size()));
            String password = "password" + i;
            String rol = roles.get(random.nextInt(roles.size()));

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEdad(edad);
            usuario.setDescripcion(descripcion);
            usuario.setEmail(email);
            usuario.setFoto(foto);
            usuario.setPassword(password);
            usuario.setRol(rol);

            userRepository.save(usuario);
        }
    }
}
