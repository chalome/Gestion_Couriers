
package Modeles;

public class TypeDossier {
    private int typeID;
    private String typeNom;

    public TypeDossier() {
    }

    public TypeDossier(int typeID, String typeNom) {
        this.typeID = typeID;
        this.typeNom = typeNom;
    }

    public TypeDossier(String typeNom) {
        this.typeNom = typeNom;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeNom() {
        return typeNom;
    }

    public void setTypeNom(String typeNom) {
        this.typeNom = typeNom;
    }

    @Override
    public String toString() {
        return typeNom;
    }
    
}
