package com.generarafd.modelo;

import java.util.ArrayList;

//Esta clase permite crear las listas donde se almacenaran las transiciones de la ER y los diferentes AFD
public class Elemento {
    private static final ArrayList<TransicionAFD> transicionesAFD = new ArrayList<>();
    private static final ArrayList<TransicionER> transicionesER = new ArrayList<>();
    private static final ArrayList<String> simbolos = new ArrayList<>();
    private static final ArrayList<TransicionAFD> AFDVariables = new ArrayList<>();
    private static final ArrayList<TransicionAFD> AFDMinimo = new ArrayList<>();

    public Elemento() {
    }


    //Metodos del AFD
    public void addTransicionAFD(TransicionAFD transicionAFD) {
        transicionesAFD.add(transicionAFD);
    }

    public String stringAFD() {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < getSizeAFD(); i++) {
            cadena.append(transicionesAFD.get(i).mostrar2()).append("\n");
        }
        return cadena.toString();
    }

    public int getSizeAFD() {
        return transicionesAFD.size();
    }

    public TransicionAFD getTransicionAFD(int i) {
        return transicionesAFD.get(i);
    }


    //Metodos de la ER
    public TransicionER getTransicionER(int i) {
        return transicionesER.get(i);
    }

    public void addTransicionER(TransicionER transicionER) {
        transicionesER.add(transicionER);
    }

    public void ordenarTransicionesER() {
        transicionesER.sort((p1, p2) -> Integer.valueOf(p1.getEstadoOrigen()).compareTo(Integer.valueOf(p2.getEstadoOrigen())));
    }


    public void addTransicionesER(ArrayList<TransicionER> transicionERS) {
        transicionesER.addAll(transicionERS);
    }

    public void incrementarER(int base, int valorAumentar) {
        for (TransicionER transicionER : transicionesER) {
            if (transicionER.getEstadoOrigen() > base) {
                transicionER.setEstadoOrigen(transicionER.getEstadoOrigen() + valorAumentar);
            }
            if (transicionER.getEstadoFinal() > base) {
                transicionER.setEstadoFinal(transicionER.getEstadoFinal() + valorAumentar);
            }
        }
    }

    public void borrarER(int i) {
        transicionesER.remove(i);
    }

    public String stringER() {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < getSizeER(); i++) {
            cadena.append(transicionesER.get(i).mostrar()).append("\n");
        }
        return cadena.toString();
    }

    public int getSizeER() {
        return transicionesER.size();
    }

    public int getAceptacion() {
        int aceptacion = 0;
        for (int i = 0; i < transicionesER.size(); i++) {
            if (getTransicionER(i).getEstadoFinal() > aceptacion) {
                aceptacion = getTransicionER(i).getEstadoFinal();
            }
        }
        return aceptacion;
    }


    //Metodos de los simbolos de la ER
    public String getSimbolo(int i) {
        return simbolos.get(i);
    }

    public void addSimbolo(String s) {
        simbolos.add(s);
    }

    public int getSizeSimbolos() {
        return simbolos.size();
    }


    //Metodos del AFD en variables
    public TransicionAFD getTransicionAFDN(int i) {
        return AFDVariables.get(i);
    }

    public int getsizeAFDN() {
        return AFDVariables.size();
    }

    public String stringAFDN() {
        StringBuilder cadena = new StringBuilder();
        for (TransicionAFD afdVariable : AFDVariables) {
            cadena.append(afdVariable.mostrar()).append("\n");
        }
        return cadena.toString();
    }

    public void addAFDN(TransicionAFD transicionAFD) {
        AFDVariables.add(transicionAFD);
    }

    //Metodos del AFDMinimo
    public void addTransicionAFDMinimo(TransicionAFD transicionAFD) {
        AFDMinimo.add(transicionAFD);
    }

    public String stringAFDMinimo() {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < getSizeAFDMinimo(); i++) {
            cadena.append(AFDMinimo.get(i).mostrar()).append("\n");
        }
        return cadena.toString();
    }

    public int getSizeAFDMinimo() {
        return AFDMinimo.size();
    }

    //Metodo para vaciar las listas
    public void vaciar() {
        transicionesAFD.clear();
        transicionesER.clear();
        simbolos.clear();
        AFDVariables.clear();
        AFDMinimo.clear();
    }

    //Metodo que retorna una cadena del AFD
    public String representacionAFD(){
        String cadena="     ";
        for (int i = 0; i < simbolos.size(); i++) {
            cadena=cadena+simbolos.get(i)+"   ";
        }
        cadena=cadena+"   ";
        for (int i = 0; i <AFDVariables.size(); i+=simbolos.size()) {
            cadena=cadena+"\n"+AFDVariables.get(i).getEstadoOrigen()+"  |";
            for (int j = 0; j < simbolos.size(); j++) {
                cadena=cadena+AFDVariables.get(i+j).getEstadoFinal()+" | ";
            }
            if (AFDVariables.get(i).isAceptacion()) {
                cadena=cadena+" 1";
            }else {cadena=cadena+" 0";}
        }
        return cadena;
    }

    //Metodo que retorna una cadena del AFD minimo
    public String representacionAFDM(){
        String cadena="       ";
        for (int i = 0; i < simbolos.size(); i++) {
            cadena=cadena+simbolos.get(i)+"    ";
        }
        cadena=cadena+"   ";
        for (int i = 0; i <AFDMinimo.size(); i+=simbolos.size()) {
            cadena=cadena+"\n"+AFDMinimo.get(i).getEstadoOrigen()+"   |";
            for (int j = 0; j < simbolos.size(); j++) {
                cadena=cadena+AFDMinimo.get(i+j).getEstadoFinal()+"|";
            }
            if (AFDMinimo.get(i).isAceptacion()) {
                cadena=cadena+"    1";
            }else {cadena=cadena+"    0";}
        }
        return cadena;
    }
}
