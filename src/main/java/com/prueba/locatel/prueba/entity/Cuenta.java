package com.prueba.locatel.prueba.entity;

import com.prueba.locatel.prueba.security.entity.Rol;
import com.prueba.locatel.prueba.security.entity.Usuario;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String numeroCuenta;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    private float valor;

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.EAGER)
    private List<Movimiento> movimientos;


    public Cuenta() {
    }

    public Cuenta(int id, String numeroCuenta, Usuario usuario, float valor) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.usuario = usuario;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Usuario getUsuarioId() {
        return usuario;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuario = usuarioId;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
