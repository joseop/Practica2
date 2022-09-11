package com.generarafd.modelo;

import java.util.ArrayList;
import java.util.Objects;

//Esta clase permite agrupar los estados para construir el AFD minimo
public class ParticionesDeEstado {
    private final Elemento elementos = new Elemento();
    private static final ArrayList<GrupoEstado> grupoEstados = new ArrayList<>();
    private static final ArrayList<Integer> grupos = new ArrayList<>();
    private static final ArrayList<Integer> apuntadorAGrupo = new ArrayList<>();
    private static final ArrayList<Integer> posicion = new ArrayList<>();
    private static final ArrayList<Integer> gruporeferencia = new ArrayList<>();

    //Este metodo se usa para vaciar las listas cuando se evalua una nueva ER
    public static void vaciar() {
        grupoEstados.clear();
        grupos.clear();
        apuntadorAGrupo.clear();
        posicion.clear();
        gruporeferencia.clear();
    }

    //Constructor
    public ParticionesDeEstado() {
        gruposIniciales();
        new AFDMinimo(grupoEstados, grupos);
    }

    //Este metodo se encarga de crear los grupos iniciales
    public void gruposIniciales() {
        grupos.add(0);
        grupos.add(1);
        for (int i = 0; i < elementos.getsizeAFDN(); i++) {
            if (!yaExiste(elementos.getTransicionAFDN(i).getEstadoOrigen())) {
                if (elementos.getTransicionAFDN(i).isAceptacion()) {
                    grupoEstados.add(new GrupoEstado(elementos.getTransicionAFDN(i).getEstadoOrigen(), 1));
                } else {
                    grupoEstados.add(new GrupoEstado(elementos.getTransicionAFDN(i).getEstadoOrigen(), 0));
                }
            }
        }
        evaluarGrupos();
        eliminarVacios();

    }

    //Este metodo se encarga de eliminar los grupos vacios
    private static void eliminarVacios() {
        for (int j = 0; j < grupos.size(); j++) {
            boolean vacio = true;
            for (int i = 0; i < grupoEstados.size(); i++) {
                if (grupoEstados.get(i).getGrupo() == grupos.get(j)) {
                    if (grupoEstados.get(i).getEstado() != null) {
                        vacio = false;
                    }
                }
            }
            if (vacio) {
                for (int i = 0; i < grupoEstados.size(); i++) {
                    if (grupoEstados.get(i).getGrupo() == grupos.get(j)) {
                        grupoEstados.remove(i);
                    }
                }
                grupos.remove(j);
            }
        }
    }

    //Este metodo verifica si un estado ya existe en un grupo
    private boolean yaExiste(String estadoOrigen) {
        for (GrupoEstado estado : grupoEstados) {
            if (estado.getEstado().equals(estadoOrigen)) {
                return true;
            }
        }
        return false;
    }

    //Este metodo se encarga de evaluar los estados y continuar creando grupos y agrupando los estados
    public void evaluarGrupos() {
        boolean cambio;
        for (int k = 0; k < elementos.getSizeSimbolos(); k++) {// Recorre los simbolos
            for (int h = 0; h < grupos.size(); h++) {
                apuntadorAGrupo.clear();
                posicion.clear();
                for (int i = 0; i < grupoEstados.size(); i++) {//Recorre los grupos de los estados
                    if (grupoEstados.get(i).getGrupo() == grupos.get(h)) {
                        apuntadorAGrupo.add(obtenerApuntadorDeEstado(obtenerTransicionAFDN(grupoEstados.get(i).getEstado(), elementos.getSimbolo(k))));
                        posicion.add(i);
                    }
                }
                cambio = procesarCambiosDeGrupo();
                if (cambio) {
                    k = 0;
                    cambio = false;
                }
            }
        }
    }

    //Este metodo se encarga de procesar los cambios o hacerle los cambios al grupo
    private boolean procesarCambiosDeGrupo() {
        if (apuntadorAGrupo.size() > 0) {
            gruporeferencia.clear();
            gruporeferencia.add(apuntadorAGrupo.get(0));
        }
        boolean cambio = false;
        int nuevoGrupo = grupoDeMayorGrado() + 1;
        for (int i = 1; i < apuntadorAGrupo.size(); i++) {
            for (int j = 0; j < gruporeferencia.size(); j++) {
                if (Objects.equals(apuntadorAGrupo.get(i), apuntadorAGrupo.get(j))) {
                    break;
                } else {
                    gruporeferencia.add(nuevoGrupo);
                    grupoEstados.get(posicion.get(i)).setGrupo(nuevoGrupo);
                    grupos.add(nuevoGrupo);
                    cambio = true;
                }
            }
        }
        return cambio;
    }

    //Este metodo se encarga de buscar u obtener una transicion AFD del AFD con variables
    public String obtenerTransicionAFDN(String estadoOrigen, String simboloIngresado) {
        for (int i = 0; i < elementos.getsizeAFDN(); i++) {
            if (elementos.getTransicionAFDN(i).getSimboloIngresado().equals(simboloIngresado) &&
                    elementos.getTransicionAFDN(i).getEstadoOrigen().equals(estadoOrigen)) {
                return elementos.getTransicionAFDN(i).getEstadoFinal();
            }
        }
        return null;
    }

    //Este metodo retorna una cadena con los grupos y los estados pertenecientes a estos
    public static String stringGE() {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i <= grupoDeMayorGrado(); i++) {
            cadena.append("p").append(i).append(" : ");
            for (GrupoEstado grupoEstado : grupoEstados) {
                if (i == grupoEstado.getGrupo()) {
                    cadena.append(" ").append(grupoEstado.getEstado());
                }
            }
            cadena.append("\n");
        }
        return cadena.toString();
    }

    //Este metodo retorna el grupo de un estado
    public int obtenerApuntadorDeEstado(String estado) {
        for (GrupoEstado value : grupoEstados) {
            if (value.getEstado().equals(estado)) {
                return value.getGrupo();
            }
        }
        return 0;
    }

    //Este metodo se encarga de retornar el valor numero del ultimo grupo creado
    public static int grupoDeMayorGrado() {
        int max = 0;
        for (GrupoEstado estado : grupoEstados) {
            if (estado.getGrupo() >= max) {
                max = estado.getGrupo();
            }
        }
        return max;
    }

}
