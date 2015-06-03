package Personne;

public class Societe extends ProprietaireNom {

    private String adresse;

    public Societe(String nom, String adresse) {
        super(nom);
        this.adresse = adresse;
    }
    
    @Override
    public String getIdentifiant() {
        return this.getNom();
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    @Override
    public String getContact() {
        return this.adresse;
    }
    
    @Override
    public String toString(){
        return "société " + this.getIdentifiant() + ", basée à " + this.getContact();
    }
}
