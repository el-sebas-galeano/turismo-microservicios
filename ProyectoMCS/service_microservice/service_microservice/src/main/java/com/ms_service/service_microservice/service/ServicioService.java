package com.ms_service.service_microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms_service.service_microservice.model.Servicio;
import com.ms_service.service_microservice.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> obtenerTodosLosServicios() {
        return servicioRepository.findAll();
    }

    public Servicio crearServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public boolean eliminarServicio(Long id) {
        Optional<Servicio> servicio = servicioRepository.findById(id);
        if (servicio.isPresent()) {
            servicioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Servicio actualizarServicio(Long id, Servicio datosActualizados) {

        Servicio servicioExistente = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));

        if (datosActualizados.getNombre() != null) {
            servicioExistente.setNombre(datosActualizados.getNombre());
        }
        if (datosActualizados.getDescripcion() != null) {
            servicioExistente.setDescripcion(datosActualizados.getDescripcion());
        }
        if (datosActualizados.getPrecio() != null) {
            servicioExistente.setPrecio(datosActualizados.getPrecio());
        }
        if (datosActualizados.getUbicacion() != null) {
            servicioExistente.setUbicacion(datosActualizados.getUbicacion());
        }
        if (datosActualizados.getFechaInicio() != null) {
            servicioExistente.setFechaInicio(datosActualizados.getFechaInicio());
        }
        if (datosActualizados.getFechaFin() != null) {
            servicioExistente.setFechaFin(datosActualizados.getFechaFin());
        }

        return servicioRepository.save(servicioExistente);
    }
}