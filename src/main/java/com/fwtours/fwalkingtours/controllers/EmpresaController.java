package com.fwtours.fwalkingtours.controllers;

import com.fwtours.fwalkingtours.dto.EmpresaDTO;
import com.fwtours.fwalkingtours.services.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    //get a la lista
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    //PUT /api/empresas/usuario/4
    @PutMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> actualizarEmpresa(@PathVariable Long usuarioId, @RequestBody EmpresaDTO dto) {
        try {
            EmpresaDTO empresaActualizada = empresaService.updateEmpresa(usuarioId, dto);
            return ResponseEntity.ok(empresaActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //obtener por id
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> obtenerEmpresaPorUsuario(@PathVariable Long usuarioId) {
        try {
            EmpresaDTO empresaDTO = empresaService.obtenerEmpresaPorUsuarioId(usuarioId);
            return ResponseEntity.ok(empresaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
