package com.prueba.locatel.prueba.dto;

import javax.validation.constraints.NotNull;

public class ListaCuenta {
    @NotNull
    private int cuentaId;

    @NotNull
    private String usuarioNombre;

    @NotNull
    private String numeroCuenta;

    @NotNull
    private float valor;

    public ListaCuenta() {
    }

    public ListaCuenta(int cuentaId, String usuarioNombre, String numeroCuenta, float valor) {
        this.cuentaId = cuentaId;
        this.usuarioNombre = usuarioNombre;
        this.numeroCuenta = numeroCuenta;
        this.valor = valor;
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

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
