package com.fwtours.fwalkingtours.controllers;

import com.fwtours.fwalkingtours.dto.UsuarioDTO;
import com.fwtours.fwalkingtours.entities.Reserva;
import com.fwtours.fwalkingtours.entities.Usuario;
import com.fwtours.fwalkingtours.services.EmpresaService;
import com.fwtours.fwalkingtours.services.ReservaService;
import com.fwtours.fwalkingtours.services.TourService;
import com.fwtours.fwalkingtours.services.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UsuarioService usuarioService;
    private final EmpresaService empresaService;
    private final TourService tourService;
    private final ReservaService reservaService;

    public AdminController(UsuarioService usuarioService,
                           EmpresaService empresaService,
                           TourService tourService,
                           ReservaService reservaService) {
        this.usuarioService = usuarioService;
        this.empresaService = empresaService;
        this.tourService = tourService;
        this.reservaService = reservaService;
    }


    @GetMapping("/admin/reservas")
    public String verReservasCliente(Model model, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioService.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            return "redirect:/login?error=true";
        }

        Usuario usuario = usuarioOpt.get();
        List<Reserva> reservas = reservaService.obtenerReservasPorCliente(usuario);
        model.addAttribute("reservas", reservas);
        return "admin/reservas";
    }


    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        System.out.println("Usuarios encontrados: " + usuarios.size());
        model.addAttribute("usuarios", usuarios);
        return "admin/usuarios";
    }

    @GetMapping("/empresas")
    public String verEmpresas(Model model) {
        model.addAttribute("empresas", empresaService.listarEmpresas());
        return "admin/empresas";
    }

    @GetMapping("/tours")
    public String verTours(Model model) {
        model.addAttribute("tours", tourService.listarTodos());
        return "admin/tours";
    }

    @GetMapping("/reservas")
    public String verReservas(Model model) {
        model.addAttribute("reservas", reservaService.listarReservas());
        return "admin/reservas";
    }

}
