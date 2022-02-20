package com.prueba.locatel.prueba.controller;

import com.prueba.locatel.prueba.dto.Mensaje;
import com.prueba.locatel.prueba.dto.NuevoMovimiento;
import com.prueba.locatel.prueba.entity.Cuenta;
import com.prueba.locatel.prueba.entity.Movimiento;
import com.prueba.locatel.prueba.enums.TipoMovimiento;
import com.prueba.locatel.prueba.service.CuentaService;
import com.prueba.locatel.prueba.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth/movimiento")
@CrossOrigin(origins = "*")
public class MovimientoController {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    MovimientoService movimientoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/consignar")
    public ResponseEntity<?> consignar(@Valid @RequestBody NuevoMovimiento nuevoMovimiento){
        try{
            if (!cuentaService.existsByNumeroCuenta(nuevoMovimiento.getNumeroCuenta()))
                return new ResponseEntity(new Mensaje("No se encontro la cuenta"), HttpStatus.BAD_REQUEST);
            Optional<Cuenta> cuenta = cuentaService.getByNumeroCuenta(nuevoMovimiento.getNumeroCuenta());
            Movimiento movimiento = new Movimiento();
            movimiento.setTipoMovimiento(TipoMovimiento.CONSIGNAR);
            movimiento.setCuentaId(cuenta.get());
            movimiento.setFecha(new Date());
            movimiento.setValorNuevo(nuevoMovimiento.getValor());
            movimiento.setValorAnterior(cuenta.get().getValor());
            movimientoService.save(movimiento);

            cuenta.get().setValor(nuevoMovimiento.getValor() + cuenta.get().getValor());
            cuentaService.save(cuenta.get());

            return new ResponseEntity(new Mensaje("Registrada su consignación"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje("Error al guardar consignación " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/retirar")
    public ResponseEntity<?> retirar(@Valid @RequestBody NuevoMovimiento nuevoMovimiento){
        try{
            if (!cuentaService.existsByNumeroCuenta(nuevoMovimiento.getNumeroCuenta()))
                return new ResponseEntity(new Mensaje("No se encontro la cuenta"), HttpStatus.BAD_REQUEST);

            Optional<Cuenta> cuenta = cuentaService.getByNumeroCuenta(nuevoMovimiento.getNumeroCuenta());

            if(cuenta.get().getValor() < nuevoMovimiento.getValor())
                return new ResponseEntity(new Mensaje("No tiene saldo suficiente"), HttpStatus.BAD_REQUEST);

            Movimiento movimiento = new Movimiento();
            movimiento.setTipoMovimiento(TipoMovimiento.RETIRAR);
            movimiento.setCuentaId(cuenta.get());
            movimiento.setFecha(new Date());
            movimiento.setValorNuevo(nuevoMovimiento.getValor());
            movimiento.setValorAnterior(cuenta.get().getValor());
            movimientoService.save(movimiento);
            cuenta.get().setValor(cuenta.get().getValor() - nuevoMovimiento.getValor());
            cuentaService.save(cuenta.get());

            return new ResponseEntity(new Mensaje("Registrada su retiro"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje("Error al guardar el retiro " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
