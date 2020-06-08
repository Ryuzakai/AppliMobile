package com.example.a2i_planning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentCalendrier extends Fragment   {
    private CalendarView vuecalendar;
    private String dateselected;


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


        myBdl_receive = this.getArguments();
        if(myBdl_receive!=null) {
            String pseudo = myBdl_receive.getString("pseudo");

            Utiles.alerter(getActivity(), "Le pseudo est : " + pseudo);
        }
        vuecalendar = getActivity().findViewById(R.id.calendarView);
        vuecalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayofMonth){
                Fragment toJournalFrag;
                dateselected = dayofMonth+"/"+month+"/"+year;
                Utiles.alerter(getActivity(), "la date est  : "+dateselected);


                myBdl_send.putString("ladate", dateselected);

                toJournalFrag= Utiles.gotoFragmentwithBdl(FragmentJournal.class, myBdl_send);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, toJournalFrag).addToBackStack(null).commit();


            }
        });
    }

}
