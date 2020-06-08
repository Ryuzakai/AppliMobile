package com.example.a2i_planning.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.a2i_planning.R;
import com.example.a2i_planning.User.Espace;
import com.example.a2i_planning.User.Rubrique;
import com.example.a2i_planning.User.User;
import com.example.a2i_planning.Utiles;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

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
    ArrayList<Espace> mesespaces = new ArrayList<>();
    ArrayList<Rubrique> mesrub = new ArrayList<>();
    Espace currentEspace = new Espace();
    User currentUser = new User();


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
            currentUser = myBdl_receive.getParcelable("user");

            edtNomEspace.setText(myBdl_receive.getString("nomEspace"));

            if(myBdl_receive.getBoolean("rub_text")){
                rub1.setVisibility(View.VISIBLE);
                titleRub1.setText(myBdl_receive.getString("titleText"));
                mesrub.add(new Rubrique("1", "text", myBdl_receive.getString("titleText")));

                titleRub1.setVisibility(View.VISIBLE);
            }else{
                rub1.setVisibility(View.INVISIBLE);
                titleRub1.setVisibility(View.INVISIBLE);
            }
            if(myBdl_receive.getBoolean("rub_num")){
                rub2.setVisibility(View.VISIBLE);
                titlerub2.setText(myBdl_receive.getString("titleNum"));
                mesrub.add(new Rubrique("2", "num", myBdl_receive.getString("titleNum")));
                titlerub2.setVisibility(View.VISIBLE);
            }else{
                rub2.setVisibility(View.INVISIBLE);
                titlerub2.setVisibility(View.INVISIBLE);
            }
            if(myBdl_receive.getBoolean("rub_time")){
                rub3.setVisibility(View.VISIBLE);
                titleRub3.setText(myBdl_receive.getString("titleTime"));
                mesrub.add(new Rubrique("3", "time", myBdl_receive.getString("titleTime")));
                titleRub3.setVisibility(View.VISIBLE);
            }else{
                rub3.setVisibility(View.INVISIBLE);
                titleRub3.setVisibility(View.INVISIBLE);
            }
            if(myBdl_receive.getBoolean("rub_spinner")){
                rub4.setVisibility(View.VISIBLE);
                titleRub4.setText(myBdl_receive.getString("titleSpinner"));
                mesrub.add(new Rubrique("4", "spinner", myBdl_receive.getString("titleSpinner")));
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
                    currentEspace.setIdEsp(nomid);
                    currentEspace.setNomEsp(nomEspace);

                    currentEspace.setMesRubriques(mesrub);
                    mesespaces.add(currentEspace);

                    currentUser.setMesEspaces(mesespaces);

                    Utiles util = new Utiles();

                    util.writeEspaceXml(currentUser, currentEspace, this);

                    myBdl_send.putParcelable("user", currentUser);
                    myBdl_send.putParcelable("espace", currentEspace);


                    NavigationView nvView = getActivity().findViewById(R.id.nvView);

                    Menu menu =  nvView.getMenu();
                    Menu submenu = menu.findItem(R.id.Espaces_item).getSubMenu();

                    submenu.add(R.id.list_espaces, R.id.espace1,Menu.NONE,nomEspace);

                    //nvView.invalidate();


                    Fragment nextfragment = Utiles.gotoFragmentwithBdl(FragmentEspace.class, myBdl_send);
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.flContent, nextfragment).commit();

                }break;
            case R.id.btn_ajout_rubrique:
                nomEspace = edtNomEspace.getText().toString();
                if (nomEspace.isEmpty()){
                    Utiles.alerter(getActivity(), "Donner un nom à l'espace");
                    break;
                }
                myBdl_send.putString("nomEspace", nomEspace);
                myBdl_send.putParcelable("user", currentUser);
                Fragment nextfragment2 = Utiles.gotoFragmentwithBdl(FragmentNewRubrique.class,myBdl_send);
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent,nextfragment2).addToBackStack(null).commit();


        }
    }
}
