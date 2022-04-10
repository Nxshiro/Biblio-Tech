package com.example.biblio_tech;

import java.util.ArrayList;

public class Livre {

    String titre;
    ArrayList<String> auteur;
    String image;
    int nbp;

    public Livre(String titre, ArrayList<String> auteur, String image){
        this.titre=titre;
        this.auteur=auteur;
        this.image=image;
    }
    public Livre(){
        this.titre="";
        this.auteur= new ArrayList<String>();
        this.image="";
    }
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ArrayList<String> getAuteur() {
        return auteur;
    }

    public void setAuteur(ArrayList<String> auteur) {
        this.auteur = auteur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbp() {
        return nbp;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }
}
