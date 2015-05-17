package Compte;

import Personne.Personne;

public class Banque {

    private final int nbMaxComptes = 10;
    private String nom;
    private Compte comptes[] = new Compte[this.nbMaxComptes];
    private int nbComptes = 0;
    CompteFactory compteFactory;

    public Banque(String nom, CompteFactory compteFactory) {
        this.nom = nom;
        this.compteFactory = compteFactory;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.nbComptes; i++) {
            res += this.comptes[i].toString() + "\n";
        }

        return res;
    }

    public int getIndexCompte(String numCompte) {
        int index = -1;
        for (int i = 0; i < this.nbComptes; i++) {
            if (this.comptes[i].getNumero().equals(numCompte)) {
                index = i;
            }
        }

        return index;
    }

    public Compte rechercheCompte(int indexCompte) {
        return this.comptes[indexCompte];
    }

    public void majInteretsComptes() {
        for (int i = 0; i < this.nbComptes; i++) {
            if (this.comptes[i] instanceof CompteEpargne) {
                ((CompteEpargne) this.comptes[i]).calculInteret();
            }
        }
    }

    public Compte creerCompte(int typeCompte, String numCompte, int solde, Personne personne, int decouvertAutorise, float tauxInterets) {
        Compte compte = this.compteFactory.create(typeCompte, numCompte, solde, personne, decouvertAutorise, tauxInterets);
        this.comptes[this.nbComptes] = compte;
        this.nbComptes++;
        
        return compte;
    }

    public void suppressionCompte(int indexCompte) {
        Compte tempCompte[] = new Compte[this.nbMaxComptes];

        for (int i = 0; i < indexCompte; i++) {
            tempCompte[i] = this.comptes[i];
        }

        for (int i = indexCompte; i < this.nbComptes - 1; i++) {
            tempCompte[i] = this.comptes[i + 1];
        }

        this.nbComptes--;
        this.comptes = tempCompte;
    }
}
