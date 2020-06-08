package com.example.a2i_planning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentConnexion extends Fragment implements View.OnClickListener {

    private EditText edtLogin;
    private EditText edtMdp;
    private Button btnConnexion;
    private Button btnInscrire;


    Bundle myBdl_send = new Bundle();
    FragmentManager fragmentManager;
    Bundle myBdl_receive = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_connexion, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        edtLogin = getActivity().findViewById(R.id.edt_login);
        edtLogin.setOnClickListener(this);
        edtMdp =  getActivity().findViewById(R.id.edt_mdp);
        edtMdp.setOnClickListener(this);
        btnConnexion = getActivity().findViewById(R.id.btn_connexion);
        btnConnexion.setOnClickListener(this);
        btnInscrire = getActivity().findViewById(R.id.btn_inscrire);
        btnInscrire.setOnClickListener(this);
        myBdl_receive = this.getArguments();
        if(myBdl_receive!=null){
            User currentUser = myBdl_receive.getParcelable("user");
            edtLogin.setText(currentUser.getMail());
            edtMdp.setText(currentUser.getMdp());
        }
    }

    @Override
    public void onClick(View v) {
        //Methode appelée lors du clic sur edtPseudo ou sur le bouton
        //l'argument v dénote une reference vers la vue à l'origine de l'évènement
        //ie la vue cliquée
        switch(v.getId()){

            case R.id.btn_connexion:
                Utiles.alerter(getActivity(),"bouton Connexion");
                //Récupérer le login saisi
                String login = edtLogin.getText().toString();
                String mdp = edtMdp.getText().toString();
                if(login.isEmpty()){
                    Utiles.alerter(getActivity(),"Saisir un pseudo");
                    return;
                }
                if (mdp.isEmpty()){
                    Utiles.alerter(getActivity(),"Saisir un mot de passe");
                }

                // si non vide changer d'activité
                //pour afficher la seconde activité
                //en passant la valeur du nom saisi

                myBdl_send.putString("pseudo", login);
                Fragment toCalendrierFrag = Utiles.gotoFragmentwithBdl(FragmentCalendrier.class, myBdl_send);


                // Insert the fragment by replacing any existing fragment
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, toCalendrierFrag).addToBackStack(null).commit();

                break;

            case R.id.edt_login:
                Utiles.alerter(getActivity(), "Saisir un login");
                break;
            case R.id.edt_mdp:
                Utiles.alerter(getActivity(), "Saisir un mot de passe");
                break;
            case R.id.btn_inscrire:
                Utiles.alerter(getActivity(), "Bonton Inscription");

                Fragment toInscriptionFrag = Utiles.gotoFragment(FragmentInscription.class);


                // Insert the fragment by replacing any existing fragment
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, toInscriptionFrag).addToBackStack(null).commit();


                break;


            default:break;

        }
    }




}
