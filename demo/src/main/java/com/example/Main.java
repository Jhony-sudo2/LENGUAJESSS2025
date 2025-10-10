package com.example;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import com.example.Automata.Automata;
import com.example.Jflex.Lexer;
import com.example.Token.Token;


public class Main {
    public static void main(String[] args) throws IOException {
        try (Scanner n = new Scanner(System.in)) {
            
            Automata automata = new Automata();
            Lexer lexer;
            while (true) {
                System.out.println("Ingrese el texto");
                String texto = n.nextLine();
                lexer =  new Lexer(new StringReader(texto));
                lexer.yylex();
                for (Token tmp : lexer.getTokens()) {
                    tmp.imprimir();
                }
                //automata.Analizar(texto);
            }
        }
    }
}