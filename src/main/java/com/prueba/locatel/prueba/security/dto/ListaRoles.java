package com.prueba.locatel.prueba.security.dto;

import javax.validation.constraints.NotNull;

public class ListaRoles {
    @NotNull
    private int id;

    @NotNull
    private String rol;

    public ListaRoles() {
    }

    public ListaRoles(int id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
