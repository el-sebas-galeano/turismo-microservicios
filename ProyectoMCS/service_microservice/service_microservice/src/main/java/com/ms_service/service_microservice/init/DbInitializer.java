package com.ms_service.service_microservice.init;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ms_service.service_microservice.model.*;
import com.ms_service.service_microservice.repository.*;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        generarCategorias();
        generarServicios(10);
    }

    private void generarCategorias() {

        Categoria categoria1 = new Categoria("Alojamiento", "Lugares donde hospedarse durante el viaje");
        Categoria categoria2 = new Categoria("Alimentación", "Servicios relacionados con la comida y bebidas");
        Categoria categoria3 = new Categoria("Transporte", "Medios de transporte para desplazamientos");
        Categoria categoria4 = new Categoria("Paseos Ecológicos","Exploración de entornos naturales y paseos al aire libre");
        Categoria categoria5 = new Categoria("Fiesta", "Eventos y celebraciones sociales");

        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);
        categoriaRepository.save(categoria3);
        categoriaRepository.save(categoria4);
        categoriaRepository.save(categoria5);
    }

private void generarServicios(int numero) {

        List<String> nombres = List.of("Servicio A", "Servicio B", "Servicio C", "Servicio D", "Servicio E",
                "Servicio F", "Servicio G", "Servicio H", "Servicio I", "Servicio J");
    
        List<String> descripciones = List.of("Este es el servicio A", "Este es el servicio B", "Descripción del servicio C",
                "Servicio D para clientes", "Servicio E de calidad", "Excelente servicio F",
                "Servicio G especializado", "Servicio H innovador", "Servicio I confiable", "Servicio J rápido");
    
        List<String> ubicaciones = List.of("Bogotá", "Medellín", "Cali", "Cartagena", "Barranquilla",
                "Pereira", "Bucaramanga", "Manizales", "Santa Marta", "Cúcuta");
    
       List<Categoria> categorias = categoriaRepository.findAll();
    
        Random random = new Random();
    
        for (int i = 0; i < numero; i++) {
            String nombre = nombres.get(random.nextInt(nombres.size()));
            String descripcion = descripciones.get(random.nextInt(descripciones.size()));
            Float precio = Math.round(random.nextFloat() * (100 - 10) + 10) * 1.0f;
            String ubicacion = ubicaciones.get(random.nextInt(ubicaciones.size()));
            LocalDateTime fechaInicio = LocalDateTime.now().minusDays(random.nextInt(30) + 1);
            LocalDateTime fechaFin = LocalDateTime.now().plusDays(random.nextInt(30) + 1);
    
            Servicio servicio = new Servicio(nombre, descripcion, precio, ubicacion, fechaInicio, fechaFin);
    
            Categoria categoria = categorias.get(random.nextInt(categorias.size()));
            servicio.setCategoria(categoria);
    
            servicioRepository.save(servicio);
        }
    }
    
}
