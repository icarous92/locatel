package com.prueba.locatel.prueba.controller;

import com.prueba.locatel.prueba.dto.ListaCuenta;
import com.prueba.locatel.prueba.dto.ListaUsuarios;
import com.prueba.locatel.prueba.dto.Mensaje;
import com.prueba.locatel.prueba.dto.NuevaCuenta;
import com.prueba.locatel.prueba.entity.Cuenta;
import com.prueba.locatel.prueba.security.dto.ListaRoles;
import com.prueba.locatel.prueba.security.entity.Rol;
import com.prueba.locatel.prueba.security.entity.Usuario;
import com.prueba.locatel.prueba.security.service.UsuarioService;
import com.prueba.locatel.prueba.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/cuenta")
@CrossOrigin(origins = "*")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevaCuenta nuevaCuenta){
        try{
            Cuenta cuenta = new Cuenta();
            cuenta.setNumeroCuenta(nuevaCuenta.getNumeroCuenta());
            cuenta.setUsuarioId(usuarioService.getByNombreUsuario(nuevaCuenta.getnombreUsuario()).get());
            cuenta.setValor(nuevaCuenta.getValor());
            if(cuentaService.existsByNumeroCuenta(nuevaCuenta.getNumeroCuenta()))
                return new ResponseEntity(new Mensaje("Ya existe numero de cuenta."), HttpStatus.BAD_REQUEST);
            cuentaService.save(cuenta);
            return new ResponseEntity(new Mensaje("Cuenta registrada."), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje("Error al guardar la cuenta " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        try{
            List<Cuenta> cuentas = cuentaService.list();
            if(cuentas.isEmpty())
                return new ResponseEntity(new Mensaje("No existen cuentas"), HttpStatus.BAD_REQUEST);

            List<ListaCuenta> listaCuenta = cuentas.stream()
                    .map(cuenta -> new ListaCuenta(
                            (int) cuenta.getId(),
                            cuenta.getUsuario().getNombreUsuario().toString(),
                            cuenta.getNumeroCuenta().toString(),
                            (float) cuenta.getValor()
                    )).collect(Collectors.toList());

            return new ResponseEntity(listaCuenta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje("Error al consultar cuentas " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<?> buscar(@RequestParam(required = false) String numeroCuenta) {
        try {
            if (!cuentaService.existsByNumeroCuenta(numeroCuenta))
                return new ResponseEntity(new Mensaje("No se encontro la cuenta"), HttpStatus.BAD_REQUEST);

            Optional<Cuenta> cuenta = cuentaService.getByNumeroCuenta(numeroCuenta);
            ListaCuenta listaCuenta = new ListaCuenta(
                    (int) cuenta.get().getId(),
                    cuenta.get().getUsuario().getNombreUsuario().toString(),
                    cuenta.get().getNumeroCuenta().toString(),
                    (float) cuenta.get().getValor());

            return new ResponseEntity(listaCuenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Error al consultar número de cuenta " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios")
    public  ResponseEntity<?> usuarios(){
        try{
            List<Usuario> usuarios = usuarioService.list();
            if(usuarios.isEmpty())
                return new ResponseEntity(new Mensaje("No existen usuarios"), HttpStatus.BAD_REQUEST);
            List<ListaUsuarios> listaUsuarios = usuarios.stream()
                    .map(usuario -> new ListaUsuarios(
                            (int) usuario.getId(),
                            usuario.getNombreUsuario().toString()
                    )).collect(Collectors.toList());

            return new ResponseEntity(listaUsuarios, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje("Error al consultar usuarios " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
