package com.example.Automata;

import java.util.ArrayList;

public class Automata {
    // x = estados y = simbolos
    private int[][] transiciones = new int[5][3];
    private int estadoActual = 0;
    private int estadoAnterior = 0;
    private ArrayList<Token> tokens = new ArrayList<>();
    private String[] palabrasReservadas = { "int" };
    // private int[] estadosAceptacion = {1,3,4};

    // numeros = 0
    // letras = 1
    // punto = 2
    public Automata() {
        // ESTADO 0
        transiciones[0][0] = 1;
        transiciones[0][1] = 4;
        transiciones[0][2] = -1;
        // ESTADO 1
        transiciones[1][0] = 1;
        transiciones[1][1] = 1;
        transiciones[1][2] = 2;
        // ESTADO 2
        transiciones[2][0] = 3;
        transiciones[2][1] = -1;
        transiciones[2][2] = -1;
        // ESTADO 3
        transiciones[3][0] = 3;
        transiciones[3][1] = -1;
        transiciones[3][2] = -1;
        // ESTADO4
        transiciones[4][0] = 4;
        transiciones[4][1] = 4;
        transiciones[4][2] = -1;
    }

    public void Analizar(String texto) {
        String lexema = "";
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            int tipo = getTipoCaracter(c);
            if (tipo != 3) {
                lexema = lexema + c;
            }
            if (tipo == 3) {
                System.out.println("ES UN ESPACIO HAY QUE REINICIAR EL AUTOMATA");
                reiniciarAutomata(lexema);
                lexema = "";
            } else if (tipo == -1) {
                System.out.println("ERROR EL CARACTER NO ESTA EN EL ALFABETO");
                estadoActual = -1;
                reiniciarAutomata(lexema);
                lexema = "";
            } else {

                estadoAnterior = estadoActual;
                estadoActual = transiciones[estadoActual][tipo];
                if (estadoActual == -1) {
                    System.out.println("ERROR");
                    reiniciarAutomata(lexema);
                    lexema = "";
                } else {
                    System.out.println(
                            "me movi del estado: " + estadoAnterior + " al estado: " + estadoActual + " con un: "
                                    + c);
                }

            }
        }
        reiniciarAutomata(lexema);
        ImprimirTokens();
        tokens = new ArrayList<>();
    }

    public void reiniciarAutomata(String lexema) {
        System.out.println("LEXEMA: " + lexema);
        if (!lexema.equals("")) {
            Token nuevoToken = new Token(lexema, getTipoToken(estadoActual, lexema));
            tokens.add(nuevoToken);
            estadoActual = 0;
            estadoAnterior = 0;
        }

    }

    public void ImprimirTokens() {
        System.out.println("TOKENS ENCONTRADOS");
        for (Token i : tokens) {
            System.out.println("Lexema: " + i.getLexema() + " tipo Token: " + i.getTipoToken());
        }
    }

    public TipoToken getTipoToken(int estadoActual, String lexema) {
        switch (estadoActual) {
            case 1:
                return TipoToken.ENTERO;
            case 3:
                return TipoToken.DECIMAL;
            case 4:
                return verificarPalabraReservada(lexema);
            case -1:
                return TipoToken.ERROR;
            default:
                break;
        }
        return TipoToken.ERROR;
    }

    public TipoToken verificarPalabraReservada(String lexema) {
        for (String tmp : palabrasReservadas) {
            if (lexema.equals(tmp))
                return TipoToken.PALABRARESERVADA;
        }
        return TipoToken.IDENTIFICADOR;
    }

    // numeros = 0
    // letras = 1
    // punto = 2
    // Espacio = 3
    public int getTipoCaracter(char tmp) {
        int valor = -1;
        if (Character.isDigit(tmp))
            return 0;
        if (Character.isLetter(tmp))
            return 1;
        if (tmp == '.')
            return 2;
        if (Character.isWhitespace(tmp))
            return 3;
        return valor;

    }

}
