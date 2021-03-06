package com.prueba.locatel.prueba.security.service;

import com.prueba.locatel.prueba.security.entity.Rol;
import com.prueba.locatel.prueba.security.enums.RolNombre;
import com.prueba.locatel.prueba.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public boolean existsById (int id){
       return rolRepository.existsById(id);
    }

    public List<Rol> list(){
        return rolRepository.findAll();
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
