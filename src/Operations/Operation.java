/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import java.util.*;
import java.text.SimpleDateFormat;

public class Operation {

    private final TypeOperation type;
    private final float montant;
    private final Date date;

    public Operation(TypeOperation type, float montant) {
        this.montant = montant;
        this.type = type;
        this.date = new Date();
    }

    public TypeOperation getType() {
        return type;
    }

    public float getMontant() {
        return montant;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return "type: " + this.type + ", date: " + df.format(this.date) + ", montant: " + this.montant;
    }
}
