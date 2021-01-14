package com.example.meteo2;

public class News {
    String auteur;
    String titre;
    String date;
    String contenu;

    public News(String auteur, String titre, String date, String contenu) {
        this.auteur = auteur;
        this.titre = titre;
        this.date = date;
        this.contenu = contenu;
    }

    public News() {
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
