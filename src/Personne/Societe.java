package Personne;

public class Societe implements ProprietaireInterface<Integer, String> {

    private String adresse;
    private String nom;

    public Societe(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public Integer getIdentifiant() {
        return 123;
    }

    @Override
    public String getContact() {
        return this.adresse;
    }

    @Override
    public String toString() {
        return "société " + this.getIdentifiant() + ", basée à " + this.getContact();
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
