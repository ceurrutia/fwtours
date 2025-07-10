package com.fwtours.fwalkingtours.controllers;

import com.fwtours.fwalkingtours.dto.TourDTO;
import com.fwtours.fwalkingtours.services.TourService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas/{empresaId}/tours")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    // Crear un tour para la empresa
    @PostMapping
    @PreAuthorize("@empresaService.esDueñoDeEmpresa(#empresaId, authentication.name)")
    public ResponseEntity<?> crearTour(@PathVariable Long empresaId, @RequestBody TourDTO tourDTO) {
        try {
            TourDTO creado = tourService.crearTour(empresaId, tourDTO);
            return ResponseEntity.ok(creado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Listar tours de una empresa
    @GetMapping
    public ResponseEntity<?> listarTours(@PathVariable Long empresaId) {
        try {
            List<TourDTO> tours = tourService.listarToursPorEmpresa(empresaId);
            return ResponseEntity.ok(tours);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Actualizar un tour
    @PutMapping("/{tourId}")
    @PreAuthorize("@empresaService.esDueñoDeEmpresa(#empresaId, authentication.name)")
    public ResponseEntity<?> actualizarTour(@PathVariable Long empresaId,
                                            @PathVariable Long tourId,
                                            @RequestBody TourDTO tourDTO) {
        try {
            TourDTO actualizado = tourService.actualizarTour(empresaId, tourId, tourDTO);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Eliminar un tour
    @DeleteMapping("/{tourId}")
    @PreAuthorize("@empresaService.esDueñoDeEmpresa(#empresaId, authentication.name)")
    public ResponseEntity<?> eliminarTour(@PathVariable Long empresaId,
                                          @PathVariable Long tourId) {
        try {
            tourService.eliminarTour(empresaId, tourId);
            return ResponseEntity.ok("Tour eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
