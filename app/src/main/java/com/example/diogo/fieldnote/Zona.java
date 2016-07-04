package com.example.diogo.fieldnote;

public class Zona {

    public String nomezona;
    public String campanha;


    public Zona(){}

    public Zona(String campo1, String campo2) {
        this.nomezona = campo1;
        this.campanha = campo2;
    }

    public String getNomezona() {
        return nomezona;
    }

    public void setNomezona(String nomezona) {
        this.nomezona = nomezona;
    }

    public String getCampanha() {
        return campanha;
    }

    public void setCampanha(String campanha) {
        this.campanha = campanha;
    }
}
