package Compte;

import Personne.Personne;

public class CompteFactory {

    public static final int CLASSIQUE = 1;
    public static final int EPARGNE = 2;

    public Compte create(int typeCompte, String numCompte, int solde, Personne personne, int decouvertAutorise, float tauxInterets) {
        if (CLASSIQUE == typeCompte) {
            return new Compte(numCompte, solde, personne, decouvertAutorise);
        } else {
            return new CompteEpargne(numCompte, solde, personne, tauxInterets);
        }
    }
}
