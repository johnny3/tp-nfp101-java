package Compte;

import Operations.Operation;
import java.util.Comparator;

public class AmountComparator implements Comparator<Operation> {

    @Override
    public int compare(Operation op1, Operation op2) {
        if (op2.getMontant() < op1.getMontant()) {
            return 1;
        } else {
            return -1;
        }
    }
}
