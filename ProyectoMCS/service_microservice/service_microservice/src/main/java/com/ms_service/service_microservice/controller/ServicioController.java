package com.ms_service.service_microservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms_service.service_microservice.model.Servicio;
import com.ms_service.service_microservice.service.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioController {
    @Autowired
    private ServicioService servicioService;

    @GetMapping("/listarServicios")
    public ResponseEntity<List<Servicio>> obtenerServicios() {
        List<Servicio> servicios = servicioService.obtenerTodosLosServicios();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @PostMapping("/crearServicio")
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        Servicio nuevoServicio = servicioService.crearServicio(servicio);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarServicio/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        boolean eliminado = servicioService.eliminarServicio(id);

        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/modificarServicio/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio datosActualizados) {
        Servicio servicioActualizado = servicioService.actualizarServicio(id, datosActualizados);
        return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Servicio>> buscarServicios(@RequestParam("keyword") String keyword) {
        List<Servicio> servicios = servicioService.buscarPorPalabrasClave(keyword);

        if (!servicios.isEmpty()) {
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clasificacion")
    public ResponseEntity<Map<String, List<Servicio>>> clasificarServiciosPorCategoria() {
        Map<String, List<Servicio>> serviciosPorCategoria = servicioService.clasificarServiciosPorCategoria();
        return ResponseEntity.ok(serviciosPorCategoria);
    }
}
