package com.prueba.locatel.prueba.repository;

import com.prueba.locatel.prueba.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

}
