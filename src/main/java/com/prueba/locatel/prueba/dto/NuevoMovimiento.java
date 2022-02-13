package com.prueba.locatel.prueba.dto;

import javax.validation.constraints.NotNull;

public class NuevoMovimiento {
    @NotNull
    private String numeroCuenta;

    @NotNull
    private float valor;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
