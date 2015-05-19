package Compte;

import Personne.*;

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

    public Compte getCompte(String numCompte) {
        for (int i = 0; i < this.nbComptes; i++) {
            if (this.comptes[i].getNumero().equals(numCompte)) {
                return this.comptes[i];
            }
        }

        return null;
    }

    public int getIndexCompte(String numCompte) {
        int indexCompte = -1;
        for (int i = 0; i < this.nbComptes; i++) {
            if (this.comptes[i].getNumero().equals(numCompte)) {
                indexCompte = i;
                break;
            }
        }

        return indexCompte;
        
    }

    public void majInteretsComptes() {
        for (int i = 0; i < this.nbComptes; i++) {
            if (this.comptes[i] instanceof CompteEpargne) {
                ((CompteEpargne) this.comptes[i]).calculInteret();
            }
        }
    }

    public Compte creerCompte(int typeCompte, String numCompte, int solde, Proprietaire proprietaire, int decouvertAutorise, float tauxInterets) {
        Compte compte = this.compteFactory.create(typeCompte, numCompte, solde, proprietaire, decouvertAutorise, tauxInterets);
        this.comptes[this.nbComptes] = compte;
        this.nbComptes++;

        return compte;
    }

    public void suppressionCompte(String numCompte) {
        Compte tempCompte[] = new Compte[this.nbMaxComptes];
        int indexCompte = this.getIndexCompte(numCompte);

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
