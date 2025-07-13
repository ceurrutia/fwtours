package com.fwtours.fwalkingtours.services;

import com.fwtours.fwalkingtours.dto.EmpresaDTO;
import com.fwtours.fwalkingtours.dto.UsuarioCreateDTO;
import com.fwtours.fwalkingtours.dto.UsuarioDTO;
import com.fwtours.fwalkingtours.entities.Empresa;
import com.fwtours.fwalkingtours.entities.Usuario;
import com.fwtours.fwalkingtours.enums.Rol;
import com.fwtours.fwalkingtours.repositories.EmpresaRepository;
import com.fwtours.fwalkingtours.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmpresaRepository empresaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //buscar por email
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Registro de usuario
    public UsuarioDTO crearUsuario(UsuarioCreateDTO createDTO) throws Exception {
        if (usuarioRepository.existsByEmail(createDTO.getEmail())) {
            throw new Exception("Email ya registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(createDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(createDTO.getPassword()));
        usuario.setRol(createDTO.getRol());
        usuario.setNombreCompleto(createDTO.getNombreCompleto());
        usuario.setUsername(createDTO.getUsername());

        usuario = usuarioRepository.save(usuario);

        //crea entidad Empresa
        if (usuario.getRol() == Rol.EMPRESA) {
            Empresa empresa = new Empresa();
            empresa.setUsuario(usuario);
            empresa.setNombreEmpresa(usuario.getNombreCompleto());
            empresa.setDescripcion("Descripción pendiente");
            empresa.setDireccion("Dirección pendiente");
            empresa.setTelefono("Teléfono pendiente");

            empresaRepository.save(empresa);
        }

        return mapToDTO(usuario);
    }

    //buscar por id
    public UsuarioDTO findUsuarioDTOById(Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id " + id));
        return mapToDTO(usuario);
    }

    public UsuarioDTO findUsuarioDTOByEmail(String email) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuario no encontrado con email: " + email));
        return mapToDTO(usuario);
    }

    //mapeo de entidad a DTO
    public UsuarioDTO mapToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getNombreCompleto(),
                usuario.getUsername()
        );
    }

    //eliminar usuario
    public void eliminarUsuario(Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id " + id));

        // Si el usuario es una empresa, se elimina la empresa tabien en cascada
        if (usuario.getRol() == Rol.EMPRESA) {
            empresaRepository.findAll().stream()
                    .filter(e -> e.getUsuario().getId().equals(id))
                    .findFirst()
                    .ifPresent(empresaRepository::delete);
        }

        usuarioRepository.delete(usuario);
    }


    //listar usuarios por rol
    public List<UsuarioDTO> listarUsuariosPorRol(Rol rol) {
        List<Usuario> usuarios = usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getRol().equals(rol))
                .collect(Collectors.toList());

        return usuarios.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    //Aactualizar usuario
    public UsuarioDTO actualizarUsuario(Long id, UsuarioCreateDTO dto) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id " + id));

        // Actualiza nombreCompleto directamente
        usuario.setNombreCompleto(dto.getNombreCompleto());

        usuario.setEmail(dto.getEmail());
        usuario.setUsername(dto.getUsername());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        // Solo actualiza rol si dto trae rol no nulo, sino conserva el actual
        if (dto.getRol() != null) {
            usuario.setRol(dto.getRol());
        }
        Usuario guardado = usuarioRepository.save(usuario);
        return mapToDTO(guardado);
    }
}
