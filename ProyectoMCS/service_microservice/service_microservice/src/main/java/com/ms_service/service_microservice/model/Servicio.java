package com.ms_service.service_microservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Float precio;
    private String ubicacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @JsonIgnore
    private Categoria categoria;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    @JsonIgnore
    private Proveedor proveedor;*/

    public Servicio(){

    }

    public Servicio(String nombre, String descripcion, Float precio, String ubicacion, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}


