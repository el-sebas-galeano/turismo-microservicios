package com.ms_service.service_microservice.repository;

import com.ms_service.service_microservice.model.Categoria;
import com.ms_service.service_microservice.model.Servicio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    
    List<Servicio> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion);

    List<Servicio> findByCategoria(Categoria categoria);
}

