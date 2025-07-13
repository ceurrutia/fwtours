package com.fwtours.fwalkingtours.dto;


import com.fwtours.fwalkingtours.enums.Rol;

public class UsuarioDTO {

    private Long id;
    private String email;
    private String telefono;
    private Rol rol;
    private String nombreCompleto;
    private String username;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String email, Rol rol, String nombreCompleto, String username) {
        this.id = id;
        this.email = email;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.username = username;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
