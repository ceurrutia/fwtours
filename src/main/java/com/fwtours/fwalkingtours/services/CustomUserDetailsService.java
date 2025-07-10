package com.fwtours.fwalkingtours.services;

import com.fwtours.fwalkingtours.entities.Usuario;
import com.fwtours.fwalkingtours.repositories.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        //roles para Spring Security
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name());

        return new User(usuario.getEmail(), usuario.getPassword(),
                Collections.singletonList(authority));
    }
}
