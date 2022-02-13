package com.prueba.locatel.prueba.service;

import com.prueba.locatel.prueba.entity.Cuenta;
import com.prueba.locatel.prueba.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuentaService {
    @Autowired
    CuentaRepository cuentaRepository;

    public Optional<Cuenta> getByNumeroCuenta(String numeroCuenta){
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    @Modifying
    public void save(Cuenta cuenta){
        cuentaRepository.save(cuenta);
    }

    public boolean existsByNumeroCuenta (String numeroCuenta){
        return cuentaRepository.existsByNumeroCuenta(numeroCuenta);
    }

    public List<Cuenta> list(){
        return cuentaRepository.findAll();
    }
}
