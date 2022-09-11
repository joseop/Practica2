package com.generarafd.modelo;

import java.util.Objects;
import java.util.Stack;

/*Esta clase permite leer la expresion regular recibida como parametro, para procesarla
  y asi comenzar a crear objetos de TransicionER, usando construccion basica de thompson*/
public class LeerER {

    private final String expresionRegular;
    private final Elemento elementos = new Elemento();
    private final ConstruccionBasicaThompson operacion = new ConstruccionBasicaThompson();

    //Constructor
    public LeerER(String expresionRegular) {
        this.expresionRegular = expresionRegular;
        iniciarConexion();
        elementos.ordenarTransicionesER();
        new CierreLambda();
    }

    //Este metodo construye la conexion inicial
    private void iniciarConexion() {
        elementos.addTransicionER(operacion.reescribirExpresion(expresionRegular, 1));
        conectarTransiciones();
        capturarSimbolosDeLaER();
    }

    //Este metodo se encarga de evaluar los simbolos de las transiciones para seguir con la construccion
    // y conectar las transiciones
    private void conectarTransiciones() {
        int i = 0;
        while (i < elementos.getSizeER()) {
            if (elementos.getTransicionER(i).getSimboloIngresado().length() > 1) {
                nuevaTransicion(elementos.getTransicionER(i).getEstadoOrigen(), elementos.getTransicionER(i).getSimboloIngresado());
                elementos.borrarER(i);
                i = 0;
            } else {
                i++;
            }
        }
    }

    //Este metodo se encarga de crear la nueva transicion haciendo uso de las construcciones basicas de thompson
    //con su respectivo operando, y teniendo en cuenta el orden de prioridad
    private void nuevaTransicion(int estadoBase, String expresionReg) {
        int s = buscarDondeDividirLaER(expresionReg);
        if (s != 0) {
            String substring = expresionReg.substring(s + 1);
            if ((expresionReg.charAt(s) == '|')) {
                elementos.incrementarER(estadoBase, 4);
                elementos.addTransicionesER(operacion.expresionOR(expresionReg.substring(0, s), substring, estadoBase));
            }
            if ((expresionReg.charAt(s) == '.')) {
                elementos.incrementarER(estadoBase, 1);
                elementos.addTransicionesER(operacion.expresionAND(expresionReg.substring(0, s), substring, estadoBase));
            }
        } else {
            if (expresionReg.charAt(0) == '(') {
                String substring = expresionReg.substring(1, expresionReg.length() - 2);
                if ((expresionReg.charAt(expresionReg.length() - 1) == '*')) {
                    elementos.incrementarER(estadoBase, 2);
                    elementos.addTransicionesER(operacion.superIndiceAsterisco(substring, estadoBase));

                } else if ((expresionReg.charAt(expresionReg.length() - 1) == '+')) {
                    elementos.incrementarER(estadoBase, 2);
                    elementos.addTransicionesER(operacion.superIndiceMas(substring, estadoBase));

                } else if ((expresionReg.charAt(expresionReg.length() - 1) == ')')) {
                    elementos.addTransicionER(operacion.reescribirExpresion(expresionReg.substring(1, expresionReg.length() - 1), estadoBase));
                }
            } else {
                if ((expresionReg.charAt(expresionReg.length() - 1) == '*')) {
                    elementos.incrementarER(estadoBase, 2);
                    elementos.addTransicionesER(operacion.superIndiceAsterisco(expresionReg.substring(0, expresionReg.length() - 1), estadoBase));

                } else if ((expresionReg.charAt(expresionReg.length() - 1) == '+')) {
                    elementos.incrementarER(estadoBase, 2);
                    elementos.addTransicionesER(operacion.superIndiceMas(expresionReg.substring(0, expresionReg.length() - 1), estadoBase));
                }
            }
        }
    }

    //Este metodo se encarga de buscar la posiciones del operando con mayor prioridad
    private int buscarDondeDividirLaER(String simbolo) {
        Stack<Boolean> parentesis = new Stack<>();
        int separador = 0;
        for (int i = 0; i < simbolo.length(); i++) {
            if (simbolo.charAt(i) == '(') {
                parentesis.push(true);
            } else if (simbolo.charAt(i) == ')') {
                if (!parentesis.isEmpty()) {
                    parentesis.pop();
                }
            }
            if ((simbolo.charAt(i) == '|') && (parentesis.isEmpty())) {
                if (simbolo.charAt(separador) != '|') {
                    separador = i;
                }
            } else if ((simbolo.charAt(i) == '.') && (parentesis.isEmpty())) {
                if (simbolo.charAt(separador) != '.' && simbolo.charAt(separador) != '|') {
                    separador = i;
                }
            }
        }
        return separador;
    }

    //Este metodo se encarga de capturar los diferentes simbolos o expresiones de la Expresion regular
    private void capturarSimbolosDeLaER() {
        for (int i = 0; i < elementos.getSizeER(); i++) {
            String nulo = "λ";
            if (!Objects.equals(elementos.getTransicionER(i).getSimboloIngresado(), nulo) && !existeEnSimbolos(elementos.getTransicionER(i).getSimboloIngresado())) {
                elementos.addSimbolo(elementos.getTransicionER(i).getSimboloIngresado());
            }
        }
    }

    //Este metodo verifica si un simbolo existe en la lista de simbolos
    private boolean existeEnSimbolos(String simbolo) {
        for (int i = 0; i < elementos.getSizeSimbolos(); i++) {
            if (elementos.getSimbolo(i).equals(simbolo)) {
                return true;
            }
        }
        return false;
    }
}
