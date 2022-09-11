package com.generarafd.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/*Esta clase permite crear el AFD sin transiciones lambdas*/
public class ConstruirAFDSinLambda {

    private final Elemento elementos = new Elemento();

    static final ArrayList<int[]> nuevosEstadosEnVectores = new ArrayList<>();
    static final ArrayList<String> estadosEnString = new ArrayList<>();
    private int[][] matrizCierreLambda;

    //Este metodo vacia las listas cuando se evalua una nueva ER
    public static void vaciar() {
        nuevosEstadosEnVectores.clear();
        estadosEnString.clear();
    }

    //Constructor vacio
    public ConstruirAFDSinLambda() {
    }

    //Constructor
    public ConstruirAFDSinLambda(int[][] matriz) {
        this.matrizCierreLambda = matriz;
        inicial();
        new AsignarVariableNumericaAEstado();
    }

    //Este metodo retorna el tamaño de la lista
    public int getEstadosssSize() {
        return estadosEnString.size();
    }

    //Este metodo retorna el elemento de la posicion i de la lista estadosEnString
    public String getEstadoss(int i) {
        return estadosEnString.get(i);
    }

    //Este metodo evalua el prime cierre de lambda de la matrizCierreLambda
    private void inicial() {
        int[] aux = new int[matrizCierreLambda.length];
        for (int i = 0; i < matrizCierreLambda.length; i++) {
            if (matrizCierreLambda[0][i] != 0) {
                aux[i] = matrizCierreLambda[0][i];
            }
        }
        nuevosEstadosEnVectores.add(aux);
        evaluarCierreLambdaInicial();
    }

    //Este metodo retorna una cadena con los nuevos estados despues de evaluar los cierreLambda de cada estado
    public static String stringNuevosEstados() {
        StringBuilder cadena = new StringBuilder();
        int i = 1;
        for (String s : estadosEnString) {
            cadena.append(i).append(" : ").append(s).append("\n");
            i++;
        }
        return cadena.toString();
    }

    //Este metodo evalua los cierreLambda para obtener los nuevos estados
    private void evaluarCierreLambdaInicial() {
        int n = 0;
        while (n < nuevosEstadosEnVectores.size()) {//Recorre los estados en vectores
            for (int j = 0; j < elementos.getSizeSimbolos(); j++) {//Recorre los simbolos ingresados en la ER
                int[] vector = new int[matrizCierreLambda.length]; //se crea un vector
                for (int k = 0; k < nuevosEstadosEnVectores.get(n).length; k++) {//Recorre cada elemento del vector de nuevo estado
                    if (nuevosEstadosEnVectores.get(n)[k] != 0) {//no se opera el cero
                        for (int l = 0; l < elementos.getSizeER(); l++) {//Recorre las transiciones de ER
                            if (elementos.getTransicionER(l).getEstadoOrigen() == nuevosEstadosEnVectores.get(n)[k]) {
                                if (elementos.getSimbolo(j).equals(elementos.getTransicionER(l).getSimboloIngresado())) {
                                    agregarEstadoAVector(elementos.getTransicionER(l).getEstadoFinal() - 1, vector);
                                }
                            }
                        }
                    }
                }
                elementos.addTransicionAFD(captarTran(nuevosEstadosEnVectores.get(n), elementos.getSimbolo(j), vector));
                if (!existeEnNuevosEstados(vector)) {
                    agregarNuevoEstadoEnVector(vector);
                }
            }
            n++;
        }

    }

    //En este metodo se usa para obtener una transicion AFD
    private TransicionAFD captarTran(int[] vectorEstadoInicial, String simbolo, int[] vectorEstadoFinal) {
        StringBuilder estadoInicial = new StringBuilder();
        StringBuilder estadoFinal = new StringBuilder();
        boolean a = false;
        for (int i = 0; i < vectorEstadoInicial.length; i++) {
            if (vectorEstadoInicial[i] != 0) {
                estadoInicial.append(" ").append(vectorEstadoInicial[i]);
                if (elementos.getAceptacion() == vectorEstadoInicial[i]) {
                    a = true;
                }
            }
        }
        for (int j : vectorEstadoFinal) {
            if (j != 0) {
                estadoFinal.append(" ").append(j);
            }
        }
        if (existeCadenaEnEstadosString(estadoInicial.toString())) {
            estadosEnString.add(estadoInicial.toString());
        }
        if (existeCadenaEnEstadosString(estadoFinal.toString())) {
            estadosEnString.add(estadoFinal.toString());
        }
        return new TransicionAFD(a, estadoInicial.toString(), estadoFinal.toString(), simbolo);

    }

    //Este metodo se usa
    private boolean existeEnNuevosEstados(int[] vector) {
        for (int[] estado : nuevosEstadosEnVectores) {
            if (Arrays.equals(vector, estado)) {
                return true;
            }
        }
        return false;
    }

    //Este metodo se usa para verificar si ya existe un estado similiar
    private boolean existeCadenaEnEstadosString(String nuevoEstadoEnString) {
        for (String s : estadosEnString) {
            if (Objects.equals(s, nuevoEstadoEnString)) {
                return false;
            }
        }
        return true;
    }

    //Este metodo agrega el vector a la lista
    private void agregarNuevoEstadoEnVector(int[] vector) {
        nuevosEstadosEnVectores.add(vector);
    }

    //Este metodo se usa para agregar un estado al vector y asi crear los nuevos estados
    private void agregarEstadoAVector(int estadoFinal, int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == 0) {
                for (int j = 0; j < matrizCierreLambda[estadoFinal].length; j++) {
                    if (matrizCierreLambda[estadoFinal][j] != 0 && !estaEnVector(vector, matrizCierreLambda[estadoFinal][j])) {
                        vector[i] = matrizCierreLambda[estadoFinal][j];
                        i++;
                    }
                }
                break;
            }
        }
    }

    //Este metodo se usa para verificar si un estado ya existe en el vector
    private boolean estaEnVector(int[] vector, int elemento) {
        for (int i : vector) {
            if (i == elemento) {
                return true;
            }
        }
        return false;
    }

}
