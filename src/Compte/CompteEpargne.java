package Compte;

import Personne.Personne;

public class CompteEpargne extends Compte implements CompteInterface {

    private float tauxInterets = 0.03f;
    private float interet = 0;

    public CompteEpargne(String numCompte, float solde, Personne proprietaire, float tauxInterets) {
        super(numCompte, proprietaire, solde);
        this.tauxInterets = tauxInterets;
    }

    public CompteEpargne(String numCompte, float solde, Personne proprietaire) {
        super(numCompte, proprietaire, solde);
    }

    public CompteEpargne(String numCompte, Personne proprietaire, float tauxInterets) {
        super(numCompte, proprietaire);
        this.tauxInterets = tauxInterets;
    }

    public CompteEpargne(String numCompte, Personne proprietaire) {
        super(numCompte, proprietaire);
    }

    public CompteEpargne() {

    }

    @Override
    public String toString() {
        return "compte épargne numéro: " + this.numero + " de " + this.proprietaire.getPrenom() + " " + this.proprietaire.getNom() + ", solde: " + this.solde + ", intérêt: " + this.interet;
    }

    public float getTauxInterets() {
        return tauxInterets;
    }

    public void setTauxInterets(float tauxInterets) {
        this.tauxInterets = tauxInterets;
    }

    public void calculInteret() {
        this.interet = this.getSolde() * this.tauxInterets;
        this.crediter(this.interet);
    }
}
