package com.example.diogo.fieldnote;

/**
 * Created by São on 02-07-2016.
 */
public class EstadoFenologico {
    private String Campanha;
    private String Estado;
    private String Data;
    private String Parcela;

    public EstadoFenologico() {

    }

    public EstadoFenologico(String cultura, String estado, String data, String parcela) {
        this.Campanha = cultura;
        this.Estado = estado;
        this.Data = data;
        this.Parcela = parcela;
    }

    public String getCampanha() {
        return Campanha;
    }

    public String getEstado() {
        return Estado;
    }

    public String getData() {
        return Data;
    }

    public String getParcela() { return Parcela; }

}
