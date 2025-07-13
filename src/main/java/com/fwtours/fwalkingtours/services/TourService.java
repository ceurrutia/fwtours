package com.fwtours.fwalkingtours.services;

import com.fwtours.fwalkingtours.dto.TourDTO;
import com.fwtours.fwalkingtours.entities.Empresa;
import com.fwtours.fwalkingtours.entities.Tour;
import com.fwtours.fwalkingtours.repositories.EmpresaRepository;
import com.fwtours.fwalkingtours.repositories.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {
    private final TourRepository tourRepository;
    private final EmpresaRepository empresaRepository;

    public TourService(TourRepository tourRepository, EmpresaRepository empresaRepository) {
        this.tourRepository = tourRepository;
        this.empresaRepository = empresaRepository;
    }

    // Crear un tour nuevo para una empresa
    public TourDTO crearTour(Long empresaId, TourDTO dto) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new Exception("Empresa no encontrada"));

        Tour tour = new Tour();
        tour.setNombreTour(dto.getNombreTour());
        tour.setDescripcion(dto.getDescripcion());
        tour.setPuntoEncuentro(dto.getPuntoEncuentro());
        tour.setDuracion(dto.getDuracion());
        tour.setFecha(dto.getFecha());
        tour.setHora(dto.getHora());
        tour.setIdioma(dto.getIdioma());
        tour.setCiudad(dto.getCiudad());
        tour.setPais(dto.getPais());
        tour.setDonacionSugerida(dto.getDonacionSugerida());
        tour.setEmpresa(empresa);

        Tour guardado = tourRepository.save(tour);
        return TourDTO.fromEntity(guardado);
    }

    // Listar tours de una empresa
    public List<TourDTO> listarToursPorEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new Exception("Empresa no encontrada"));

        List<Tour> tours = tourRepository.findByEmpresa(empresa);
        return tours.stream().map(TourDTO::fromEntity).collect(Collectors.toList());
    }

    // Actualizar un tour (solo si pertenece a la empresa)
    public TourDTO actualizarTour(Long empresaId, Long tourId, TourDTO dto) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new Exception("Empresa no encontrada"));

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new Exception("Tour no encontrado"));

        if (!tour.getEmpresa().getId().equals(empresa.getId())) {
            throw new Exception("No autorizado para modificar este tour");
        }

        if (dto.getNombreTour() != null) tour.setNombreTour(dto.getNombreTour());
        if (dto.getDescripcion() != null) tour.setDescripcion(dto.getDescripcion());
        if (dto.getPuntoEncuentro() != null) tour.setPuntoEncuentro(dto.getPuntoEncuentro());
        if (dto.getDuracion() != null) tour.setDuracion(dto.getDuracion());
        if (dto.getFecha() != null) tour.setFecha(dto.getFecha());
        if (dto.getHora() != null) tour.setHora(dto.getHora());
        if (dto.getIdioma() != null) tour.setIdioma(dto.getIdioma());
        if (dto.getCiudad() != null) tour.setCiudad(dto.getCiudad());
        if (dto.getPais() != null) tour.setPais(dto.getPais());
        if (dto.getDonacionSugerida() != null) tour.setDonacionSugerida(dto.getDonacionSugerida());

        Tour actualizado = tourRepository.save(tour);
        return TourDTO.fromEntity(actualizado);
    }

    // Eliminar un tour (solo si pertenece a la empresa)
    public void eliminarTour(Long empresaId, Long tourId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new Exception("Empresa no encontrada"));

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new Exception("Tour no encontrado"));

        if (!tour.getEmpresa().getId().equals(empresa.getId())) {
            throw new Exception("No autorizado para eliminar este tour");
        }

        tourRepository.delete(tour);
    }

    // Listar todos los tours que haya
    public List<TourDTO> listarTodos() {
        List<Tour> tours = tourRepository.findAll();
        return tours.stream().map(TourDTO::fromEntity).collect(Collectors.toList());
    }
}
