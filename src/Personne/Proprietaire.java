package Personne;

public abstract class Proprietaire {
    
    private String nom;

    public Proprietaire(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
