package Compte;

import Personne.*;
import java.util.*;
import Exceptions.*;
import java.io.*;

public class Banque {

    private String nom;
    private final HashMap<String, Compte> comptes = new HashMap();
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
        File f = new File(filename);
        FileWriter fw = new FileWriter(f);
        fw.write(this.toString());
        fw.close();
    }

    public String restituerComptes(String filename) throws FileNotFoundException, IOException {
        String output = "";
        InputStream ips = new FileInputStream(filename);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne;
        while ((ligne = br.readLine()) != null) {
            output += ligne + "\n";
        }
        br.close();

        return output;
    }
}
