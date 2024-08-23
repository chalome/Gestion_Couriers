
package Modeles;

import java.sql.Blob;

public class Utilisateur {
    private int userID;
    private String nom;
    private String prenom;
    private String service;
    private String password;
    private String type;
    private Blob image;

    public Utilisateur() {
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Utilisateur(String prenom, String password) {
        this.prenom = prenom;
        this.password = password;
    }

    public Utilisateur(String nom, String prenom, String service, String password, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.service = service;
        this.password = password;
        this.type = type;
    }

    public Utilisateur(int userID, String nom, String prenom, String service, String password, String type) {
        this.userID = userID;
        this.nom = nom;
        this.prenom = prenom;
        this.service = service;
        this.password = password;
        this.type = type;
    }

    public Utilisateur(String nom, String prenom, String service, String password, String type, Blob image) {
        this.nom = nom;
        this.prenom = prenom;
        this.service = service;
        this.password = password;
        this.type = type;
        this.image = image;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  nom + " " + prenom ;
    }
    
}
