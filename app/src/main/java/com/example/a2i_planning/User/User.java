package com.example.a2i_planning.User;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {
    String idUser;
    String nom;
    String prenom;
    String mail;
    String ddn;
    String mdp;
    ArrayList<Espace> mesEspaces;

    public User() {

    }

    public User(String id, String nom, String prenom, String mail, String ddn, String mdp) {
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

    public String getId() {
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

    public void setId(String id) {
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


    public ArrayList<Espace> getMesEspaces() {
        return mesEspaces;
    }

    public void setMesEspaces(ArrayList<Espace> mesEspaces) {
        this.mesEspaces = mesEspaces;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", ddn='" + ddn + '\'' +
                ", mdp='" + mdp + '\'' +
                ", mesEspaces=" + mesEspaces +
                '}';
    }


}
