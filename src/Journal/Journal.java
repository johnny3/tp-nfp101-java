/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Journal;

import java.io.Serializable;

/**
 *
 * @author john
 */
public class Journal implements Serializable {

    private String logError = "";

    /**
     * Constructeur privé
     */
    private Journal() {
        this.logError = "";
    }

    /**
     * Instance unique pré-initialisée
     */
    private static Journal INSTANCE = null;

    /**
     * Point d'accès pour l'instance unique du singleton
     * @return Journal instance
     */
    public static Journal getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new Journal();
        }
        return INSTANCE;
    }

    public void logError(String log) {
        this.logError += log + "\n";
    }

    public String getLogErrors() {
        return this.logError;
    }
}
