package com.example.diogo.fieldnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tutorialString = (TextView) findViewById(R.id.tutorialText);

        tutorialString.setText(Html.fromHtml(
                "Neste tutorial iremos mostrar que passos seguir quando começar a usar a nossa aplicação. <br/><br/>" +
        "Comece por criar uma ou mais zonas, clicando em <b>Meus Campos</b> e depois <b>Zonas</b>, clique no botão <b> +</b> no " +
                        " fundo da página e introduza os dados, criando as zonas em que divide os seus terrenos. <br/><br/>" +
        "Há duas de maneiras de criar parcelas, dentro da zona em que pretende criar a parcela ou entrando em <b>Parcelas</b>, " +
                        "dentro de <b>Meus Campos</b>, clicando depois no botão <b> +</b> no fundo da página. <br/><br/>" +
        "Criados os seus terrenos, pode agora começar a registar as suas campanhas, entrando em <b>Campanhas</b>" +
                        " dentro de <b>Meus Campos</b>, e clicando no botão <b> +</b>. <br/><br/>" +
        "Depois de criadas as suas campanhas, pode registar e acompanhar a sua evolução na página <b>Estados Fenológicos</b>, " +
                        "situada no <b>Menu Principal</b>. Para registar um novo estado basta clicar no botão <b> +</b> no fundo da página " +
                        "e escolher a que campanha se refere. <br/><br/>" +
        "Para registar <b>Observações de Organismos</b> ou <b>Intervenções Técnicas</b> efetuadas nos seus campos, " +
                        "clique nas respetivas imagens no <b>Menu Principal</b>. <br/><br/>"+
        "Para registar compras entre em <b>Registo de Entradas</b> no <b>Menu Principal</b> e " +
                        "consulte antigas compras ou adicione uma nova compra no botão no fundo da página. <br/><br/>"+
        "Se quiser consultar informações metereológicas relativas à sua localidade clique em <b>Metereologia</b> no <b>Menu Principal</b>."
        ));

    }
}
