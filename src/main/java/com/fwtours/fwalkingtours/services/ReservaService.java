package com.fwtours.fwalkingtours.services;

import com.fwtours.fwalkingtours.dto.ReservaDTO;
import com.fwtours.fwalkingtours.entities.Reserva;
import com.fwtours.fwalkingtours.entities.Tour;
import com.fwtours.fwalkingtours.entities.Usuario;
import com.fwtours.fwalkingtours.repositories.ReservaRepository;
import com.fwtours.fwalkingtours.repositories.TourRepository;
import com.fwtours.fwalkingtours.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final TourRepository tourRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository,
                          TourRepository tourRepository,
                          UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.tourRepository = tourRepository;
        this.usuarioRepository = usuarioRepository;
    }


    //crea una nueva reserva
    public ReservaDTO crearReserva(ReservaDTO dto) throws Exception {
        Tour tour = tourRepository.findById(dto.getTourId())
                .orElseThrow(() -> new Exception("Tour no encontrado con ID: " + dto.getTourId()));
        Usuario cliente = usuarioRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new Exception("Cliente no encontrado con ID: " + dto.getClienteId()));

        Reserva reserva = new Reserva();
        reserva.setTour(tour);
        reserva.setCliente(cliente);
        reserva.setFechaReserva(dto.getFechaReserva());

        Reserva guardada = reservaRepository.save(reserva);
        return mapToDTO(guardada);
    }

    //listar reservas por ID de cliente
    public List<ReservaDTO> listarReservasPorCliente(Long clienteId) {
        return reservaRepository.findAll().stream()
                .filter(r -> r.getCliente().getId().equals(clienteId))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //listar reservas por tour
    public List<ReservaDTO> listarReservasPorTour(Long tourId) {
        List<Reserva> reservas = reservaRepository.findByTourId(tourId);
        return reservas.stream().map(this::mapToDTO).toList();
    }

    //listar todas las reservas
    public List<ReservaDTO> listarReservas() {
        return reservaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //deletear reserva
    public void eliminarReserva(Long id) throws Exception {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva no encontrada con ID: " + id));
        reservaRepository.delete(reserva);
    }

    // Mapear entidad a DTO
    private ReservaDTO mapToDTO(Reserva reserva) {
        return new ReservaDTO(
                reserva.getId(),
                reserva.getTour().getId(),
                reserva.getCliente().getId(),
                reserva.getFechaReserva()
        );
    }

}
