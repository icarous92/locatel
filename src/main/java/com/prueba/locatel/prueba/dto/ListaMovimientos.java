package com.prueba.locatel.prueba.dto;

import javax.validation.constraints.NotNull;

public class ListaMovimientos {
    @NotNull
    private int cuentaId;

    @NotNull
    private String movimiento;

    @NotNull
    private float valorNuevo;

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public float getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(float valorNuevo) {
        this.valorNuevo = valorNuevo;
    }
}
