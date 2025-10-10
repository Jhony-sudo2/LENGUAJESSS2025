package com.example.Token;

public class Token {
    private TipoToken tipoToken;
    private String Lexema;
    private int linea;
    private int columna;

    

    public Token(TipoToken tipoToken, String lexema, int linea, int columna) {
        this.tipoToken = tipoToken;
        Lexema = lexema;
        this.linea = linea;
        this.columna = columna;
    }

    public void imprimir(){
        System.out.println("Tipo Token: " + tipoToken + " lexema: " + Lexema + " linea: " + linea + " columna: " + columna);
    }

    public TipoToken getTipoToken() {
        return tipoToken;
    }
    public void setTipoToken(TipoToken tipoToken) {
        this.tipoToken = tipoToken;
    }
    public String getLexema() {
        return Lexema;
    }
    public void setLexema(String lexema) {
        Lexema = lexema;
    }
    public int getLinea() {
        return linea;
    }
    public void setLinea(int linea) {
        this.linea = linea;
    }
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }

    
}
