package com.ms_service.service_microservice.init;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.ms_service.service_microservice.model.*;
import com.ms_service.service_microservice.repository.*;

public class DbInitializer implements CommandLineRunner {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public void run(String... args) throws Exception {
        generarServicios(10);
    }

    private void generarServicios(int numero) {

        List<String> nombres = List.of("Servicio A", "Servicio B", "Servicio C", "Servicio D", "Servicio E",
                "Servicio F", "Servicio G", "Servicio H", "Servicio I", "Servicio J");

        List<String> descripciones = List.of("Este es el servicio A", "Este es el servicio B",
                "Descripción del servicio C",
                "Servicio D para clientes", "Servicio E de calidad", "Excelente servicio F",
                "Servicio G especializado", "Servicio H innovador", "Servicio I confiable", "Servicio J rápido");

        List<String> ubicaciones = List.of("Bogotá", "Medellín", "Cali", "Cartagena", "Barranquilla",
                "Pereira", "Bucaramanga", "Manizales", "Santa Marta", "Cúcuta");

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String nombre = nombres.get(random.nextInt(nombres.size()));
            String descripcion = descripciones.get(random.nextInt(descripciones.size()));
            Float precio = Math.round(random.nextFloat() * (100 - 10) + 10) * 1.0f;
            String ubicacion = ubicaciones.get(random.nextInt(ubicaciones.size()));
            LocalDateTime fechaInicio = LocalDateTime.now().minusDays(random.nextInt(30) + 1);
            LocalDateTime fechaFin = LocalDateTime.now().plusDays(random.nextInt(30) + 1);

            Servicio servicio = new Servicio(nombre, descripcion, precio, ubicacion, fechaInicio, fechaFin);

            servicioRepository.save(servicio);
        }
    }
}
