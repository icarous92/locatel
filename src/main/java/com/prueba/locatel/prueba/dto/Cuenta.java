package com.prueba.locatel.prueba.dto;

import javax.validation.constraints.NotNull;

public class Cuenta {
    @NotNull
    private String numeroCuenta;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
