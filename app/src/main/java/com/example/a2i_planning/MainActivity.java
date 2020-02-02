package com.example.a2i_planning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;


import android.content.Intent;

import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CAT = "IME";
    private EditText edtLogin;
    private EditText edtMdp;
    private Button btnConnexion;
    private Button btnInscrire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alerter("onCreate");

        Log.i(CAT, "onCreate"); //traces dans le logcat

        edtLogin = findViewById(R.id.edt_login);
        edtLogin.setOnClickListener(this);
        btnConnexion = findViewById(R.id.btn_connexion);
        btnConnexion.setOnClickListener(this);
        btnInscrire = findViewById(R.id.btn_inscrire);
        btnInscrire.setOnClickListener(this);
    }

    public void alerter(String s) {

        Log.i(CAT,s);
        Toast t = Toast.makeText(this,s,Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.action_account : break;
            case R.id.action_settings :
                // affiche de l'activité préférences
                Intent versPrefs = new Intent(this, PreferencesActivity.class);
                startActivity(versPrefs);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //Methode appelée lors du clic sur edtPseudo ou sur le bouton
        //l'argument v dénote une reference vers la vue à l'origine de l'évènement
        //ie la vue cliquée
        switch(v.getId()){
            case R.id.btn_connexion:
                alerter("bouton Connexion");
                //Récupérer le login saisi
                String login = edtLogin.getText().toString();

                if(login.isEmpty()){
                    alerter("Saisir un pseudo");
                    return;
                }


                alerter("pseudo :" +login);

                // si non vide changer d'activité
                //pour afficher la seconde activité
                //en passant la valeur du nom saisi
                Bundle myBdl = new Bundle();
                myBdl.putString("pseudo", login);
                Intent versCalendar = new Intent(this, CalendarActivity.class);
                versCalendar.putExtras(myBdl);
                startActivity(versCalendar);
                break;

            case R.id.edt_login:
                alerter("Saisir un login");
                break;
            case R.id.edt_mdp:
                alerter("Saisir un mot de passe");
            case R.id.btn_inscrire:
                alerter("Bonton Inscription");

                Intent versInscription = new Intent(this, InscriptionActivity.class);
                startActivity(versInscription);
                break;


            default:break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
