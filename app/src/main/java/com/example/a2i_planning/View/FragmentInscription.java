package com.example.a2i_planning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FragmentInscription extends Fragment implements View.OnClickListener {

    private EditText edtNom;
    private EditText edtPrenom;
    private EditText edtMail;
    private EditText edtDdn;
    private EditText edtMdp;
    private EditText edtConfMdp;
    private Button btnConfIns;
    int compteurUser = 0;
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
        edtDdn = getActivity().findViewById(R.id.edt_mail);
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







                    UUID id = UUID.randomUUID();
                    User newUser = new User(id, nom, prenom, mail, dateN, mdp);
                    myBdl_send.putParcelable("user", newUser);
                    myBdl_send.putString("login", newUser.getMail());
                    myBdl_send.putString("mdp", newUser.getMdp());

                    File newxmlfile = new File(getActivity().getFilesDir()+"/monfichier.xml");
                    if (!(newxmlfile.exists())){
                        try {
                            newxmlfile.createNewFile();
                        } catch (IOException e) {
                            Log.e("IOException","exception in createNewFile method");
                        }
                    }

                    FileOutputStream fileos = null;
                    try {
                        fileos = new FileOutputStream(newxmlfile);
                    } catch (FileNotFoundException e) {
                        Log.e("FileNotFoundException","can't create FileOutputStream");
                    }
                    XmlSerializer serializer = Xml.newSerializer();
                    try {
                        serializer.setOutput(fileos, "UTF-8");
                        serializer.startDocument(null,Boolean.valueOf(true));
                        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output",true);
                        serializer.startTag(null,"utilisateur");
                        serializer.startTag(null, "id");
                        serializer.text(newUser.getId().toString());
                        serializer.endTag(null,"id");
                        serializer.startTag(null,"nom");
                        serializer.text(newUser.getNom());
                        serializer.endTag(null,"nom");
                        serializer.startTag(null,"prenom");
                        serializer.text(newUser.getPrenom());
                        serializer.endTag(null,"prenom");
                        serializer.startTag(null,"mail");
                        serializer.text(newUser.getMail());
                        serializer.endTag(null,"mail");
                        serializer.startTag(null,"dateN");
                        serializer.text(newUser.getDdn());
                        serializer.endTag(null,"dateN");
                        serializer.startTag(null,"mdp");
                        serializer.text(newUser.getMdp());
                        serializer.endTag(null,"mdp");
                        serializer.endTag(null, "utilisateur");
                        serializer.endDocument();
                        serializer.flush();
                        fileos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
