package com.fwtours.fwalkingtours.controllers;

import com.fwtours.fwalkingtours.dto.EmpresaDTO;
import com.fwtours.fwalkingtours.dto.UsuarioCreateDTO;
import com.fwtours.fwalkingtours.dto.UsuarioDTO;
import com.fwtours.fwalkingtours.dto.UsuarioLoginDTO;
import com.fwtours.fwalkingtours.entities.Usuario;
import com.fwtours.fwalkingtours.enums.Rol;
import com.fwtours.fwalkingtours.services.UsuarioService;
import com.fwtours.fwalkingtours.utils.JwtAuthFilter;
import com.fwtours.fwalkingtours.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    //get a la lista de usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    //get a usuario por iD
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.findUsuarioDTOById(id);
            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioCreateDTO dto) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.actualizarUsuario(id, dto);
            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //buscar por mail
    @GetMapping("/email/{email}")
    public ResponseEntity<?> obtenerUsuarioPorEmail(@PathVariable String email) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.findUsuarioDTOByEmail(email);
            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Registro de usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioCreateDTO createDTO) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.crearUsuario(createDTO);
            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO loginDTO) {
        var usuarioOpt = usuarioService.findByEmail(loginDTO.getEmail());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Email o contraseña incorrectos");
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).body("Email o contraseña incorrectos");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());

        return ResponseEntity.ok(new JwtResponse(token));
    }


    public static class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }


    //delete de usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //lista usuarios por rol
    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosPorRol(@PathVariable Rol rol) {
        return ResponseEntity.ok(usuarioService.listarUsuariosPorRol(rol));
    }
}