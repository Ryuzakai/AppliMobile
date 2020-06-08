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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class FragmentConnexion extends Fragment implements View.OnClickListener {

    private EditText edtLogin;
    private EditText edtMdp;
    private Button btnConnexion;
    private Button btnInscrire;
    ArrayList<User> utilisateurs = new ArrayList<User>();


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

            edtLogin.setText(myBdl_receive.getString("login"));
            edtMdp.setText(myBdl_receive.getString("mdp"));
        }

        File dossier = new File(getActivity().getFilesDir() + "/users");
        File newxmlfile = new File(dossier.getPath() + "/users.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(newxmlfile);
            doc.getDocumentElement().normalize();
            String root = doc.getDocumentElement().getNodeName();
            System.out.println("Exemple root : " + root);

            NodeList items = doc.getChildNodes();

            System.out.println("Exemple items : " + items.getLength());
            for (int i = 0; i < items.getLength(); i++) {
                Node item = items.item(i);
                System.out.println("Node item name : " + item.getNodeName());
                Element eElement = (Element) item;
                User currentUser = new User();
                currentUser.setId(eElement.getElementsByTagName("id").item(i).getTextContent());
                currentUser.setMail(eElement.getElementsByTagName("mail").item(i).getTextContent());
                currentUser.setMdp(eElement.getElementsByTagName("mdp").item(i).getTextContent());
                currentUser.setNom(eElement.getElementsByTagName("nom").item(i).getTextContent());
                currentUser.setPrenom(eElement.getElementsByTagName("prenom").item(i).getTextContent());
                currentUser.setDdn(eElement.getElementsByTagName("dateN").item(i).getTextContent());
                utilisateurs.add(currentUser);
                System.out.println("Récupération d'un user item : " + i + " : " + utilisateurs);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
                for (User user : utilisateurs) {
                    if (user.getMail().equals(login) && user.getMdp().equals(mdp)) {
                        myBdl_send.putParcelable("user", user);
                        Fragment toCalendrierFrag = Utiles.gotoFragmentwithBdl(FragmentCalendrier.class, myBdl_send);


                        // Insert the fragment by replacing any existing fragment
                        fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, toCalendrierFrag).commit();
                    }
                }


                // si non vide changer d'activité
                //pour afficher la seconde activité
                //en passant la valeur du nom saisi



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
