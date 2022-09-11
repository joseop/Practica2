package com.generarafd.modelo;

//Esta clase permite asignarle variables numericas a los nuevos estados
public class AsignarVariableNumericaAEstado {
    private static final ConstruirAFDSinLambda estados = new ConstruirAFDSinLambda();
    private final Elemento elementos = new Elemento();

    //Constructor
    public AsignarVariableNumericaAEstado() {
        convertirEstadosEnVariablesNumericas();
        new ParticionesDeEstado();
    }

    //Este metodo asigna las variables numericas a los estados
    public void convertirEstadosEnVariablesNumericas() {
        for (int i = 0; i < elementos.getSizeAFD(); i++) {
            TransicionAFD transicionAFD = new TransicionAFD(elementos.getTransicionAFD(i).isAceptacion(),
                    posicionDelEstado(elementos.getTransicionAFD(i).getEstadoOrigen()),
                    posicionDelEstado(elementos.getTransicionAFD(i).getEstadoFinal()),
                    elementos.getTransicionAFD(i).getSimboloIngresado());
            elementos.addAFDN(transicionAFD);
        }
    }

    //Este metodo retorna la posicion de un estado en la lista
    private String posicionDelEstado(String estado) {
        for (int i = 0; i < estados.getEstadosssSize(); i++) {
            if (estado.equals(estados.getEstadoss(i))) {
                i++;
                return String.valueOf(i);
            }
        }
        return estado;
    }
}
