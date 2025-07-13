package com.fwtours.fwalkingtours.repositories;

import com.fwtours.fwalkingtours.entities.Reserva;
import com.fwtours.fwalkingtours.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    //buscar reserva por tour
    List<Reserva> findByTourId(Long tourId);
    List<Reserva> findByCliente(Usuario cliente);
    List<Reserva> findAll();

    @Query("""
        SELECT r FROM Reserva r
        JOIN FETCH r.tour t
        JOIN FETCH t.empresa e
        JOIN FETCH r.cliente c
    """)
    List<Reserva> findAllConRelaciones();
}
