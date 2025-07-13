package com.fwtours.fwalkingtours.controllers;


import com.fwtours.fwalkingtours.entities.Reserva;
import com.fwtours.fwalkingtours.entities.Usuario;
import com.fwtours.fwalkingtours.services.ReservaService;
import com.fwtours.fwalkingtours.services.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/cliente")
@Controller
public class ClienteWebController {
    private final ReservaService reservaService;
    private final UsuarioService usuarioService;

    public ClienteWebController(ReservaService reservaService, UsuarioService usuarioService) {
        this.reservaService = reservaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/reservas")
    public String verReservasCliente(Model model, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioService.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            return "redirect:/login?error=true";
        }

        Usuario usuario = usuarioOpt.get();
        List<Reserva> reservas = reservaService.obtenerReservasPorCliente(usuario);
        model.addAttribute("reservas", reservas);
        return "cliente/reservas";
    }


    @GetMapping("/perfil")
    public String verPerfilCliente(Model model, Authentication authentication) throws Exception {
        String email = authentication.getName();

        Usuario cliente = usuarioService.findByEmail(email)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        model.addAttribute("cliente", cliente);
        return "cliente/perfil";
    }
}


