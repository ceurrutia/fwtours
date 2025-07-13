package com.fwtours.fwalkingtours.dto;

import com.fwtours.fwalkingtours.entities.Tour;
import com.fwtours.fwalkingtours.enums.Idioma;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TourDTO {

    private Long id;
    private String nombreTour;
    private String descripcion;
    private String nombreEmpresa;
    private String puntoEncuentro;
    private String duracion;
    private LocalDate fecha;
    private Idioma idioma;
    private LocalTime hora;
    private String ciudad;
    private String pais;
    private Double donacionSugerida;
    private Long empresaId;

    public String getIdiomaLabel() {
        return idioma != null ? idioma.getLabel() : "";
    }

    public TourDTO() {}

    public TourDTO(Long id, String nombreTour, String descripcion,
                   String nombreEmpresa, String puntoEncuentro, String duracion,
                   LocalDate fecha, LocalTime hora, Idioma idioma, String ciudad, String pais,
                   Double donacionSugerida, Long empresaId) {
        this.id = id;
        this.nombreTour = nombreTour;
        this.descripcion = descripcion;
        this.nombreEmpresa = nombreEmpresa;
        this.puntoEncuentro = puntoEncuentro;
        this.duracion = duracion;
        this.fecha = fecha;
        this.idioma = idioma;
        this.hora = hora;
        this.ciudad = ciudad;
        this.pais = pais;
        this.donacionSugerida = donacionSugerida;
        this.empresaId = empresaId;
    }

// Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreTour() { return nombreTour; }
    public void setNombreTour(String nombreTour) { this.nombreTour = nombreTour; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getPuntoEncuentro() { return puntoEncuentro; }
    public void setPuntoEncuentro(String puntoEncuentro) { this.puntoEncuentro = puntoEncuentro; }

    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public Double getDonacionSugerida() { return donacionSugerida; }
    public void setDonacionSugerida(Double donacionSugerida) { this.donacionSugerida = donacionSugerida; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }

    // Método para mapear entity a DTO
    public static TourDTO fromEntity(Tour tour) {
        String nombreEmpresa = null;
        Long empresaId = null;

        if (tour.getEmpresa() != null) {
            nombreEmpresa = tour.getEmpresa().getNombreEmpresa();
            empresaId = tour.getEmpresa().getId();
        }

        return new TourDTO(
                tour.getId(),
                tour.getNombreTour(),
                tour.getDescripcion(),
                nombreEmpresa,
                tour.getPuntoEncuentro(),
                tour.getDuracion(),
                tour.getFecha(),
                tour.getHora(),
                tour.getIdioma(),
                tour.getCiudad(),
                tour.getPais(),
                tour.getDonacionSugerida(),
                empresaId
        );
    }
}
