package com.example.a2i_planning;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.UUID;

public class User implements Parcelable {
    UUID idUser;
    String nom;
    String prenom;
    String mail;
    String ddn;
    String mdp;
    ArrayList<Espace> mesEspaces;

    public User(UUID id, String nom, String prenom, String mail, String ddn, String mdp) {
        this.idUser = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.ddn = ddn;
        this.mdp = mdp;
    }

    protected User(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        mail = in.readString();
        ddn = in.readString();
        mdp = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public UUID getId() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getDdn() {
        return ddn;
    }

    public String getMdp() {
        return mdp;
    }

    public void setId(UUID id) {
        this.idUser = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(mail);
        dest.writeString(ddn);
        dest.writeString(mdp);
    }
}
