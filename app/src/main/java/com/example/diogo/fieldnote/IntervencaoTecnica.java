package com.example.diogo.fieldnote;

/**
 * Created by SarkTime on 06/07/2016.
 */
public class IntervencaoTecnica {

    private String data,zona, motivo1, quantificadorJustificacao,praga,quantificadorRisco,tipo,equipamento,debito,fertilizante,
                    adubo,especies,meio,quantificadorTratamento,colheita,quantificadorProducao,operador,area;

    public IntervencaoTecnica(){}

    public IntervencaoTecnica(String data,String zona,String motivo1,String quantificadorJustificacao,String praga, String quantificadorRisco, String tipo,String equipamento,
                              String debito, String fertilizante, String adubo, String especies, String meio,String quantificadorTratamento,String colheita,
                              String quantificadorProducao,String operador,String area)
    {

        this.motivo1=motivo1;
        this.quantificadorJustificacao=quantificadorJustificacao;
        this.praga=praga;
        this.quantificadorRisco=quantificadorRisco;
        this.tipo=tipo;
        this.equipamento=equipamento;
        this.debito=debito;
        this.fertilizante=fertilizante;
        this.adubo=adubo;
        this.especies=especies;
        this.meio=meio;
        this.quantificadorTratamento=quantificadorTratamento;
        this.colheita=colheita;
        this.quantificadorProducao=quantificadorProducao;
        this.operador=operador;
        this.area=area;
        this.data=data;
        this.zona=zona;

    }

    public String getMotivo1()
    {
        return motivo1;
    }
    public String getQuantificadorJustificacao()
    {
        return quantificadorJustificacao;
    }
    public String getPraga()
    {
        return praga;
    }
    public String getQuantificadorRisco()
    {
        return quantificadorRisco;
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
    public String getQuantificadorTratamento()
    {
        return quantificadorTratamento;
    }
    public String getColheita() { return colheita;    }
    public String getQuantificadorProducao(){
        return quantificadorProducao;
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