package com.example.diogo.fieldnote;

import java.util.HashMap;
import java.util.Map;

public class Observacao {
    private String Data;
    private String Parcela;

    public Observacao(){}

    public Observacao(String data, String parcela ) {
        this.Data = data;
        this.Parcela = parcela;
    }

    public String getParcela() {
        return Parcela;
    }

    public String getData() {
        return Data;
    }

}
