package com.prueba.locatel.prueba.security.controller;

import com.prueba.locatel.prueba.dto.ListaCuenta;
import com.prueba.locatel.prueba.dto.Mensaje;
import com.prueba.locatel.prueba.entity.Cuenta;
import com.prueba.locatel.prueba.security.dto.JwtDto;
import com.prueba.locatel.prueba.security.dto.ListaRoles;
import com.prueba.locatel.prueba.security.dto.LoginUsuario;
import com.prueba.locatel.prueba.security.dto.NuevoUsuario;
import com.prueba.locatel.prueba.security.entity.Rol;
import com.prueba.locatel.prueba.security.entity.Usuario;
import com.prueba.locatel.prueba.security.enums.RolNombre;
import com.prueba.locatel.prueba.security.jwt.JwtProvider;
import com.prueba.locatel.prueba.security.service.RolService;
import com.prueba.locatel.prueba.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        if(nuevoUsuario.getRoles().isEmpty()){
            return new ResponseEntity(new Mensaje("selecciona un rol"), HttpStatus.BAD_REQUEST);
        }
        if(nuevoUsuario.getRoles().contains("ROLE_USER"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("ROLE_ADMIN")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public  ResponseEntity<?> roles(){
        try{
            List<Rol> roles = rolService.list();
            if(roles.isEmpty())
                return new ResponseEntity(new Mensaje("No existen roles"), HttpStatus.BAD_REQUEST);
            List<ListaRoles> listaRoles = roles.stream()
                    .map(rol -> new ListaRoles(
                            (int) rol.getId(),
                            rol.getRolNombre().toString()
                    )).collect(Collectors.toList());

            return new ResponseEntity(listaRoles, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje("Error al consultar roles " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
