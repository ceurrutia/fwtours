package com.fwtours.fwalkingtours.services;

import com.fwtours.fwalkingtours.dto.ReservaDTO;
import com.fwtours.fwalkingtours.entities.Empresa;
import com.fwtours.fwalkingtours.entities.Reserva;
import com.fwtours.fwalkingtours.entities.Tour;
import com.fwtours.fwalkingtours.entities.Usuario;
import com.fwtours.fwalkingtours.repositories.ReservaRepository;
import com.fwtours.fwalkingtours.repositories.TourRepository;
import com.fwtours.fwalkingtours.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final TourRepository tourRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpresaService empresaService;

    public ReservaService(ReservaRepository reservaRepository,
                          TourRepository tourRepository,
                          UsuarioRepository usuarioRepository, EmpresaService empresaService) {
        this.reservaRepository = reservaRepository;
        this.tourRepository = tourRepository;
        this.usuarioRepository = usuarioRepository;
        this.empresaService = empresaService;
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

    public List<ReservaDTO> obtenerReservasDeEmpresa(String emailUsuario) {
        Optional<Empresa> empresaOpt = empresaService.findByEmailUsuario(emailUsuario);

        if (empresaOpt.isEmpty()) {
            return List.of();
        }

        Empresa empresa = empresaOpt.get();

        return reservaRepository.findAll().stream()
                .filter(r -> r.getTour() != null && r.getTour().getEmpresa() != null)
                .filter(r -> r.getTour().getEmpresa().getId().equals(empresa.getId()))
                .map(ReservaDTO::fromEntity)
                .toList();
    }

    //reservas de cda cliente
    public List<Reserva> obtenerReservasPorCliente(Usuario cliente) {
        return reservaRepository.findByCliente(cliente);
    }

    //listar reservas por tour
    public List<ReservaDTO> listarReservasPorTour(Long tourId) {
        List<Reserva> reservas = reservaRepository.findByTourId(tourId);
        return reservas.stream().map(this::mapToDTO).toList();
    }

    //listar todas las reservas
    public List<ReservaDTO> listarReservas() {
        return reservaRepository.findAllConRelaciones()
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
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFechaReserva(reserva.getFechaReserva());

        if (reserva.getTour() != null) {
            dto.setTourId(reserva.getTour().getId());
            dto.setNombreTour(reserva.getTour().getNombreTour());

            if (reserva.getTour().getEmpresa() != null) {
                dto.setNombreEmpresa(reserva.getTour().getEmpresa().getNombreEmpresa());
            }
        }

        if (reserva.getCliente() != null) {
            dto.setClienteId(reserva.getCliente().getId());
            dto.setEmailCliente(reserva.getCliente().getEmail());
        }

        return dto;
    }

}
