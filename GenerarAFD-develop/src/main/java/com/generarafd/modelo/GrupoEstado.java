package com.generarafd.modelo;

//Esta clase permite crear objetos que representan los grupos y los estados
public class GrupoEstado {
    private final String estado;
    private int grupo;

    public GrupoEstado(String estado, int grupo) {
        this.estado = estado;
        this.grupo = grupo;
    }

    public String getEstado() {
        return estado;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

}
