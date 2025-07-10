package com.fwtours.fwalkingtours.repositories;

import com.fwtours.fwalkingtours.entities.Empresa;
import com.fwtours.fwalkingtours.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByEmpresa(Empresa empresa);
}
