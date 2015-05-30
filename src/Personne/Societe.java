package Personne;

public class Societe implements Proprietaire {

    private String nom;
    private String adresse;

    public Societe(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }
    
    @Override
    public String getIdentifiant() {
        return this.nom;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    @Override
    public String getInfoLocalisation() {
        return this.adresse;
    }
    
    @Override
    public String toString(){
        return "société " + this.getIdentifiant() + ", basée à " + this.getInfoLocalisation();
    }
}
