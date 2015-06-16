package Compte;

import Personne.*;
import java.util.*;
import Exceptions.*;
import java.io.*;

public class Banque {

    private String nom;
    private HashMap<String, Compte> comptes;
    CompteFactory compteFactory;

    public Banque(String nom, CompteFactory compteFactory) {
        this.nom = nom;
        this.compteFactory = compteFactory;
        this.comptes = new HashMap();
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
        for (Iterator<Compte> i = this.comptes.values().iterator(); i.hasNext();) {
            res += i.next().toString() + "\n";
        }

        return res;
    }

    public Compte getCompte(String numCompte) {
        if (this.comptes.containsKey(numCompte)) {
            return this.comptes.get(numCompte);
        }

        return null;
    }

    public void majInteretsComptes() {
        Compte compte;
        for (Iterator<Compte> i = this.comptes.values().iterator(); i.hasNext();) {
            compte = i.next();
            if (compte instanceof CompteEpargne) {
                ((CompteEpargne) compte).calculInteret();
            }
        }
    }

    public Compte creerCompte(int typeCompte, String numCompte, int solde, ProprietaireInterface proprietaire, int decouvertAutorise, float tauxInterets) {
        Compte compte = this.compteFactory.create(typeCompte, numCompte, solde, proprietaire, decouvertAutorise, tauxInterets);
        this.comptes.put(compte.getNumero(), compte);

        return compte;
    }

    public void suppressionCompte(String numCompte) throws InvalidSuppressionException {
        if (this.comptes.containsKey(numCompte)) {
            this.comptes.remove(numCompte);
        } else {
            throw new InvalidSuppressionException("Ce compte n'existe pas.\n");
        }
    }

    public void sauvegarderComptes(String filename) throws FileNotFoundException, IOException {
        FileOutputStream ficSortie;
        ObjectOutputStream oSortie;
        ficSortie = new FileOutputStream(filename);
        oSortie = new ObjectOutputStream(ficSortie);
        oSortie.writeObject(this.comptes);
        oSortie.close();
    }

    public void restituerComptes(String filename) throws FileNotFoundException, ClassNotFoundException, IOException {
        FileInputStream ficEntree;
        ObjectInputStream oEntree;
        ficEntree = new FileInputStream(filename);
        oEntree = new ObjectInputStream(ficEntree);
        this.comptes = (HashMap<String, Compte>) oEntree.readObject();
    }
}
