package Personne;

public abstract class ProprietaireNom implements ProprietaireInterface {

    private String nom;

    public ProprietaireNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
