package com.fwtours.fwalkingtours.entities;

import com.fwtours.fwalkingtours.enums.Idioma;
import com.fwtours.fwalkingtours.enums.Rol;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private String ciudad;
    private String pais;
    private Double donacionSugerida;



    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;

    //constructores


    public Tour() {
    }

    public Tour(Long id, String nombreTour,
                String descripcion, String puntoEncuentro, String duracion,
                LocalDate fecha, LocalTime hora, Idioma idioma, String ciudad, String pais, Double donacionSugerida,
                Empresa empresa) {
        this.id = id;
        this.nombreTour = nombreTour;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.puntoEncuentro = puntoEncuentro;
        this.donacionSugerida = donacionSugerida;
        this.fecha = fecha;
        this.hora = hora;
        this.idioma = idioma;
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
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
