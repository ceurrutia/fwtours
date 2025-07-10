package com.fwtours.fwalkingtours.enums;

public enum Rol {
    ADMIN("Administrador"),
    CLIENTE("Cliente"),
    EMPRESA("Empresa");

    private final String descripcion;

    Rol(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
