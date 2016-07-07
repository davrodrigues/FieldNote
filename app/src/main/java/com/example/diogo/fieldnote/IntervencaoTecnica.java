package com.example.diogo.fieldnote;

/**
 * Created by SarkTime on 06/07/2016.
 */
public class IntervencaoTecnica {

    private String data,zona, motivo1, quantificacao1,praga,quantificacao2,tipo,equipamento,debito,fertilizante,
                    adubo,especies,meio,quantificacao3,colheita,quantificacao4,operador,area;

    public IntervencaoTecnica(){}

    public IntervencaoTecnica(String data,String zona,String motivo1,String quantificacao1,String praga, String quantificacao2, String tipo,String equipamento,
                              String debito, String fertilizante, String adubo, String especies, String meio,String quantificacao3,String colheita,
                              String quantificacao4,String operador,String area)
    {

        this.motivo1=motivo1;
        this.quantificacao1=quantificacao1;
        this.praga=praga;
        this.quantificacao2=quantificacao2;
        this.tipo=tipo;
        this.equipamento=equipamento;
        this.debito=debito;
        this.fertilizante=fertilizante;
        this.adubo=adubo;
        this.especies=especies;
        this.meio=meio;
        this.quantificacao3=quantificacao3;
        this.colheita=colheita;
        this.quantificacao4=quantificacao4;
        this.operador=operador;
        this.area=area;
        this.data=data;
        this.zona=zona;

    }

    public String getMotivo1()
    {
        return motivo1;
    }
    public String getQuantificacao1()
    {
        return quantificacao1;
    }
    public String getPraga()
    {
        return praga;
    }
    public String getQuantificacao2()
    {
        return quantificacao2;
    }
    public String getTipo()
    {
        return tipo;
    }
    public String getEquipamento()
    {
        return equipamento;
    }
    public String getDebito()
    {
        return debito;
    }
    public String getFertilizante()
    {
        return fertilizante;
    }
    public String getAdubo()
    {
        return adubo;
    }
    public String getEspecies()
    {
        return especies;
    }
    public String getMeio()
    {
        return meio;
    }
    public String getData()
    {
        return data;
    }
    public String getZona()
    {
        return zona;
    }
    public String getQuantificacao3()
    {
        return quantificacao3;
    }
    public String getColheita()
    {
        return colheita;
    }
    public String getQuantificacao4(){
        return quantificacao4;
    }
    public String getOperador()
    {
        return operador;
    }
    public String getArea()
    {
        return area;
    }

}