package com.example.biblio_tech;

public class Livre {

    String titre;
    String auteur;
    String image;
    int nbp;

    public Livre(String titre, String auteur, String image){
        this.titre=titre;
        this.auteur=auteur;
        this.image=image;
    }
    public Livre(){
        this.titre="";
        this.auteur="";
        this.image="";
    }
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
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
