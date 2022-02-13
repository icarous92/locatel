package com.prueba.locatel.prueba.service;

import com.prueba.locatel.prueba.entity.Movimiento;
import com.prueba.locatel.prueba.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MovimientoService {
    @Autowired
    MovimientoRepository movimientoRepository;

    public void save(Movimiento movimiento){
        movimientoRepository.save(movimiento);
    }
}
