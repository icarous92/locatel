package com.prueba.locatel.prueba.util;

import com.prueba.locatel.prueba.security.entity.Rol;
import com.prueba.locatel.prueba.security.enums.RolNombre;
import com.prueba.locatel.prueba.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateRoles implements CommandLineRunner {
    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        if(!rolService.existsById(1)) {
            Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
            rolService.save(rolAdmin);
        }
        if(!rolService.existsById(2)) {
            Rol rolUser = new Rol(RolNombre.ROLE_USER);
            rolService.save(rolUser);
        }
    }
}
