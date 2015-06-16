package nfp101;

import Personne.*;
import Compte.*;
import Ihm.IhmTextCompte;
import Exceptions.*;
import Operations.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Tp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String numCompte;
        ProprietaireInterface proprietaire;
        int numPersonne;
        int numSociete;
        int solde;
        float tauxInterets = 0f;
        int decouvertAutorise = 0;
        int reponse = -1;
        Compte compte;
        IhmTextCompte ihmTextCompte = new IhmTextCompte();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ProprietaireInterface personne1 = new Personne("nomHomme", "prenomHomme", "homme@cnam.fr", "12/05/1980");
        ProprietaireInterface personne2 = new Personne("nomFemme", "prenomFemme", "femme@cnam.fr", "04/07/1980");
        ProprietaireInterface societe1 = new Societe("nomSociete1", "adresseSociete1", 12345678912345L);
        ProprietaireInterface societe2 = new Societe("nomSociete2", "adresseSociete2", 98765432112345L);
        CompteFactory compteFactory = new CompteFactory();
        Banque banque = new Banque("banque", compteFactory);

        while (reponse != 11) {
            System.out.println("1:affichage des comptes");
            System.out.println("2:création d'un compte");
            System.out.println("3:créditer un compte");
            System.out.println("4:débiter un compte");
            System.out.println("5:virer de l'argent sur un compte");
            System.out.println("6:recherche d'un compte");
            System.out.println("7:suppression d'un compte");
            System.out.println("8:mise à jour des intérêts");
            System.out.println("9:Voir les opérations d'un compte");
            System.out.println("10:Restituer comptes");
            System.out.println("11:fin");

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
                            numSociete = IhmTextCompte.lireInt();

                            if (numSociete == 1) {
                                proprietaire = societe1;
                            } else if (numSociete == 2) {
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

                        try {
                            banque.sauvegarderComptes("comptes.txt");
                        } catch (FileNotFoundException e) {
                            System.out.println("erreur création de fichier " + e.getMessage());
                            System.exit(-1);
                        } catch (IOException exception) {
                            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
                        }

                        System.out.println("Création réussie du " + compte.toString() + "\n");
                    } else {
                        System.out.println("Le compte " + numCompte + " est déjà existant et ne peut être ajouté.\n");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Numéro du compte:");
                    numCompte = IhmTextCompte.lireString();
                    compte = banque.getCompte(numCompte);
                    if (null != compte) {
                        System.out.println("Somme à créditer:");
                        float somme = ihmTextCompte.lireFloat();
                        compte.crediter(somme);
                    } else {
                        System.out.println("Ce compte n'existe pas.\n");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Numéro du compte:");
                    numCompte = IhmTextCompte.lireString();
                    compte = banque.getCompte(numCompte);
                    if (null != compte) {
                        System.out.println("Somme à débiter:");
                        float somme = ihmTextCompte.lireFloat();
                        try {
                            compte.debiter(somme);
                        } catch (InvalidDebitException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Ce compte n'existe pas.\n");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Numéro du compte à débiter:");
                    numCompte = IhmTextCompte.lireString();
                    Compte compteADebiter = banque.getCompte(numCompte);

                    if (null != compteADebiter) {
                        System.out.println("Numéro du compte à créditer:");
                        numCompte = IhmTextCompte.lireString();
                        Compte compteACrediter = banque.getCompte(numCompte);

                        if (null != compteACrediter) {
                            System.out.println("Somme à virer:");
                            float somme = ihmTextCompte.lireFloat();
                            try {
                                compteADebiter.debiter(somme);
                                compteADebiter.addOperation(TypeOperation.debit, somme);
                                compteACrediter.crediter(somme);
                            } catch (InvalidDebitException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Ce compte n'existe pas.\n");
                        }
                    } else {
                        System.out.println("Ce compte n'existe pas.\n");
                    }
                    break;
                }
                case 6: {
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
                case 7: {
                    System.out.println("Numéro du compte à supprimer:");
                    numCompte = IhmTextCompte.lireString();
//                    compte = banque.getCompte(numCompte);

                    try {
                        banque.suppressionCompte(numCompte);
                        System.out.println("Suppression réussie du compte numéro " + numCompte + "\n");
                    } catch (InvalidSuppressionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 8: {
                    banque.majInteretsComptes();
                    System.out.println("Les intérêts de tous les comptes ont été mis à jour.");
                    break;
                }
                case 9: {
                    System.out.println("Numéro du compte:");
                    numCompte = IhmTextCompte.lireString();
                    compte = banque.getCompte(numCompte);

                    if (null != compte) {
                        System.out.println("Tri par date (1) ou montant (2)?");
                        int typeTriChoix = IhmTextCompte.lireInt();
                        TypeTri typeTri;
                        if (1 == typeTriChoix) {
                            typeTri = TypeTri.date;
                        } else if (2 == typeTriChoix) {
                            typeTri = TypeTri.montant;
                        } else {
                            System.out.println("Type incorrect");
                            break;
                        }
                        System.out.println(compte.getHistoriqueOperations(typeTri));
                    } else {
                        System.out.println("Ce compte n'existe pas.\n");
                    }
                    break;
                }
                case 10: {
                    try {
                        banque.restituerComptes("comptes.txt");
                    } catch (ClassNotFoundException e1) {
                        System.out.println(e1.getMessage());
                    } catch (FileNotFoundException e2) {
                        System.out.println("erreur lecture de fichier " + e2.getMessage() + "ou aucun compte créé");
                    } catch (IOException e3) {
                        System.out.println(e3.toString());
                    }
                    break;
                }
                case 11: {
                    System.out.println("A bientôt");
                }

            }
        }
    }
}
