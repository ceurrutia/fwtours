package com.fwtours.fwalkingtours.controllers;

import com.fwtours.fwalkingtours.dto.TourDTO;
import com.fwtours.fwalkingtours.services.EmpresaService;
import com.fwtours.fwalkingtours.services.TourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourWebController {
    private final TourService tourService;
    private final EmpresaService empresaService;

    public TourWebController(TourService tourService, EmpresaService empresaService) {
        this.tourService = tourService;
        this.empresaService = empresaService;
    }

    // Vista pública para cualquier usuario - TODOS los tours
    @GetMapping("/tours")
    public String verTodosLosTours(Model model) {
        List<TourDTO> tours = tourService.listarTodos();
        model.addAttribute("tours", tours);
        return "public/tours";
    }


}
