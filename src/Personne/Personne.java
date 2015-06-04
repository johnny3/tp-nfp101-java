package Personne;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Personne extends ProprietaireNom {

    private String prenom;
    private String email;
    private Date dateNaissance; // dd-mm-YYYY
    private Personne conjoint;

    public Personne(String nom, String prenom, String email, String dateNaissance) {
        super(nom);
        this.prenom = prenom;
        this.email = email;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dateNaissance = df.parse(dateNaissance);
        } catch (ParseException e) {
            System.out.println("Erreur date de naissance ");
        }
    }

    @Override
    public String getIdentifiant() {
        return this.prenom + " " + this.getNom();
    }

    @Override
    public String getContact() {
        return this.email;
    }

    @Override
    public void setIdentifiant(Object identifiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setContact(Object contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return this.getIdentifiant() + ", né(e) le " + df.format(this.dateNaissance) + ", email " + this.getContact();
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Personne getConjoint() {
        return this.conjoint;
    }

    public int getAge() {
        // création des deux instances de calendrier
        Calendar dateCourante = Calendar.getInstance();
        Calendar dateNaiss = Calendar.getInstance();

        // on set l'année à l'instance Calendar dateNaissance
        dateNaiss.setTime(this.dateNaissance);

        // on soustrait l'année courante et l'année de naissance
        int nbAnneesDiff = dateCourante.get(Calendar.YEAR) - dateNaiss.get(Calendar.YEAR);

        // on soustrait à l'année actuelle le nombre d'années de différence
        dateCourante.add(Calendar.YEAR, -nbAnneesDiff);

        // si la date de naissance se trouve après dateCourante, 
        // alors l'anniversaire de la personne n'est pas encore passé 
        // et il faut soustraire une année au calcul fait précédemment
        if (dateNaiss.after(dateCourante)) {
            nbAnneesDiff = nbAnneesDiff - 1;
        }
        return nbAnneesDiff;
    }

    public boolean marier(Personne personne) {
        boolean mariageFait = false;
        if (!estMarie() && !personne.estMarie()) {
            this.conjoint = personne;
            personne.conjoint = this;
            mariageFait = true;
        }
        return mariageFait;
    }

    public boolean estMarie() {
        boolean estMarie = false;
        if (null != this.conjoint) {
            estMarie = true;
        }

        return estMarie;
    }

    public boolean divorcer() {
        boolean divorceFait = false;
        if (estMarie() && this.conjoint.estMarie()) {
            this.conjoint.conjoint = null;
            this.conjoint = null;
            divorceFait = true;
        }

        return divorceFait;
    }
}
