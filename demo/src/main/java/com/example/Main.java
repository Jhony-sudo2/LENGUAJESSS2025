package com.example;

import java.util.Scanner;

import com.example.Automata.Automata;

public class Main {
    public static void main(String[] args) {
        try (Scanner n = new Scanner(System.in)) {
            Automata automata = new Automata();

            while (true) {
                System.out.println("Ingrese el texto");
                String texto = n.nextLine();
                automata.Analizar(texto);
            }
        }

    }
}