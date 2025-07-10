package com.fwtours.fwalkingtours.controllers;

import com.fwtours.fwalkingtours.dto.ReservaDTO;
import com.fwtours.fwalkingtours.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    //Crea reserva
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            ReservaDTO creada = reservaService.crearReserva(reservaDTO);
            return ResponseEntity.ok(creada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtenertodas las reservas
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarTodasLasReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }

    // Obtener reservas por tour
    @GetMapping("/tour/{tourId}")
    public ResponseEntity<?> listarReservasPorTour(@PathVariable Long tourId) {
        try {
            List<ReservaDTO> reservas = reservaService.listarReservasPorTour(tourId);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id) {
        try {
            reservaService.eliminarReserva(id);
            return ResponseEntity.ok("Reserva eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
