package com.fwtours.fwalkingtours.entities;

import com.fwtours.fwalkingtours.enums.Rol;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    // Cliente o empresa
    private String nombreCompleto;
    private String username;

    //constructores

    public Usuario() {
    }

    public Usuario(Long id, String email, String password, Rol rol,
                   String nombreCompleto, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.username = username;
    }

    //getters y seters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
