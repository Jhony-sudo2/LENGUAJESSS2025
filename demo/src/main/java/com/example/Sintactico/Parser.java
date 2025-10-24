package com.example.Sintactico;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;
import com.example.Token.*;;;

public class Parser {
    private final List<Token> tokens;
    private int index = 0;

    // Tabla LL(1)
    private final Map<String, Map<String, List<String>>> tabla = new HashMap<>();

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        inicializarTabla();
    }

    private void inicializarTabla() {
        // LL(1) Table (P, L, A, E, E', T, T', F)

        // P
        put("P", "ID", Arrays.asList("L"));
        put("P", "$", Arrays.asList("L"));

        // L
        put("L", "ID", Arrays.asList("A", "L"));
        put("L", "$", Arrays.asList("ε"));

        // A
        put("A", "ID", Arrays.asList("ID", "=", "E", ";"));

        // E
        for (String t : Arrays.asList("ID", "NUMERO", "DECIMAL"))
            put("E", t, Arrays.asList("T", "E'"));

        // E'
        put("E'", "+", Arrays.asList("+", "T", "E'"));
        put("E'", "-", Arrays.asList("-", "T", "E'"));
        put("E'", ";", Arrays.asList("ε"));

        // T
        for (String t : Arrays.asList("ID", "NUMERO", "DECIMAL"))
            put("T", t, Arrays.asList("F", "T'"));

        // T'
        put("T'", "+", Arrays.asList("ε"));
        put("T'", "-", Arrays.asList("ε"));
        put("T'", "*", Arrays.asList("*", "F", "T'"));
        put("T'", "/", Arrays.asList("/", "F", "T'"));
        put("T'", ";", Arrays.asList("ε"));

        // F
        put("F", "ID", Arrays.asList("ID"));
        put("F", "NUMERO", Arrays.asList("NUMERO"));
        put("F", "DECIMAL", Arrays.asList("DECIMAL"));
    }

    private void put(String noTerminal, String terminal, List<String> produccion) {
        tabla.computeIfAbsent(noTerminal, k -> new HashMap<>()).put(terminal, produccion);
    }

    private String lookahead() {
        if (index < tokens.size())
            return mapToken(tokens.get(index).getTipoToken());
        return "$";
    }

    private String mapToken(TipoToken tipo) {
        return switch (tipo) {
            case ID -> "ID";
            case IGUAL -> "=";
            case NUMERO -> "NUMERO";
            case DECIMAL -> "DECIMAL";
            case PCOMA -> ";";
            case MAS -> "+";
            case MENOS -> "-";
            case POR -> "*";
            case DIV -> "/";
            case EOF -> "$";
            default -> "ERROR";
        };
    }

    public boolean parse() {
        Stack<String> pila = new Stack<>();
        pila.push("$");
        pila.push("P");

        System.out.printf("%-40s %-25s %-20s%n", "PILA", "ENTRADA", "ACCIÓN");
        System.out.println("----------------------------------------------------------------------");

        while (!pila.isEmpty()) {
            String cima = pila.peek();
            String tokenActual = lookahead();

            System.out.printf("%-40s %-25s ", pila.toString(), tokenActual);

            if (esTerminal(cima)) {
                if (cima.equals(tokenActual)) {
                    pila.pop();
                    index++;
                    System.out.println("match(" + tokenActual + ")");
                } else {
                    System.out.println("Error: se esperaba " + cima + " pero llegó " + tokenActual);
                    return false;
                }
            } else {
                Map<String, List<String>> fila = tabla.get(cima);
                if (fila == null || !fila.containsKey(tokenActual)) {
                    System.out.println("Error: no hay regla para [" + cima + ", " + tokenActual + "]");
                    return false;
                }

                List<String> produccion = fila.get(tokenActual);
                pila.pop();

                if (!produccion.get(0).equals("ε")) {
                    ListIterator<String> it = produccion.listIterator(produccion.size());
                    while (it.hasPrevious()) pila.push(it.previous());
                }

                System.out.println(cima + " -> " + String.join(" ", produccion));
            }
        }

        System.out.println("\n Análisis sintáctico exitoso");
        return true;
    }

    private boolean esTerminal(String simbolo) {
        return switch (simbolo) {
            case "ID", "=", "NUMERO", "DECIMAL", "+", "-", "*", "/", ";", "$" -> true;
            default -> false;
        };
    }
}
