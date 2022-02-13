package com.prueba.locatel.prueba.dto;

import javax.validation.constraints.NotNull;

public class NuevaCuenta {

    @NotNull
    private String numeroCuenta;

    @NotNull
    private String nombreUsuario;

    @NotNull
    private float valor;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getnombreUsuario() {
        return nombreUsuario;
    }

    public void setnombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
