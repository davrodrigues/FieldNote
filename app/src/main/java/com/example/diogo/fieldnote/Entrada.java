package com.example.diogo.fieldnote;

public class Entrada {
    private String Data;
    private String Produto;
    private String Custo;
    private String Fabricante;
    private String Fornecedor;
    private String Quantidade;
    private String Observações;


    public Entrada(){}

    public Entrada(String data, String produto, String custo, String fabricante, String fornecedor, String quantidade, String observações) {
        Data = data;
        Produto = produto;
        Custo = custo;
        Fabricante = fabricante;
        Fornecedor = fornecedor;
        Quantidade = quantidade;
        Observações = observações;
    }

    public String getCusto() {
        return Custo;
    }

    public void setCusto(String custo) {
        Custo = custo;
    }

    public void setFabricante(String fabricante) {
        Fabricante = fabricante;
    }

    public void setFornecedor(String fornecedor) {
        Fornecedor = fornecedor;
    }

    public void setQuantidade(String quantidade) {
        Quantidade = quantidade;
    }

    public void setObservações(String observações) {
        Observações = observações;
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

    public String getQuantidade() {
        return Quantidade;
    }

    public String getObservações() {
        return Observações;
    }




}
