package com.fwtours.fwalkingtours.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedireccionLogin {
    @GetMapping("/redireccion")
    public String redirigirPorRol(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities() != null) {
            String rol = authentication.getAuthorities().iterator().next().getAuthority();

            switch (rol) {
                case "ROLE_ADMIN":
                    return "redirect:/admin/usuarios";
                case "ROLE_EMPRESA":
                    return "redirect:/empresa.html";
                case "ROLE_CLIENTE":
                    return "redirect:/cliente/reservas.html";
                default:
                    return "redirect:/login?error=true";
            }
        }
        return "redirect:/login?error=true";
    }
}
