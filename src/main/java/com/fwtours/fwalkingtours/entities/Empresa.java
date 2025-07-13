package com.fwtours.fwalkingtours.entities;

import jakarta.persistence.*;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEmpresa;
    private String cuit;
    private String descripcion;
    private String direccion;
    private String telefono;

    @Column(nullable = false)
    private Boolean activa = true;

    @OneToOne
    private Usuario usuario;

    //constructores

    public Empresa() {
    }

    public Empresa(Long id, String nombreEmpresa, String cuit,
                   String descripcion, String direccion,
                   String telefono,Boolean activa, Usuario usuario) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.cuit = cuit;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activa = activa;
        this.usuario = usuario;
    }

    //getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
