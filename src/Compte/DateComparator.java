package Compte;

import Operations.Operation;
import java.util.Comparator;

class DateComparator implements Comparator<Operation> {

    @Override
    public int compare(Operation op1, Operation op2) {
        if (op2.getDate().before(op1.getDate())) {
            return 1;
        } else {
            return -1;
        }
    }
}
