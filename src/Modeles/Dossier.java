
package Modeles;

import java.util.Date;

public class Dossier {
    private int dossierID;
    private String dossierType;
    private Date dateDossier;
    private Date dateRecu;
    private String reference;
    private String objet;
    private String source;
    private String destination;
    private boolean transfere;
    public Dossier() {
    }

    public Dossier(int dossierID, String dossierType, Date dateDossier, Date dateRecu, String reference, String objet, String source, String destination) {
        this.dossierID = dossierID;
        this.dossierType = dossierType;
        this.dateDossier = dateDossier;
        this.dateRecu = dateRecu;
        this.reference = reference;
        this.objet = objet;
        this.source = source;
        this.destination = destination;
    }

    public Dossier(String dossierType, Date dateDossier, Date dateRecu, String reference, String objet, String source, String destination) {
        this.dossierType = dossierType;
        this.dateDossier = dateDossier;
        this.dateRecu = dateRecu;
        this.reference = reference;
        this.objet = objet;
        this.source = source;
        this.destination = destination;
    }

    public Dossier(int dossierID, String dossierType, Date dateDossier, Date dateRecu, String reference, String objet, String source, String destination, boolean transfere) {
        this.dossierID = dossierID;
        this.dossierType = dossierType;
        this.dateDossier = dateDossier;
        this.dateRecu = dateRecu;
        this.reference = reference;
        this.objet = objet;
        this.source = source;
        this.destination = destination;
        this.transfere = transfere;
    }

    public int getDossierID() {
        return dossierID;
    }

    public void setDossierID(int dossierID) {
        this.dossierID = dossierID;
    }

    public String getDossierType() {
        return dossierType;
    }

    public void setDossierType(String dossierType) {
        this.dossierType = dossierType;
    }

    public Date getDateDossier() {
        return dateDossier;
    }

    public void setDateDossier(Date dateDossier) {
        this.dateDossier = dateDossier;
    }

    public Date getDateRecu() {
        return dateRecu;
    }

    public void setDateRecu(Date dateRecu) {
        this.dateRecu = dateRecu;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isTransfere() {
        return transfere;
    }

    public void setTransfere(boolean transfere) {
        this.transfere = transfere;
    }
    
    
}
