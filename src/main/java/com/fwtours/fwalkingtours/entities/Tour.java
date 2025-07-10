package com.fwtours.fwalkingtours.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreTour;
    private String descripcion;
    private String puntoEncuentro;
    private String duracion;
    private LocalDate fecha;
    private String ciudad;
    private String pais;
    private Double donacionSugerida;

    @ManyToOne
    private Empresa empresa;

    //constructores


    public Tour() {
    }

    public Tour(Long id, String nombreTour,
                String descripcion, String puntoEncuentro, String duracion,
                LocalDate fecha, String ciudad, String pais, Double donacionSugerida,
                Empresa empresa) {
        this.id = id;
        this.nombreTour = nombreTour;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.puntoEncuentro = puntoEncuentro;
        this.donacionSugerida = donacionSugerida;
        this.fecha = fecha;
        this.ciudad = ciudad;
        this.pais = pais;
        this.empresa = empresa;
    }

    //getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTour() {
        return nombreTour;
    }

    public void setNombreTour(String nombreTour) {
        this.nombreTour = nombreTour;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPuntoEncuentro() {
        return puntoEncuentro;
    }

    public void setPuntoEncuentro(String puntoEncuentro) {
        this.puntoEncuentro = puntoEncuentro;
    }

    public Double getDonacionSugerida() {
        return donacionSugerida;
    }

    public void setDonacionSugerida(Double donacionSugerida) {
        this.donacionSugerida = donacionSugerida;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
