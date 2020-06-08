package com.example.a2i_planning.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.a2i_planning.R;
import com.example.a2i_planning.User.Rubrique;
import com.example.a2i_planning.User.User;
import com.example.a2i_planning.Utiles;

import java.util.ArrayList;

public class FragmentNewRubrique extends Fragment {

    TextView tvLibEspace;
    TextView tvnbChamp;
    EditText edtLibText;
    EditText edtLibNum;
    EditText edtLibTime;
    EditText edtLibSpinner;
    EditText edtnbChamp;
    ConstraintLayout scrViewChamps;
    CheckBox rdBtnText;
    CheckBox rdBtnNum;
    CheckBox rdBtnTime;
    CheckBox rdtBtnSpinner;
    Button btnAjoutRub;
    Bundle myBdl_receive = new Bundle();
    Bundle myBdl_send = new Bundle();
    ArrayList<Rubrique> mesrub = new ArrayList<>();
    User currentUser = new User();
    String nomEspace;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_new_rubrique,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        edtLibText = getActivity().findViewById(R.id.edt_LibText);
        edtLibNum = getActivity().findViewById(R.id.edt_LibNum);
        edtLibTime = getActivity().findViewById(R.id.edt_LibTime);
        edtLibSpinner = getActivity().findViewById(R.id.edt_LibSpinner);
        tvLibEspace = getActivity().findViewById(R.id.tv_choixEspace);
        tvnbChamp = getActivity().findViewById(R.id.tv_nbChamps);
        edtnbChamp = getActivity().findViewById(R.id.edt_nbChamp);
        scrViewChamps = getView().findViewById(R.id.scrView_champSpinner);
        btnAjoutRub = getActivity().findViewById(R.id.btn_ajout_rub);
        myBdl_receive = this.getArguments();

        if(myBdl_receive!=null){
            currentUser = myBdl_receive.getParcelable("user");
            nomEspace = myBdl_receive.getString("nomEspace");
            tvLibEspace.setText(nomEspace);

        }

        rdBtnNum = getActivity().findViewById(R.id.chB_Numeric);
        rdBtnNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    edtLibNum.setVisibility(View.VISIBLE);
                    myBdl_send.putBoolean("rub_num",true);
                }
                else {
                    edtLibNum.setVisibility(View.INVISIBLE);
                    myBdl_send.putBoolean("rub_num", false);
                }
            }
        });
        rdBtnText = getActivity().findViewById(R.id.chB_TextArea);
        rdBtnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    edtLibText.setVisibility(View.VISIBLE);
                    myBdl_send.putBoolean("rub_text",true);
                }
                else {
                    edtLibText.setVisibility(View.INVISIBLE);
                    myBdl_send.putBoolean("rub_text", false);
                }
            }
        });
        rdBtnTime = getActivity().findViewById(R.id.chB_Time);
        rdBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    edtLibTime.setVisibility(View.VISIBLE);
                    myBdl_send.putBoolean("rub_time",true);
                }
                else {
                    edtLibTime.setVisibility(View.INVISIBLE);
                    myBdl_send.putBoolean("rub_time", false);
                }
            }
        });
        rdtBtnSpinner = getActivity().findViewById(R.id.chB_Spinner);
        rdtBtnSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    edtLibSpinner.setVisibility(View.VISIBLE);
                    tvnbChamp.setVisibility(View.VISIBLE);
                    edtnbChamp.setVisibility(View.VISIBLE);
                    scrViewChamps.setVisibility(View.VISIBLE);
                    myBdl_send.putBoolean("rub_spinner",true);
                }
                else {
                    edtLibSpinner.setVisibility(View.INVISIBLE);
                    tvnbChamp.setVisibility(View.INVISIBLE);
                    edtnbChamp.setVisibility(View.INVISIBLE);
                    scrViewChamps.setVisibility(View.INVISIBLE);
                    myBdl_send.putBoolean("rub_spinner",false);
                }
            }
        });

        btnAjoutRub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText, titleNum, titleTime, titleSpinner;
                titleText = edtLibText.getText().toString();
                titleNum = edtLibNum.getText().toString();
                titleTime = edtLibTime.getText().toString();
                titleSpinner = edtLibSpinner.getText().toString();
                myBdl_send.putString("nomEspace", nomEspace);
                myBdl_send.putString("titleText",titleText);
                myBdl_send.putString("titleNum", titleNum);
                myBdl_send.putString("titleTime", titleTime);
                myBdl_send.putString("titleSpinner", titleSpinner);
                myBdl_send.putParcelable("user", currentUser);
                System.out.println("Bundle envoyé à créer espace :" + myBdl_send);
                Fragment toEspaceFrag = Utiles.gotoFragmentwithBdl(FragmentCreerEspace.class,myBdl_send);


                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, toEspaceFrag).addToBackStack(null).commit();
            }
        });

    }




}
