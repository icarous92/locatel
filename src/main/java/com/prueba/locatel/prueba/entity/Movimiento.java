package com.prueba.locatel.prueba.entity;

import com.prueba.locatel.prueba.enums.TipoMovimiento;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    private Cuenta cuenta;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @NotNull
    private float valorAnterior;

    @NotNull
    private float valorNuevo;

    @NotNull
    private Date fecha;

    public Movimiento() {
    }

    public Movimiento(int id, Cuenta cuenta, TipoMovimiento tipoMovimiento, float valorAnterior, float valorNuevo, Date fecha) {
        this.id = id;
        this.cuenta = cuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cuenta getCuentaId() {
        return cuenta;
    }

    public void setCuentaId(Cuenta cuentaId) {
        this.cuenta = cuentaId;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public float getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(float valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public float getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(float valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
