package com.fwtours.fwalkingtours.services;

import com.fwtours.fwalkingtours.dto.EmpresaDTO;
import com.fwtours.fwalkingtours.entities.Empresa;
import com.fwtours.fwalkingtours.repositories.EmpresaRepository;
import com.fwtours.fwalkingtours.repositories.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    public EmpresaService(EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository) {
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //Listar empresas
    public List<EmpresaDTO> listarEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //buscar por email de usuario
    public Optional<Empresa> findByEmailUsuario(String email) {
        return empresaRepository.findAll().stream()
                .filter(e -> e.getUsuario().getEmail().equals(email))
                .findFirst();
    }

    //buscar por empresa
    public Optional<Empresa> findByUsuarioId(Long usuarioId) {
        return empresaRepository.findAll().stream()
                .filter(e -> e.getUsuario().getId().equals(usuarioId))
                .findFirst();
    }

    //obtiene por ID
    public EmpresaDTO obtenerEmpresaPorUsuarioId(Long usuarioId) throws Exception {
        Empresa empresa = findByUsuarioId(usuarioId)
                .orElseThrow(() -> new Exception("Empresa no encontrada para el usuario " + usuarioId));
        return mapToDTO(empresa);
    }

    //actualiza
    public EmpresaDTO updateEmpresa(Long usuarioId, EmpresaDTO dto) throws Exception {
        Empresa empresa = findByUsuarioId(usuarioId)
                .orElseThrow(() -> new Exception("Empresa no encontrada para el usuario " + usuarioId));

        if (dto.getNombreEmpresa() != null) empresa.setNombreEmpresa(dto.getNombreEmpresa());
        if (dto.getCuit() != null) empresa.setCuit(dto.getCuit());
        if (dto.getDescripcion() != null) empresa.setDescripcion(dto.getDescripcion());
        if (dto.getDireccion() != null) empresa.setDireccion(dto.getDireccion());
        if (dto.getTelefono() != null) empresa.setTelefono(dto.getTelefono());

        empresaRepository.save(empresa);
        return mapToDTO(empresa);
    }

    public EmpresaDTO mapToDTO(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getId(),
                empresa.getNombreEmpresa(),
                empresa.getCuit(),
                empresa.getDescripcion(),
                empresa.getDireccion(),
                empresa.getTelefono(),
                empresa.getActiva(),
                empresa.getUsuario().getId()
        );
    }

    // valida si el usuario es duenio de la empresa
    public boolean esDueñoDeEmpresa(Long empresaId, String emailUsuario) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaId);
        return empresaOpt.isPresent() &&
                empresaOpt.get().getUsuario().getEmail().equals(emailUsuario);
    }

    //obtener el email del usuario logueado desde el contexto de seguridad
    public String getEmailUsuarioLogueado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }
}
