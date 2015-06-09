package Ihm;

import java.io.*;
import java.util.Scanner;

public class IhmTextCompte {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static String lireString() {
        String tmp = "";
        try {
            tmp = in.readLine();
        } catch (IOException e) {
            exceptionHandler(e);
        }
        return tmp;
    }

    public static int lireInt() {
        int x = 0;
        try {
            x = Integer.parseInt(lireString());
        } catch (NumberFormatException e) {
            exceptionHandler(e);
        }
        return x;
    }

    public static boolean lireBoolean() {
        boolean b = true;
        try {
            b = Boolean.valueOf(lireString()).booleanValue();
        } catch (NumberFormatException e) {
            exceptionHandler(e);
        }
        return b;
    }

    public static double lireDouble() {
        double x = 0.0;
        try {
            x = Double.valueOf(lireString()).doubleValue();
        } catch (NumberFormatException e) {
            exceptionHandler(e);
        }
        return x;
    }

    public static char lireChar() {
        String tmp = lireString();
        if (tmp.length() == 0) {
            return '\n';
        } else {
            return tmp.charAt(0);
        }
    }

    public float lireFloat() {
        Scanner entree = new Scanner(System.in);
        return entree.nextFloat();
    }

    protected static void exceptionHandler(Exception ex) {
        IhmTextException err = new IhmTextException(ex);
        throw err;
    }
}

class IhmTextException extends RuntimeException {

    Exception ex;

    IhmTextException(Exception e) {
        ex = e;
    }
}
