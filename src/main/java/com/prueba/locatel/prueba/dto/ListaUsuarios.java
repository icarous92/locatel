package com.prueba.locatel.prueba.dto;

import javax.validation.constraints.NotNull;

public class ListaUsuarios {
    @NotNull
    private int id;

    @NotNull
    private String usuario;

    public ListaUsuarios(int id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

