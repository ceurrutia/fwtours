package com.fwtours.fwalkingtours.dto;

import com.fwtours.fwalkingtours.enums.Rol;

public class UsuarioCreateDTO {

    private String email;
    private String password;
    private Rol rol;
    private String nombreCompleto;
    private String username;

    public UsuarioCreateDTO() {}

    public UsuarioCreateDTO(String email, String password, Rol rol,
                            String nombreCompleto, String username) {
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.username = username;
    }

    // Getters y setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
