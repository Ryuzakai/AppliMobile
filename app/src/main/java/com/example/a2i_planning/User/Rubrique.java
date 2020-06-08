package com.example.a2i_planning.User;

public class Rubrique {
    String idRub;
    String type;
    String nom;

    public Rubrique(String id, String type, String nom) {
        this.idRub = id;
        this.type = type;
        this.nom = nom;
    }

    public String getIdRub() {
        return idRub;
    }

    public void setIdRub(String idRub) {
        this.idRub = idRub;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
