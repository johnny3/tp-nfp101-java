package Compte;

import Personne.Personne;

public interface CompteInterface {

    public String getNumero();

    public float getSolde();

    public void setNumero(String numero);

    public void setSolde(float solde);

    public void setProprietaire(Personne proprietaire);
}
