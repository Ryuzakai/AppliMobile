package com.example.a2i_planning.User;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Espace implements Parcelable {
    public static final Creator<Espace> CREATOR = new Creator<Espace>() {
        @Override
        public Espace createFromParcel(Parcel in) {
            return new Espace(in);
        }

        @Override
        public Espace[] newArray(int size) {
            return new Espace[size];
        }
    };
    String nomEsp;
    ArrayList<Rubrique> mesRubriques;
    String idEsp;
    String idUser;

    public Espace() {

    }

    public Espace(Parcel in) {
        idEsp = in.readString();
        nomEsp = in.readString();
        idUser = in.readString();
    }

    public String getIdEsp() {
        return idEsp;
    }

    public void setIdEsp(String idEsp) {
        this.idEsp = idEsp;
    }

    public String getNomEsp() {
        return nomEsp;
    }

    public void setNomEsp(String nomEsp) {
        this.nomEsp = nomEsp;
    }

    public ArrayList<Rubrique> getMesRubriques() {
        return mesRubriques;
    }

    public void setMesRubriques(ArrayList<Rubrique> mesRubriques) {
        this.mesRubriques = mesRubriques;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idEsp);
        dest.writeString(nomEsp);
        dest.writeString(idUser);
    }
}


