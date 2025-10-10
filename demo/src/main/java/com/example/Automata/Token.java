package com.example.Automata;

public class Token {
    private String lexema;
    private TipoToken tipoToken;
    private String tipo;
    private int linea;
    private int columna;
    
    public Token(String lexema, TipoToken tipoToken) {
        this.lexema = lexema;
        this.tipoToken = tipoToken;
    }

    
    public Token(String lexema, String tipoToken, int linea, int columna) {
        this.lexema = lexema;
        this.tipo = tipoToken;
        this.linea = linea;
        this.columna = columna;
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


    public Token() {
    }
    public String getLexema() {
        return lexema;
    }
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    public TipoToken getTipoToken() {
        return tipoToken;
    }
    public void setTipoToken(TipoToken tipoToken) {
        this.tipoToken = tipoToken;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
