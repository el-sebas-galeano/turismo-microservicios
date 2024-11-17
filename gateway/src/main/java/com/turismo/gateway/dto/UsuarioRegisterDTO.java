package com.turismo.gateway.dto;

import java.util.List;

public class UsuarioRegisterDTO {
    private Long idUsuario;
    private String nombre;
    private int edad;
    private String descripcion;
    private String telefono;
    private String web;
    private List<String> redesSociales;
    private byte[] foto;
    private CredencialDTO credencial;
    
    public UsuarioRegisterDTO() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public List<String> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(List<String> redesSociales) {
        this.redesSociales = redesSociales;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public CredencialDTO getCredencial() {
        return credencial;
    }

    public void setCredencial(CredencialDTO credencial) {
        this.credencial = credencial;
    }


}
