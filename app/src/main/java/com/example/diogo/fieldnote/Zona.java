package com.example.diogo.fieldnote;

import java.util.ArrayList;
import java.util.List;

public class Zona {

    public String nomezona;
    public String area;
    public String fitossanidade;
    public String localização;
    public String modoprodução;
    public String solo;



    public Zona(){}

    public Zona(String nomezona, String area, String fitossanidade, String localizacao, String modoProd, String solo) {
        this.nomezona = nomezona;
        this.area = area;
        this.fitossanidade = fitossanidade;
        this.localização = localizacao;
        this.modoprodução = modoProd;
        this.solo = solo;
    }

    public String getNomezona() {
        return nomezona;
    }

    public void setNomezona(String nomezona) {
        this.nomezona = nomezona;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFitossanidade() {
        return fitossanidade;
    }

    public void setFitossanidade(String fitossanidade) {
        this.fitossanidade = fitossanidade;
    }

    public String getLocalização() {
        return localização;
    }

    public void setLocalização(String localização) {
        this.localização = localização;
    }

    public String getModoprodução() {
        return modoprodução;
    }

    public void setModoprodução(String modoprodução) {
        this.modoprodução = modoprodução;
    }

    public String getSolo() {
        return solo;
    }

    public void setSolo(String solo) {
        this.solo = solo;
    }

}
