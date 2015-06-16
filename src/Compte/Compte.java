package Compte;

import Exceptions.InvalidDebitException;
import Personne.*;
import Journal.Journal;
import Operations.*;
import java.util.*;
import java.io.*;
import Exceptions.*;

public class Compte implements CompteInterface, Serializable {

    protected String numero;
    protected float solde = 0;
    private int decouvertAutorise = 0;
    private final Journal journal = Journal.getInstance();
    protected ProprietaireInterface<?,?> proprietaire;
    private final ArrayList<Operation> historique = new ArrayList();

    public Compte(String numCompte, float solde, ProprietaireInterface<?,?> proprietaire, int decouvertAutorise) {
        this.numero = numCompte;
        this.solde = solde;
        this.decouvertAutorise = decouvertAutorise;
        this.proprietaire = proprietaire;
    }

    public Compte(String numCompte, ProprietaireInterface<?,?> proprietaire, int decouvertAutorise) {
        this.numero = numCompte;
        this.decouvertAutorise = decouvertAutorise;
        this.proprietaire = proprietaire;
    }

    public Compte(String numCompte, ProprietaireInterface<?,?> proprietaire, float solde) {
        this.numero = numCompte;
        this.solde = solde;
        this.proprietaire = proprietaire;
    }

    public Compte(String numCompte, ProprietaireInterface<?,?> proprietaire) {
        this.numero = numCompte;
        this.proprietaire = proprietaire;
    }

    public Compte() {

    }

    @Override
    public String getNumero() {
        return this.numero;
    }

    @Override
    public float getSolde() {
        return this.solde;
    }

    public int getDecouvertAutorise() {
        return this.decouvertAutorise;
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
    public void setProprietaire(ProprietaireInterface proprietaire) {
        this.proprietaire = proprietaire;
    }

    @Override
    public String toString() {
        return "compte numéro: " + this.numero + " de " + this.proprietaire.toString() + ", solde: " + this.solde + ", découvert autorisé: " + this.decouvertAutorise;
    }

    public String getHistoriqueOperations(TypeTri type) {
        String res = "";
        String separator = "\n";

        if (TypeTri.montant == type) {
            Collections.sort(this.historique, new AmountComparator());
        } else {
            Collections.sort(this.historique, new DateComparator());
        }

        for (int i = 0; i < this.historique.size(); i++) {
            if (i == this.historique.size() - 1) {
                separator = "";
            }
            res += this.historique.get(i).toString() + separator;
        }

        return res;
    }

    public boolean isDebitable(float montant) {
        boolean debitable = false;

        if (this.solde + this.decouvertAutorise - montant >= 0) {
            debitable = true;
        }

        return debitable;
    }

    public void debiter(float montant) throws InvalidDebitException {
        if (this.isDebitable(montant)) {
            this.solde -= montant;
            this.addOperation(TypeOperation.debit, montant);
        } else {
            this.journal.logError("Désolé, le compte numero " + this.numero + " ne peut être débité de la somme " + montant + "€ car son solde est de " + this.solde + "€ et son découvert autorisé " + this.decouvertAutorise + "€.");
            throw new InvalidDebitException("Débit impossible");
        }
    }

    public void addOperation(TypeOperation typeOperation, float montant) {
        Operation op = new Operation(typeOperation, montant);
        this.historique.add(op);
    }

    public void crediter(float montant) {
        this.solde += montant;
        this.addOperation(TypeOperation.credit, montant);
    }
}
