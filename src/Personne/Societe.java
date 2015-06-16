package Personne;

import java.io.*;

public class Societe implements ProprietaireInterface<Long, String>, Serializable {

    private String adresse;
    private String nom;
    private long siret;

    public Societe(String nom, String adresse, Long siret) {
        this.nom = nom;
        this.adresse = adresse;
        this.siret = siret;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getSiret() {
        return siret;
    }

    public void setSiret(int siret) {
        this.siret = siret;
    }

    @Override
    public Long getIdentifiant() {
        return this.siret;
    }

    @Override
    public String getContact() {
        return this.adresse;
    }

    @Override
    public String toString() {
        return "société " + this.getIdentifiant() + ", basée à " + this.getContact();
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
