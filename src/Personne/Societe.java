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
    
    @Override
    public String getContact() {
        return this.adresse;
    }
    
    @Override
    public void setIdentifiant(Object identifiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setContact(Object contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        return "société " + this.getIdentifiant() + ", basée à " + this.getContact();
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
