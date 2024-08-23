
package Modeles;

import java.util.Date;

public class Stock {
    private int stockID;
    private int dossier;
    private String couleur;
    private String etagere;
    private Date date;

    public Stock() {
    }

    public Stock(int stockID, int dossier, String couleur, String etagere, Date date) {
        this.stockID = stockID;
        this.dossier = dossier;
        this.couleur = couleur;
        this.etagere = etagere;
        this.date = date;
    }

    public Stock(int dossier, String couleur, String etagere, Date date) {
        this.dossier = dossier;
        this.couleur = couleur;
        this.etagere = etagere;
        this.date = date;
    }



    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getDossier() {
        return dossier;
    }

    public void setDossier(int dossier) {
        this.dossier = dossier;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getEtagere() {
        return etagere;
    }

    public void setEtagere(String etagere) {
        this.etagere = etagere;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
