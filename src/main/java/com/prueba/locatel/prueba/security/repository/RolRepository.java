package com.prueba.locatel.prueba.security.repository;

import com.prueba.locatel.prueba.security.entity.Rol;
import com.prueba.locatel.prueba.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    boolean existsById(int id);
}
