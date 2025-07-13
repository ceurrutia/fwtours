package com.fwtours.fwalkingtours.controllers;

import com.fwtours.fwalkingtours.dto.ReservaDTO;
import com.fwtours.fwalkingtours.dto.TourDTO;
import com.fwtours.fwalkingtours.entities.Empresa;
import com.fwtours.fwalkingtours.entities.Usuario;
import com.fwtours.fwalkingtours.enums.Idioma;
import com.fwtours.fwalkingtours.services.EmpresaService;
import com.fwtours.fwalkingtours.services.ReservaService;
import com.fwtours.fwalkingtours.services.TourService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empresa")
public class EmpresaWebController {
    private final EmpresaService empresaService;
    private final ReservaService reservaService;
    private final TourService tourService;

    public EmpresaWebController(EmpresaService empresaService, ReservaService reservaService, TourService tourService) {
        this.empresaService = empresaService;
        this.reservaService = reservaService;
        this.tourService = tourService;
    }

    @GetMapping("/perfil")
    public String verPerfilEmpresa(Model model) {
        String emailUsuario = empresaService.getEmailUsuarioLogueado();
        Optional<Empresa> empresaOpt = empresaService.findByEmailUsuario(emailUsuario);

        if (empresaOpt.isEmpty()) {
            return "redirect:/error";
        }

        Empresa empresa = empresaOpt.get();
        model.addAttribute("empresa", empresa);
        model.addAttribute("usuarioId", empresa.getUsuario().getId());

        return "empresa/perfil";
    }

    @GetMapping("/reservas")
    public String verReservasEmpresa(Model model) {
        String emailUsuario = empresaService.getEmailUsuarioLogueado();
        List<ReservaDTO> reservas = reservaService.obtenerReservasDeEmpresa(emailUsuario);

        model.addAttribute("reservas", reservas);
        return "empresa/reservas"; // templates/empresa/reservas.html
    }

    @GetMapping("/tours")
    @PreAuthorize("hasRole('EMPRESA')")
    public String verToursEmpresa(Model model, @AuthenticationPrincipal Usuario empresa) throws Exception {
        Long empresaId = empresa.getId();
        model.addAttribute("empresaId", empresaId);
        model.addAttribute("tours", tourService.listarToursPorEmpresa(empresaId));
        return "empresa/tours";
    }

    // Vista privada para empresas logueadas
    @GetMapping("/mis-tours")
    @PreAuthorize("hasRole('EMPRESA')")
    public String verMisTours(Model model) {
        String email = empresaService.getEmailUsuarioLogueado();
        var empresaOpt = empresaService.findByEmailUsuario(email);

        if (empresaOpt.isEmpty()) {
            return "redirect:/error";
        }

        Long empresaId = empresaOpt.get().getId();

        try {
            List<TourDTO> tours = tourService.listarToursPorEmpresa(empresaId);
            model.addAttribute("tours", tours);
            model.addAttribute("empresaId", empresaId);
            model.addAttribute("idiomas", Idioma.values());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener los tours: " + e.getMessage());
        }

        return "empresa/mis-tours";
    }
}
