package Compte;

import Personne.*;

public class CompteFactory {

    public static final int CLASSIQUE = 1;
    public static final int EPARGNE = 2;

    public Compte create(int typeCompte, String numCompte, int solde, Proprietaire proprietaire, int decouvertAutorise, float tauxInterets) {
        if (CLASSIQUE == typeCompte) {
            return new Compte(numCompte, solde, proprietaire, decouvertAutorise);
        } else {
            return new CompteEpargne(numCompte, solde, proprietaire, tauxInterets);
        }
    }
}
