package com.example.a2i_planning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentJournal extends Fragment {

    private Spinner monspinner;
    private String ladate = "";
    private TextView tvdate;
    Bundle myBdl_receive = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_journal,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        monspinner = getActivity().findViewById(R.id.spinner_espaces);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monspinner.setAdapter(adapter);
        myBdl_receive = this.getArguments();
        if(myBdl_receive!=null) {
            ladate = myBdl_receive.getString("ladate");


            Utiles.alerter(getActivity(), "date reçue : " + ladate);
            tvdate = getActivity().findViewById(R.id.tv_date);
            tvdate.setText(ladate);
        }
    }


}
