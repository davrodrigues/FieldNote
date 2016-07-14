package com.example.diogo.fieldnote;

/**
 * Created by SarkTime on 12/07/2016.
 */
public class DadosPessoais {

    public String nome,datanasc,telefone,telemovel,email,concelho,freguesia,codpostal,morada;

    public DadosPessoais(){}

    public DadosPessoais(String nome, String datanasc, String telefone, String telemovel, String email, String concelho,String freguesia, String codpostal,String morada){

        this.nome = nome;
        this.datanasc=datanasc;
        this.telefone = telefone;
        this.telemovel=telemovel;
        this.email=email;
        this.concelho=concelho;
        this.freguesia=freguesia;
        this.codpostal=codpostal;
        this.morada=morada;
    }

    public String getNome()
    {
        return nome;
    }

    public String getDatanasc()
    {
        return datanasc;
    }

    public String getCodpostal()
    {
        return codpostal;
    }

    public String getConcelho()
    {
        return concelho;
    }

    public String getEmail()
    {
        return email;
    }

    public String getFreguesia()
    {
        return freguesia;
    }

    public String getMorada()
    {
        return morada;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public String getTelemovel()
    {
        return telemovel;
    }
}
