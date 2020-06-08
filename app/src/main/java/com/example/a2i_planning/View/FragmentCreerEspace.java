package com.example.a2i_planning;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FragmentCreerEspace extends Fragment implements View.OnClickListener {

    EditText edtNomEspace;
    Button btnAjoutEspace;
    Button btnAjoutRubrique;
    TextView rub1,rub2,rub3,rub4,titleRub1,titlerub2,titleRub3,titleRub4;
    View it;
    int num=0;
    String nomEspace;
    Bundle myBdl_send = new Bundle();
    Bundle myBdl_receive = new Bundle();
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_creer_espace,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        edtNomEspace=getActivity().findViewById(R.id.edt_nom_espace);
        edtNomEspace.setOnClickListener(this);
        btnAjoutEspace=getActivity().findViewById(R.id.btn_creer_espace);
        btnAjoutEspace.setOnClickListener(this);
        btnAjoutRubrique=getActivity().findViewById(R.id.btn_ajout_rubrique);
        btnAjoutRubrique.setOnClickListener(this);
        it=getActivity().findViewById(R.id.list_espaces);
        rub1 = getActivity().findViewById(R.id.tv_rub1);
        rub2 = getActivity().findViewById(R.id.tv_rub2);
        rub3 = getActivity().findViewById(R.id.tv_rub3);
        rub4 = getActivity().findViewById(R.id.tv_rub4);
        titleRub1 =getActivity().findViewById(R.id.tv_rub1_title);
        titlerub2 =getActivity().findViewById(R.id.tv_rub2_title);
        titleRub3 =getActivity().findViewById(R.id.tv_rub3_title);
        titleRub4 =getActivity().findViewById(R.id.tv_rub4_title);
        myBdl_receive = this.getArguments();
        if(myBdl_receive!=null){
            System.out.println(myBdl_receive);
            if(myBdl_receive.getBoolean("rub_text")){
                rub1.setVisibility(View.VISIBLE);
                titleRub1.setText(myBdl_receive.getString("titleText"));
                titleRub1.setVisibility(View.VISIBLE);
            }else{
                rub1.setVisibility(View.INVISIBLE);
                titleRub1.setVisibility(View.INVISIBLE);
            }
            if(myBdl_receive.getBoolean("rub_num")){
                rub2.setVisibility(View.VISIBLE);
                titlerub2.setText(myBdl_receive.getString("titleNum"));
                titlerub2.setVisibility(View.VISIBLE);
            }else{
                rub2.setVisibility(View.INVISIBLE);
                titlerub2.setVisibility(View.INVISIBLE);
            }
            if(myBdl_receive.getBoolean("rub_time")){
                rub3.setVisibility(View.VISIBLE);
                titleRub3.setText(myBdl_receive.getString("titleTime"));
                titleRub3.setVisibility(View.VISIBLE);
            }else{
                rub3.setVisibility(View.INVISIBLE);
                titleRub3.setVisibility(View.INVISIBLE);
            }
            if(myBdl_receive.getBoolean("rub_spinner")){
                rub4.setVisibility(View.VISIBLE);
                titleRub4.setText(myBdl_receive.getString("titleSpinner"));
                titleRub4.setVisibility(View.VISIBLE);
            }else{
                rub4.setVisibility(View.INVISIBLE);
                titleRub4.setVisibility(View.INVISIBLE);
            }

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_creer_espace:
                num=num+1;
                nomEspace = edtNomEspace.getText().toString();
                if (nomEspace!=null){

                    String nomid="espace"+num;


                    FileOutputStream fileos=null;
                    try {
                        fileos = getContext().openFileOutput("monfichier",Context.MODE_APPEND);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    XmlSerializer serializer = Xml.newSerializer() ;

                        try {
                            serializer.setOutput(fileos, "UTF_8");
                            serializer.startDocument(null, Boolean.valueOf(true));
                            //serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indentoutput", true);
                            serializer.startTag(null, "monxml");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    try {
                        serializer.startTag(null,"etape");
                        serializer.attribute(null,"id",nomid);
                        serializer.endTag(null,"etape");
                        serializer.endDocument();;
                        serializer.flush();
                        fileos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    NavigationView nvView = getActivity().findViewById(R.id.nvView);

                    Menu menu =  nvView.getMenu();
                    Menu submenu = menu.findItem(R.id.Espaces_item).getSubMenu();

                    submenu.add(R.id.list_espaces, R.id.espace1,Menu.NONE,nomEspace);

                    //nvView.invalidate();


                    Fragment nextfragment = Utiles.gotoFragment(FragmentCalendrier.class);
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.flContent, nextfragment).addToBackStack(null).commit();

                }break;
            case R.id.btn_ajout_rubrique:
                nomEspace = edtNomEspace.getText().toString();
                if (nomEspace.isEmpty()){
                    Utiles.alerter(getActivity(), "Donner un nom à l'espace");
                    break;
                }
                myBdl_send.putString("nomEspace", nomEspace);
                Fragment nextfragment2 = Utiles.gotoFragmentwithBdl(FragmentNewRubrique.class,myBdl_send);
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent,nextfragment2).addToBackStack(null).commit();


        }
    }
}
