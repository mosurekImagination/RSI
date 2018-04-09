package RMIServer.src;

import tomek.Task;

import java.math.BigDecimal;
import java.util.Vector;

public class CombinePiTask implements Task {
    /**
     * Funkcja uruchamiająca dzialanie klasy
     * @param args Vector z danymi - wartości od, do wyliczania ilości uderzeń
     * @return ilość uderzeń
     */
    @Override
    public Object compute(Object args) {
        int hits = ((Vector<Integer>) args).get(0); //Tu trzebaby przekazać zsumowany result
        int numberOfThrows = ((Vector<Integer>) args).get(1);
        float pi = getPi(hits,numberOfThrows);
        return pi;
    }

    /**
     * Funkcja licząca ilość uderzeń w danym przedziale
     * @param total_hits ilość uderzeń
     * @param numberOfThrows wielkość przedziału
     * @return ilość uderzeń
     */
    private float getPi(int total_hits, int numberOfThrows) {
        float s = (float) total_hits / numberOfThrows;
        float calculatedPi = round(4*s,6);
        return calculatedPi;
    }


    /**
     * Metoda wyliczająca zadaną liczbę float z zadaną dokładnością
     * @param d - liczba do zwrócenia
     * @param decimalPlace - dokładność
     * @return float z zadaną dokładnością
     */
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
