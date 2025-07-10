package com.fwtours.fwalkingtours.repositories;

import com.fwtours.fwalkingtours.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    //buscar reserva por tour
    List<Reserva> findByTourId(Long tourId);
}
