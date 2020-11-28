package com.example.bafometrov2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    String classificacao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enviarDados = (Button) findViewById(R.id.enviarDados);

        enviarDados.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){

                Intent it = getIntent();

                Double pesoSec = Double.valueOf(it.getStringExtra("peso"));
                String sexoSec = it.getStringExtra("sexo");
                Double coposSec = Double.valueOf(it.getStringExtra("copos"));
                String jejumSec = it.getStringExtra("jejum");

                Double alcoolemia = 0.0;
                Double coeficiente = 0.0;

                if(jejumSec == "s") {
                    if (sexoSec == "h") {
                        coeficiente = 0.7;
                    } else if (sexoSec == "m"){
                        coeficiente = 0.6;
                    }
                }
                else{
                    coeficiente = 1.1;
                }
                alcoolemia = coposSec * 4.8 / (pesoSec * coeficiente);
                if(alcoolemia > 0.2){
                    classificacao = "Taxa de alcoolemia: " + alcoolemia + "\n Classificação: Pessoa Alcoolizada";
                }
                else{
                    classificacao = "Taxa de alcoolemia: " + alcoolemia + "\n Classificação: Pessoa NÃO Alcoolizada";
                }

                Intent it2 = new Intent(getBaseContext(), MainActivity.class);

                it2.putExtra("classificacao", classificacao);

                setResult(10, it2);
                finish();
            }
        });
    }
}