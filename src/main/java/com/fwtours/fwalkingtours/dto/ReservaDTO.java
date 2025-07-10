package com.fwtours.fwalkingtours.dto;

import java.time.LocalDate;

public class ReservaDTO {
    private Long id;
    private Long tourId;
    private Long clienteId;
    private LocalDate fechaReserva;

    //constructores
    public ReservaDTO() {}

    public ReservaDTO(Long id, Long tourId, Long clienteId, LocalDate fechaReserva) {
        this.id = id;
        this.tourId = tourId;
        this.clienteId = clienteId;
        this.fechaReserva = fechaReserva;
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

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
