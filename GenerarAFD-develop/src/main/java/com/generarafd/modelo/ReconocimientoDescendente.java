package com.generarafd.modelo;


import javax.swing.JOptionPane;

import java.util.Stack;

public class ReconocimientoDescendente {
    private String eR;
    private Stack<String> pila = new Stack<>();
    private String simbolo;
    private int x = 0;
    private boolean salir = false, correcto = false;

    //Constructor
    public ReconocimientoDescendente(String er) {
        this.eR = er + "-";
        leerEr();
    }

    public void leerEr() {
        pila.push("λ");//Indicador de pila vacia
        pila.push("A");//no Terminar inicial de la G
        simbolo = String.valueOf(eR.charAt(x));
        while (!salir) {
            switch (pila.lastElement()) {
                case "A":
                    switch (simbolo) {
                        case "|":
                        case ".":
                            JOptionPane.showMessageDialog(null, "Hay un operador al inicio de la ER",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "(":
                        case "1":
                        case "0":
                            remplazar("BC");
                            break;
                        case ")":
                            JOptionPane.showMessageDialog(null, "Hay un abre parentesis al inicio de la ER",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "*":
                        case "+":
                            JOptionPane.showMessageDialog(null, "Hay un operador elevado al inicio de la ER",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "-":
                            JOptionPane.showMessageDialog(null, "No usar el simbolo fin se secuencua -",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                    }
                    break;
                case "B":
                    switch (simbolo) {
                        case "|":
                            remplazar("BC");
                            avance();
                            break;
                        case ".":
                            JOptionPane.showMessageDialog(null, "7",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "(":
                            JOptionPane.showMessageDialog(null, "8",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case ")":
                        case "-":
                            desapilar();
                            break;
                        case "0":
                            JOptionPane.showMessageDialog(null, "9",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "1":
                            JOptionPane.showMessageDialog(null, "10",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "*":
                            JOptionPane.showMessageDialog(null, "11",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "+":
                            JOptionPane.showMessageDialog(null, "12",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                    }
                    break;
                case "C":
                    switch (simbolo) {
                        case "|":
                        case ".":
                            salir = true;
                            break;
                        case "(":
                        case "0":
                        case "1":
                            remplazar("ED");
                            break;
                        case ")":
                            JOptionPane.showMessageDialog(null, "Hay un cierre parentesis despues de un operador",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "*":
                        case "+":
                            JOptionPane.showMessageDialog(null, "Hay un operador elevado despues de un operador",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "-":
                            JOptionPane.showMessageDialog(null, "No usar el simbolo (-) fin de secuencia o verificar operador al final de la ER",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                    }
                    break;
                case "D":
                    switch (simbolo) {
                        case "|":
                        case ".":
                            JOptionPane.showMessageDialog(null, "Hay dos operadores seguidos",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "(":
                        case "1":
                        case "0":
                            remplazar("GF");
                            break;
                        case ")":
                            JOptionPane.showMessageDialog(null, "Hay un cierre parentesis despues de un operador",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "*":
                        case "+":
                            JOptionPane.showMessageDialog(null, "Hay un operador elevado despues de un operador",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "-":
                            JOptionPane.showMessageDialog(null, "No usar el simbolo (-) fin de secuencia o verificar operador al final de la ER",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                    }
                    break;
                case "E":
                    switch (simbolo) {
                        case "|":
                        case "-":
                        case ")":
                            desapilar();
                            break;
                        case ".":
                            remplazar("ED");
                            avance();
                            break;
                        case "(":
                            JOptionPane.showMessageDialog(null, "Hay un parentesis despues de un operador elevado, usar operadores para separar",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "0":
                        case "1":
                            JOptionPane.showMessageDialog(null, "Hay un operando despues de un operador elevado, usar operadores para separar",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "*":
                        case "+":
                            JOptionPane.showMessageDialog(null, "Hay dos operadores elevados seguidos",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                    }
                    break;
                case "F":
                    switch (simbolo) {
                        case "|":
                            JOptionPane.showMessageDialog(null, "30",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case ".":
                            JOptionPane.showMessageDialog(null, "31",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "(":
                            remplazar(")A");
                            avance();
                            break;
                        case ")":
                            JOptionPane.showMessageDialog(null, "32",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "0":
                        case "1":
                            desapilar();
                            avance();
                            break;
                        case "*":
                            JOptionPane.showMessageDialog(null, "33",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "+":
                            JOptionPane.showMessageDialog(null, "34",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "-":
                            JOptionPane.showMessageDialog(null, "35",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                    }
                    break;
                case "G":
                    switch (simbolo) {
                        case "|":
                        case ".":
                        case ")":
                        case "-":
                            desapilar();
                            break;
                        case "(":
                            JOptionPane.showMessageDialog(null, "Hay un parentesis despues de un operando, poner un operador",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "0":
                        case "1":
                            JOptionPane.showMessageDialog(null, "Existen mas de 2 operandos seguidos",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "*":
                        case "+":
                            desapilar();
                            avance();
                            break;
                    }
                    break;
                case ")":
                    switch (simbolo) {
                        case "|":
                            JOptionPane.showMessageDialog(null, "39",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case ".":
                            JOptionPane.showMessageDialog(null, "40",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "(":
                            JOptionPane.showMessageDialog(null, "41",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case ")":
                            desapilar();
                            avance();
                            break;
                        case "0":
                            JOptionPane.showMessageDialog(null, "42",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "1":
                            JOptionPane.showMessageDialog(null, "43",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "*":
                            JOptionPane.showMessageDialog(null, "44",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "+":
                            JOptionPane.showMessageDialog(null, "45",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "-":
                            JOptionPane.showMessageDialog(null, "Falta un cierre parentesis",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                    }
                    break;
                case "λ":
                    switch (simbolo) {
                        case "|":
                            JOptionPane.showMessageDialog(null, "47",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case ".":
                            JOptionPane.showMessageDialog(null, "48",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "(":
                            JOptionPane.showMessageDialog(null, "49",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case ")":
                            JOptionPane.showMessageDialog(null, "Hay un abre parentesis de más",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "0":
                            JOptionPane.showMessageDialog(null, "51",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "1":
                            JOptionPane.showMessageDialog(null, "52",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "*":
                            JOptionPane.showMessageDialog(null, "53",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "+":
                            JOptionPane.showMessageDialog(null, "54",
                                    "Verificar Error", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                            break;
                        case "-":
                            correcto = true;
                            salir = true;
                            break;
                    }
                    break;
            }
        }
    }

    //Este metodo nos permitira avanzar en la ER
    private void avance() {
        x++;
        simbolo = String.valueOf(eR.charAt(x));
    }

    private void desapilar() {
        pila.pop();
    }

    //Este metodo se usa para remplazar el elemento de la pila por los nuevos simbolos
    private void remplazar(String cadena) {
        pila.pop();
        for (int i = 0; i < cadena.length(); i++) {
            pila.push(String.valueOf(cadena.charAt(i)));
        }
    }

    //Este metodo se usara para retornar un boolean en la que indicara si la expresion es correc
    public boolean isCorrecto() {
        return correcto;
    }
}

