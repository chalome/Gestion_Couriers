package enums;

public enum Couleur {

    ROUGE("Rouge"),
    VERTE("Verte"),
    BLEU("Bleu"),
    JAUNE("Jaune"),
    ORANGE("Orange");
    private final String afficheNom;

    Couleur(String afficheNom) {
        this.afficheNom = afficheNom;
    }

    @Override
    public String toString() {
        return afficheNom;
    }

}
