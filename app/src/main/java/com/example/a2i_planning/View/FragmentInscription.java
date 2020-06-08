package com.example.a2i_planning.View;

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

import com.example.a2i_planning.R;
import com.example.a2i_planning.User.User;
import com.example.a2i_planning.Utiles;

import java.util.UUID;

public class FragmentInscription extends Fragment implements View.OnClickListener {

    private EditText edtNom;
    private EditText edtPrenom;
    private EditText edtMail;
    private EditText edtDdn;
    private EditText edtMdp;
    private EditText edtConfMdp;
    private Button btnConfIns;
    Bundle myBdl_send = new Bundle();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_inscription, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        edtNom = getActivity().findViewById(R.id.edt_nom);
        edtNom.setOnClickListener(this);
        edtPrenom = getActivity().findViewById(R.id.edt_prenom);
        edtPrenom.setOnClickListener(this);
        edtMail = getActivity().findViewById(R.id.edt_mail);
        edtMail.setOnClickListener(this);
        edtDdn = getActivity().findViewById(R.id.edt_ddn);
        edtMdp = getActivity().findViewById(R.id.edt_mdp);
        edtConfMdp = getActivity().findViewById(R.id.edt_confmdp);
        btnConfIns = getActivity().findViewById(R.id.btn_confIns);
        btnConfIns.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edt_nom:
                Utiles.alerter(getActivity(), "Entrez un nom"); break;
            case R.id.edt_prenom:
                Utiles.alerter(getActivity(),"Entrer un prénom");break;
            case R.id.edt_ddn:
                Utiles.alerter(getActivity(), "Entrer une date de naissance");break;
            case R.id.edt_mail:
                Utiles.alerter(getActivity(), "Entrer une adresse mail");break;
            case R.id.edt_mdp:
                Utiles.alerter(getActivity(), "Entrer un mot de passe");break;
            case R.id.edt_confmdp:
                Utiles.alerter(getActivity(), "Confirmer le mot de passe");break;
            case R.id.btn_confIns:
                String nom = edtNom.getText().toString();
                String prenom = edtPrenom.getText().toString();
                String mail = edtMail.getText().toString();
                String dateN = edtDdn.getText().toString();
                String mdp = edtMdp.getText().toString();
                String mdp2 = edtConfMdp.getText().toString();
                if (nom.isEmpty()||prenom.isEmpty()||mail.isEmpty()||dateN.isEmpty()){
                    Utiles.alerter(getActivity(), "Il manque un champ");
                    return;
                }
                if (mdp.equals(mdp2)){

                    String id = UUID.randomUUID().toString();
                    User newUser = new User(id, nom, prenom, mail, dateN, mdp);
                    System.out.println(newUser.toString());
                    //myBdl_send.putParcelable("user", newUser);
                    myBdl_send.putString("login", newUser.getMail());
                    myBdl_send.putString("mdp", newUser.getMdp());
                    Utiles ut = new Utiles();
                    ut.writeInXmlUser(newUser, this);

                    Fragment toConnexionFrag = Utiles.gotoFragmentwithBdl(FragmentConnexion.class,myBdl_send);


                    // Insert the fragment by replacing any existing fragment
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.flContent, toConnexionFrag).addToBackStack(null).commit();

                    break;
                }else{
                    Utiles.alerter(getActivity(), "Le mot de passe est incorrecte");
                    return;
                }


            default:
        }
    }
}
