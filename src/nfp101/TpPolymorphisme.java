package nfp101;

import Personne.*;
import Compte.*;
import Ihm.IhmTextCompte;

public class TpPolymorphisme {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String numCompte;
        Proprietaire proprietaire;
        int numPersonne;
        int solde;
        float tauxInterets = 0f;
        int decouvertAutorise = 0;
        int reponse = -1;
        Compte compte;
        IhmTextCompte ihmTextCompte = new IhmTextCompte();
        Proprietaire personne1 = new Personne("nomHomme", "prenomHomme", "homme@cnam.fr", "12/05/1980");
        Proprietaire personne2 = new Personne("nomFemme", "prenomFemme", "femme@cnam.fr", "04/07/1980");
        Societe societe1 = new Societe("nomSociete1", "adresseSociete1");
        Societe societe2 = new Societe("nomSociete2", "adresseSociete2");
        CompteFactory compteFactory = new CompteFactory();
        Banque banque = new Banque("banque", compteFactory);

        while (reponse != 6) {
            System.out.println("1:affichage des comptes");
            System.out.println("2:création d'un compte");
            System.out.println("3:recherche d'un compte");
            System.out.println("4:suppression d'un compte");
            System.out.println("5:mise à jour des intérêts");
            System.out.println("6:fin");

            reponse = IhmTextCompte.lireInt();
            switch (reponse) {
                case 1: {
                    System.out.println(banque.toString());
                    break;
                }
                case 2: {
                    System.out.println("Type de compte: 1 pour classique, 2 pour épargne");
                    int typeCompte = IhmTextCompte.lireInt();

                    if (1 != typeCompte && 2 != typeCompte) {
                        System.out.println("Type de compte inconnu. Veuillez recommencer.");
                        break;
                    }

                    System.out.println("Numéro du compte:");
                    numCompte = IhmTextCompte.lireString();

                    Compte compteSearch = banque.getCompte(numCompte);

                    if (null == compteSearch) {
                        System.out.println("Solde du compte:");
                        solde = IhmTextCompte.lireInt();

                        if (1 == typeCompte) {
                            System.out.println("Découvert autorisé du compte:");
                            decouvertAutorise = IhmTextCompte.lireInt();
                        } else {
                            System.out.println("Taux d'intérêts:");
                            tauxInterets = ihmTextCompte.lireFloat();
                        }

                        System.out.println("Type de propriétaire: personne (1) ou société (2)?");
                        int typeProprietaireChoix = IhmTextCompte.lireInt();

                        if (typeProprietaireChoix == 1) {
                            System.out.println("Propriétaire du compte: personne 1 ou 2?");
                            numPersonne = IhmTextCompte.lireInt();

                            if (numPersonne == 1) {
                                proprietaire = personne1;
                            } else if (numPersonne == 2) {
                                proprietaire = personne2;
                            } else {
                                System.out.println("Personne inconnue. Veuillez recommencer.");
                                break;
                            }
                        } else if (typeProprietaireChoix == 2) {
                            System.out.println("Propriétaire du compte: société 1 ou 2?");
                            numPersonne = IhmTextCompte.lireInt();

                            if (numPersonne == 1) {
                                proprietaire = societe1;
                            } else if (numPersonne == 2) {
                                proprietaire = societe2;
                            } else {
                                System.out.println("Societe inconnue. Veuillez recommencer.");
                                break;
                            }
                        } else {
                            System.out.println("Personne inconnue. Veuillez recommencer.");
                            break;
                        }

                        compte = banque.creerCompte(typeCompte, numCompte, solde, proprietaire, decouvertAutorise, tauxInterets);

                        System.out.println("Création réussie du " + compte.toString() + "\n");
                    } else {
                        System.out.println("Le compte " + numCompte + " est déjà existant et ne peut être ajouté.\n");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Numéro du compte à rechercher:");
                    numCompte = IhmTextCompte.lireString();
                    compte = banque.getCompte(numCompte);

                    if (null != compte) {
                        System.out.println(compte.toString());
                    } else {
                        System.out.println("Ce compte n'existe pas.\n");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Numéro du compte à supprimer:");
                    numCompte = IhmTextCompte.lireString();
                    compte = banque.getCompte(numCompte);

                    if (null != compte) {
                        banque.suppressionCompte(numCompte);
                        System.out.println("Suppression réussie du compte numéro " + numCompte + "\n");
                    } else {
                        System.out.println("Ce compte n'existe pas.\n");
                    }
                    break;
                }
                case 5: {
                    banque.majInteretsComptes();
                    System.out.println("Les intérêts de tous les comptes ont été mis à jour.");
                    break;
                }
                case 6: {
                    System.out.println("A bientôt");
                }

            }
        }
    }
}
