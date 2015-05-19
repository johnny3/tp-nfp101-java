package Personne;

public class Societe extends Proprietaire {

    private String adresse;

    public Societe(String nom) {
        super(nom);
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    @Override
    public String toString(){
        return "société " + this.getNom() + ", basée à " + this.adresse;
    }
}
