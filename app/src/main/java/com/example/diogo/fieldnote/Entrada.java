package com.example.diogo.fieldnote;

public class Entrada {
    private String Data;
    private String Produto;
    private String Fabricante;
    private String Fornecedor;
    private String Quantidade;
    private String Observações;


    public Entrada(){}

    public Entrada(String data, String Produto ) {
        this.Data = data;
        this.Produto = Produto;
    }

    public String getProduto() {
        return Produto;
    }

    public String getData() {
        return Data;
    }

    public String getFabricante() {
        return Fabricante;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setData(String data) {
        Data = data;
    }

    public void setProduto(String produto) {
        Produto = produto;
    }

    public void setFabricante(String fabricante) {
        Fabricante = fabricante;
    }

    public void setFornecedor(String fornecedor) {
        Fornecedor = fornecedor;
    }

    public String getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(String quantidade) {
        Quantidade = quantidade;
    }

    public String getObservações() {
        return Observações;
    }

    public void setObservações(String observações) {
        Observações = observações;
    }

    public Entrada(String data, String produto, String fabricante, String fornecedor) {
        Data = data;
        Produto = produto;
        Fabricante = fabricante;
        Fornecedor = fornecedor;
    }
}
