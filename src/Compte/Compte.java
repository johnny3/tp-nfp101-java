package Compte;

import Personne.*;
import Journal.Journal;
import Operations.*;

public class Compte implements CompteInterface {

    protected String numero;
    protected float solde = 0;
    private int decouvertAutorise = 0;
    private final Journal journal = Journal.getInstance();
    protected Proprietaire proprietaire;
    private final Operation historique[] = new Operation[10];
    private int head = 0;
    private int nbOperations = 0;

    public Compte(String numCompte, float solde, Proprietaire proprietaire, int decouvertAutorise) {
        this.numero = numCompte;
        this.solde = solde;
        this.decouvertAutorise = decouvertAutorise;
        this.proprietaire = proprietaire;
    }

    public Compte(String numCompte, Proprietaire proprietaire, int decouvertAutorise) {
        this.numero = numCompte;
        this.decouvertAutorise = decouvertAutorise;
        this.proprietaire = proprietaire;
    }

    public Compte(String numCompte, Proprietaire proprietaire, float solde) {
        this.numero = numCompte;
        this.solde = solde;
        this.proprietaire = proprietaire;
    }

    public Compte(String numCompte, Proprietaire proprietaire) {
        this.numero = numCompte;
        this.proprietaire = proprietaire;
    }

    public Compte() {

    }

    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public float getSolde() {
        return solde;
    }

    public int getDecouvertAutorise() {
        return decouvertAutorise;
    }

    @Override
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setDecouvertAutorise(int decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public void setSolde(float solde) {
        this.solde = solde;
    }

    @Override
    public void setProprietaire(Personne proprietaire) {
        this.proprietaire = proprietaire;
    }

    @Override
    public String toString() {
        return "compte numéro: " + this.numero + " de " + this.proprietaire.toString() + ", solde: " + this.solde + ", découvert autorisé: " + this.decouvertAutorise;
    }

    public boolean virer(float montant, Compte compte) {
        boolean virementFait = false;
        if (this.isDebitable(montant)) {
            this.debiter(montant);
            Operation op = new Operation(TypeOperation.debit, montant);
            this.push(op);
            compte.crediter(montant);
            virementFait = true;
        } else {
            journal.logError("Virement échoué. La somme " + montant + "€ ne peut être viré du compte " + this.numero + " vers le compte numero " + compte.numero + " car son solde est de " + this.solde + "€ et son découvert autorisé s'élève à " + this.decouvertAutorise + "€.");
        }

        return virementFait;
    }

    public boolean isDebitable(float montant) {
        boolean debitable = false;

        if (this.solde + this.decouvertAutorise - montant >= 0) {
            debitable = true;
        }

        return debitable;
    }

    public void debiter(float montant) {
        if (this.isDebitable(montant)) {
            this.solde -= montant;
            Operation op = new Operation(TypeOperation.debit, montant);
            this.push(op);
        } else {
            journal.logError("Désolé, le compte numero " + this.numero + " ne peut être débité de la somme " + montant + "€ car son solde est de " + this.solde + "€ et son découvert autorisé " + this.decouvertAutorise + "€.");
        }
    }

    public void crediter(float montant) {
        this.solde += montant;
        Operation op = new Operation(TypeOperation.credit, montant);
        this.push(op);
    }

    private void push(Operation op) {
        this.historique[this.head] = op;
        this.head++;
        this.nbOperations++;

        // si on a atteint 11 opérations, on réinitialise à 10 pour éviter une 
        // erreur de sortie du tableau lors du parcours du tableau dans printOperations
        if (this.nbOperations > this.historique.length) {
            this.nbOperations = this.historique.length;
        }
        if (this.head == 10) { // si on a atteint la limite, on réinitialise pour écraser les valeurs du début du tableau
            this.head = 0;
        }
    }

    public String printOperations() {
        String res = "";
        String separator = "\n";

        for (int i = 0; i < this.nbOperations; i++) {
            if (i == this.nbOperations - 1) {
                separator = "";
            }
            res += this.historique[i].toString() + separator;
        }

        return res;
    }
}
