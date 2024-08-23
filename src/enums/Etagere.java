package enums;

public enum Etagere {

    UN("Un"),
    DEUX("Deux"),
    TROIS("Trois"),
    QUATRE("Quatre"),
    CINQ("Cinq");
    private final String afficheNom;

    Etagere(String afficheNom) {
        this.afficheNom = afficheNom;
    }

    @Override
    public String toString() {
        return afficheNom;
    }
}
