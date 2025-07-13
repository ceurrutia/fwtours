package com.fwtours.fwalkingtours.dto;

import com.fwtours.fwalkingtours.entities.Reserva;
import com.fwtours.fwalkingtours.enums.Idioma;

import java.time.LocalDate;

public class ReservaDTO {
    private Long id;
    private Long tourId;
    private Long clienteId;
    private String emailCliente;
    private String nombreTour;
    private LocalDate fechaReserva;
    private String nombreEmpresa;
    private Idioma idioma;

    public String getIdiomaLabel() {
        return idioma != null ? idioma.getLabel() : "";
    }

    //constructores
    public ReservaDTO() {}

    public static ReservaDTO fromEntity(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFechaReserva(reserva.getFechaReserva());

        if (reserva.getTour() != null) {
            dto.setNombreTour(reserva.getTour().getNombreTour());
            dto.setIdioma(reserva.getTour().getIdioma());
            if (reserva.getTour().getEmpresa() != null) {
                dto.setNombreEmpresa(reserva.getTour().getEmpresa().getNombreEmpresa());
            }
        }

        if (reserva.getCliente() != null) {
            dto.setClienteId(reserva.getCliente().getId());
            dto.setEmailCliente(reserva.getCliente().getEmail());
        }


        return dto;
    }

    //getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getEmailCliente() {
        return emailCliente;
    }
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }


    public String getNombreTour() {
        return nombreTour;
    }

    public void setNombreTour(String nombreTour) {
        this.nombreTour = nombreTour;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }
}
