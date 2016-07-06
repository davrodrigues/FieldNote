package com.example.diogo.fieldnote;

/**
 * Created by São on 03-07-2016.
 */
public class Campanha {
    private String Cultura;
    private String Parcela;
    private String Data_de_Plantacao;

    public Campanha() {}

    public Campanha(String cultura, String data_de_Plantacao, String parcela) {
        this.Cultura = cultura;
        this.Parcela = parcela;
        this.Data_de_Plantacao = data_de_Plantacao;
    }

    public String getCultura() {
        return Cultura;
    }

    public String getParcela() {
        return Parcela;
    }

    public String getData_de_Plantacao() {
        return Data_de_Plantacao;
    }
}
