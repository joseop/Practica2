package com.generarafd.modelo;

import java.util.ArrayList;
import java.util.Objects;

//Esta clase se encarga de buscar los cierre de lambda de cada estado
public class CierreLambda {
    private static final Elemento elementos = new Elemento();
    private static final ArrayList<Boolean> yaProcesado = new ArrayList<>();
    private static int[][] matrizCierreLambda = new int[elementos.getAceptacion()][elementos.getAceptacion()];

    //Este metodo vacia la lista de yaProcesados y
    // la matriz de Cierre de lambda cada que se evalua una nueva expresion regular
    public static void vaciar() {
        yaProcesado.clear();
        matrizCierreLambda = new int[0][0];
    }

    //Constructor
    public CierreLambda() {
        matrizCierreLambda = new int[elementos.getAceptacion()][elementos.getAceptacion()];
        buscarCierreLambda();
        new ConstruirAFDSinLambda(matrizCierreLambda);

    }

    //Este metodo se encarga de recorrer los cierre de lambda de los estados
    private void buscarCierreLambda() {
        crearArrayListBoolean();
        for (int i = 0; i < matrizCierreLambda.length; i++) {
            convertirArrayListAFalso();
            int k = 0;
            matrizCierreLambda[i][k] = i + 1;
            k++;
            for (int j = 0; j < elementos.getSizeER(); j++) {
                if (existeEnFila(elementos.getTransicionER(j).getEstadoOrigen(), i) && !yaProcesado.get(j)) {
                    if (Objects.equals(elementos.getTransicionER(j).getSimboloIngresado(), "λ")) {
                        matrizCierreLambda[i][k] = elementos.getTransicionER(j).getEstadoFinal();
                        yaProcesado.set(j, true);
                        k++;
                        j = 0;
                    }
                }
            }
        }
    }

    //Este metodo agrega datos falsos a la lista de yaProcesados
    private void crearArrayListBoolean() {
        for (int m = 0; m < elementos.getAceptacion(); m++) {
            yaProcesado.add(false);
        }
    }

    //Este metodo se encarga de convertir la lista de yaProcesados a false
    private void convertirArrayListAFalso() {
        for (int m = 0; m < elementos.getAceptacion(); m++) {
            yaProcesado.add(m, false);
        }
    }

    //Este metodo se encarga de retornar una cadena de los cierre de lambda de los estados
    //para imprimirlos en pantalla
    public static String stringMatriz() {
        StringBuilder cadena = new StringBuilder();
        int i = 1;
        for (int[] ints : matrizCierreLambda) {
            cadena.append(i).append("λ: ");
            i++;
            for (int j = 0; j < matrizCierreLambda.length; j++) {
                if (ints[j] != 0) {
                    cadena.append(ints[j]).append(" ");
                }
            }
            cadena.append("\n");
        }
        return cadena.toString();
    }

    //este metodo verifica si un dato o elemento existe en una respectiva fila de la matriz de cierreLambda
    private boolean existeEnFila(int dato, int fila) {
        for (int j = 0; j < matrizCierreLambda.length; j++) {
            if (dato == matrizCierreLambda[fila][j]) {
                return true;
            }
        }
        return false;
    }
}
