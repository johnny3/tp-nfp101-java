package Personne;

public class Societe extends Proprietaire {

    private String adresse;

    public Societe(String nom, String adresse) {
        super(nom);
        this.adresse = this.adresse;
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
