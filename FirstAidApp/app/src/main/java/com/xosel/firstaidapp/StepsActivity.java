package com.xosel.firstaidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StepsActivity extends AppCompatActivity {

    private boolean flagMostradoAyuda = false;
    private boolean flagMostradoEvaluacionAccidentado = false;
    private int currentStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamarCruzRoja(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_steps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(null, "Muy pronto!! :D", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void verEvaluar(View view){
        currentStep = 1;
        verActividad(view);
    }

    public void mostrarCuidaPersona(View view){
        currentStep = 3;
        verActividad(view);
    }

    public void mostrarEvaluaConsciencia(View view){
        currentStep = 4;
        verActividad(view);
    }

    public void mostrarEvaluaPulso(View view){
        currentStep = 5;
        verActividad(view);
    }

    public void mostrarEvaluaHemorragia(View view){
        currentStep = 6;
        verActividad(view);
    }

    public void mostrarRCP(View view){
        currentStep = 7;
        verActividad(view);
    }

    public void mostrarMenuAyuda(View view){
        LinearLayout menuAyuda = (LinearLayout) findViewById(
                R.id.menuAyuda);

        if(flagMostradoAyuda){
            menuAyuda.setVisibility(View.GONE);
            flagMostradoAyuda = false;
        }else{
            menuAyuda.setVisibility(View.VISIBLE);
            flagMostradoAyuda=true;
        }
    }

    public void mostrarEvaluacionAccidentado(View view){
        LinearLayout menuAccidentado = (LinearLayout) findViewById(
                R.id.menuEvaluacionAccidentado
        );

        if(flagMostradoEvaluacionAccidentado){
            menuAccidentado.setVisibility(View.GONE);
            flagMostradoEvaluacionAccidentado=false;
        }else{
            menuAccidentado.setVisibility(View.VISIBLE);
            flagMostradoEvaluacionAccidentado=true;
        }
    }

    public void llamarCruzRoja(View view){
        llamar("065");
    }

    public void llamarFamiliar(View view){
        llamar("3121223099");
    }

    public void enviarAlerta(View view){
        Snackbar.make(view, "Muy pronto!! :D", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void llamar(String tel){
        try {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));
        }catch (SecurityException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void verActividadPrincipal(View view){
        setContentView(R.layout.activity_steps);
    }

    public void verActividadAnterior(View view){
        currentStep -= 1;
        verActividad(view);
    }

    public void verActividadSiguiente(View view){
        currentStep += 1;
        verActividad(view);
    }

    public void verActividad(View view){
        switch (currentStep){
            case 0:
                setContentView(R.layout.activity_steps);
                break;
            case 1:
                setContentView(R.layout.activity_evalua_escena);
                break;
            case 2:
                setContentView(R.layout.activity_consigue_ayuda);
                break;
            case 3:
                setContentView(R.layout.activity_cuida_persona);
                break;
            case 4:
                setContentView(R.layout.activity_evalua_consciencia);
                break;
            case 5:
                setContentView(R.layout.activity_evalua_pulso);
                break;
            case 6:
                setContentView(R.layout.evalua_hemorragia);
                break;
            default:
                Snackbar.make(view, "Muy pronto!! :D", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

}
