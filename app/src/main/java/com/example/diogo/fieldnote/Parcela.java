package com.example.diogo.fieldnote;

public class Parcela {
    private String data_plantação;
    private String fertilização;
    private String tipo_rega;
    private String zona;
    private String área;
    private String nome_parcela;




    public Parcela(){}


    public Parcela(String data_plantação, String fertilização, String tipo_rega, String zona, String área, String nome_parcela) {
        this.data_plantação = data_plantação;
        this.fertilização = fertilização;
        this.tipo_rega = tipo_rega;
        this.zona = zona;
        this.área = área;
        this.nome_parcela = nome_parcela;
    }

    public String getData_plantação() {
        return data_plantação;
    }

    public void setData_plantação(String data_plantação) {
        this.data_plantação = data_plantação;
    }

    public String getFertilização() {
        return fertilização;
    }

    public void setFertilização(String fertilização) {
        this.fertilização = fertilização;
    }

    public String getTipo_rega() {
        return tipo_rega;
    }

    public void setTipo_rega(String tipo_rega) {
        this.tipo_rega = tipo_rega;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getÁrea() {
        return área;
    }

    public void setÁrea(String área) {
        this.área = área;
    }

    public String getNome_parcela() {
        return nome_parcela;
    }

    public void setNome_parcela(String nome_parcela) {
        this.nome_parcela = nome_parcela;
    }
}
