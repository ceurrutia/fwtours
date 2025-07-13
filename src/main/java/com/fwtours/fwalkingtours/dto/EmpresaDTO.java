package com.fwtours.fwalkingtours.dto;


public class EmpresaDTO {
    private Long id;
    private String nombreEmpresa;
    private String cuit;
    private String descripcion;
    private String direccion;
    private String telefono;
    private boolean activa;
    private Long usuarioId;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Long id, String nombreEmpresa, String cuit, String descripcion,
                      String direccion, String telefono, Boolean activa, Long usuarioId) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.cuit = cuit;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activa = activa;
        this.usuarioId = usuarioId;
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
