package com.example.a2i_planning;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.a2i_planning.User.Espace;
import com.example.a2i_planning.User.Rubrique;
import com.example.a2i_planning.User.User;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utiles extends FragmentActivity {

    private static final String CAT = "IME";

    public static void alerter(Context c, String s) {
        Log.i(CAT, s);
        Toast t = Toast.makeText(c, s, Toast.LENGTH_SHORT);
        t.show();
    }

    public static Fragment gotoFragment(Class fragmentClass) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) fragmentClass.newInstance();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return fragment;
    }

    public static Fragment gotoFragmentwithBdl(Class fragmentClass, Bundle myBdl_send) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(myBdl_send);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fragment;

    }

    public void writeInXmlUser(User newUser, Fragment fragment) {
        File dossier = new File(fragment.getActivity().getFilesDir() + "/users");
        if (!(dossier.exists())) {
            dossier.mkdir();
        }
        File newxmlfile = new File(dossier.getPath() + "/users.xml");
        if (!(newxmlfile.exists())) {
            try {
                newxmlfile.createNewFile();
            } catch (IOException e) {
                Log.e("IOException", "exception in createNewFile method");
            }
        }

        FileOutputStream fileos = null;
        try {
            fileos = new FileOutputStream(newxmlfile);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "can't create FileOutputStream");
        }
        XmlSerializer serializer = Xml.newSerializer();
        try {
            serializer.setOutput(fileos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.startTag(null, "utilisateur");
            serializer.startTag(null, "id");
            serializer.text(newUser.getId());
            serializer.endTag(null, "id");
            serializer.startTag(null, "nom");
            serializer.text(newUser.getNom());
            serializer.endTag(null, "nom");
            serializer.startTag(null, "prenom");
            serializer.text(newUser.getPrenom());
            serializer.endTag(null, "prenom");
            serializer.startTag(null, "mail");
            serializer.text(newUser.getMail());
            serializer.endTag(null, "mail");
            serializer.startTag(null, "dateN");
            serializer.text(newUser.getDdn());
            serializer.endTag(null, "dateN");
            serializer.startTag(null, "mdp");
            serializer.text(newUser.getMdp());
            serializer.endTag(null, "mdp");
            serializer.endTag(null, "utilisateur");
            serializer.endDocument();
            serializer.flush();
            fileos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeEspaceXml(User user, Espace espace, Fragment fragment) {
        File dossierEsp = new File(fragment.getActivity().getFilesDir() + "/espaces");
        if (!(dossierEsp.exists())) {
            dossierEsp.mkdir();
        }
        File dossierUse = new File(dossierEsp.getPath() + "/" + user.getId());
        if (!(dossierUse.exists())) {
            dossierUse.mkdir();
        }


        File newxmlfile = new File(dossierUse.getPath() + "/" + espace.getIdEsp() + "-" + espace.getNomEsp() + ".xml");
        FileOutputStream fileos = null;
        try {
            fileos = new FileOutputStream(newxmlfile);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "can't create FileOutputStream");
        }
        XmlSerializer serializer = Xml.newSerializer();
        try {
            serializer.setOutput(fileos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.startTag(null, "espace");
            serializer.startTag(null, "id");
            serializer.text(espace.getIdEsp());
            serializer.endTag(null, "id");
            serializer.startTag(null, "nom");
            serializer.text(espace.getNomEsp());
            serializer.endTag(null, "nom");
            int num = 1;
            for (Rubrique marub : espace.getMesRubriques()) {
                System.out.println("get mesrub :" + marub);
                serializer.startTag(null, "rubrique" + num);
                serializer.attribute(null, "type", marub.getType());
                serializer.text(marub.getNom());
                serializer.endTag(null, "rubrique" + num);
                num = num + 1;
            }
            serializer.endDocument();
            serializer.flush();
            fileos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
