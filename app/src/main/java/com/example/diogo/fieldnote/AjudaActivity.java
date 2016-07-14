package com.example.diogo.fieldnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

public class AjudaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView ajudaString = (TextView) findViewById(R.id.ajudaText);

        ajudaString.setText( Html.fromHtml("  No <b>Menu Principal</b>, pode encontrar os seguintes icones:" + "<br/><br/>" +
                "-  <b>Meus Campos</b> - Neste menu pode aceder às <b>zonas</b>, <b>parcelas</b> e <b>campanhas</b> relativas aos seus terrenos. Pode também fazer novos registos de cada um destes elementos. <br /><br />"+
                "- <b>Intervenções Técnicas</b> - Permite registar intervenções feitas nos seus terrenos, por exemplo a aplicação de um fertilizante, e permite observar observações feitas anteriormente. <br /><br />"+
                "- <b>Registo de Entradas</b> - Aqui pode registar a compra dos produtos que vai utilizar nos seus campos, bem como ver detalhes de compras anteriores.  <br /><br />" +
                "- <b>Estados Fenológicos</b> - Ao clicar neste ícone pode acompanhar a evolução das suas campanhas e registar novos estados fenológicos. Tem acesso a uma cronologia de estados fenológicos anteriormente registados. <br /><br />" +
                "- <b>Observações de Organismos</b> - Permite registar observações de organismos feitas nos seus campos e permite consultar observações antigas. <br /><br />"+
                "- <b>Metereologia</b> - Pode consultar informações sobre a previsão do tempo para a sua localidade."
        ));
    }
}
