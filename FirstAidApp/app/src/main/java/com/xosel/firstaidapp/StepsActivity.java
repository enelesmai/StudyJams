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
import android.widget.EditText;
import android.widget.LinearLayout;

public class StepsActivity extends AppCompatActivity {

    private SettingsActivity settings = new SettingsActivity();
    private boolean flagMostradoAyuda = false;
    private boolean flagMostradoEvaluacionAccidentado = false;
    private int currentStep = 0;
    private String numberPhone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
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
            setContentView(R.layout.configura_numero);
            viewNumBerPhone();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void init(){
        MyApp.setContext(getApplicationContext());

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

    //region SET_PHONE_NUMBER
    public  void savePhoneNumber(View view){
        EditText phone_number_edit = (EditText) findViewById(R.id.phone_number_edit);
        numberPhone = phone_number_edit.getText().toString();
        settings.saveNumberFamiliar(numberPhone);
        verActividadPrincipal(view);
    }

    public void viewNumBerPhone(){
        EditText phone_number_edit = (EditText) findViewById(R.id.phone_number_edit);
        numberPhone = settings.getNumberFamiliar();
        if(numberPhone != null && !numberPhone.isEmpty()){
            phone_number_edit.setText(numberPhone);
        }
    }
    //region

    //region OPCIONES_MENU
    public void mostrarEvaluar(View view){
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

    public void mostrarMenuEvaluacionAccidentado(View view){
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
    //region

    //region ACCIONES_NAVEGACION
    public void verActividadPrincipal(View view){
        currentStep = 0;
        verActividad(view);
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
                init();
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
                sendMessageMuyPronto(view);
                break;
        }
    }
    //region


    //region FEATURES
    public void llamarCruzRoja(View view){
        llamar("065");
    }

    public void llamarFamiliar(View view){
        numberPhone = settings.getNumberFamiliar();
        if(!numberPhone.isEmpty()){
            llamar(numberPhone);
        }else {
            sendMessage("NO hay teléfono de familiar configurado.", view);
        }
    }

    public void llamar(String tel){
        try {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));
        }catch (SecurityException se){
            se.printStackTrace();
            //sendMessage(se.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            //sendMessage(e.getMessage());
        }
    }

    public void enviarAlerta(View view){
        sendMessageMuyPronto(view);
    }

    public void sendMessageMuyPronto(View view){
        Snackbar.make(view, "Muy pronto!! :D", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void sendMessage(String message, View view){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    //

}
