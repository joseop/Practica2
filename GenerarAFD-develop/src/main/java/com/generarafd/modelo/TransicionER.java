package com.generarafd.modelo;
/* Esta clase es para crear objetos tipo Transiciones de Expresiones regulares,
   que representa las transiciones o las construcciones basicas de thompson.*/
public class TransicionER {
    private int estadoOrigen;
    private int estadoFinal;
    private final String simboloIngresado;

    //Constructor
    public TransicionER(int estadoOrigen, int estadoFinal, String simboloIngresado) {
        this.estadoOrigen = estadoOrigen;
        this.estadoFinal = estadoFinal;
        this.simboloIngresado = simboloIngresado;
    }

    //Metodos de acceso
    public int getEstadoOrigen() {
        return estadoOrigen;
    }

    public void setEstadoOrigen(int estadoOrigen) {
        this.estadoOrigen = estadoOrigen;
    }

    public int getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(int estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public String getSimboloIngresado() {
        return simboloIngresado;
    }

    //Metodo que retorna una cadena con los datos a mostrar en pantalla
    public String mostrar() {
        return getEstadoOrigen() + "  --  " + getSimboloIngresado() + "  -->  " + getEstadoFinal();
    }
}
