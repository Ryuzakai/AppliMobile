package com.example.a2i_planning.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.a2i_planning.R;
import com.example.a2i_planning.User.User;
import com.example.a2i_planning.Utiles;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentCalendrier extends Fragment implements View.OnClickListener {
    private CalendarView vuecalendar;
    private String dateselected;

    User currentUser = new User();
    private Toolbar maToolBar;
    private FloatingActionButton btnAjout;
    Bundle myBdl_send = new Bundle();
    Bundle myBdl_receive = new Bundle();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_calendar,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        btnAjout = getActivity().findViewById(R.id.btn_addnote_calVie);
        btnAjout.setOnClickListener(this);
        myBdl_receive = this.getArguments();
        if(myBdl_receive!=null) {
            currentUser = myBdl_receive.getParcelable("user");

            Utiles.alerter(getActivity(), "Le pseudo est : " + currentUser.getMail());
        }
        maToolBar = getActivity().findViewById(R.id.toolbar);
        maToolBar.setTitle("Bonjour " + currentUser.getPrenom());

        vuecalendar = getActivity().findViewById(R.id.calendarView);
        vuecalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayofMonth){
                Fragment toJournalFrag;
                dateselected = dayofMonth+"/"+month+"/"+year;
                Utiles.alerter(getActivity(), "la date est  : "+dateselected);


                myBdl_send.putString("ladate", dateselected);
                myBdl_send.putParcelable("user", currentUser);
                toJournalFrag= Utiles.gotoFragmentwithBdl(FragmentJournal.class, myBdl_send);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, toJournalFrag).addToBackStack(null).commit();


            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_addnote_calVie:
                myBdl_send.putParcelable("user", currentUser);
                Fragment toCreerEspace;
                toCreerEspace = Utiles.gotoFragmentwithBdl(FragmentCreerEspace.class, myBdl_send);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, toCreerEspace).addToBackStack(null).commit();
        }
    }
}
