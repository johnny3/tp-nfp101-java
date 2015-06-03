package Compte;

import Personne.*;

public interface CompteInterface {

    public String getNumero();

    public float getSolde();

    public void setNumero(String numero);

    public void setSolde(float solde);

    public void setProprietaire(ProprietaireInterface proprietaire);
}
