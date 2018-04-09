/**
 * AUTOR: MARTYNA KUMASZKA I TOMASZ MOSUR
 */
package tomek;

import java.util.Vector;
/**
 * Klasa wyliczająca silnię liczby
 */
public class FactorialTask implements tomek.Task {
    /**
     * Funkcja uruchamiająca dzialanie klasy
     * @param args liczba do wyliczenia
     * @return silnia z podanej liczby
     */
    @Override
    public Object compute(Object args) {
        int number = ((Vector<Integer>) args).get(0);
        return computeFactorial(number);
    }

    /**
     * Funkcja wyliczająca silnię podanej liczby
     * @param n zadana liczba
     * @return silnia podanej liczby
     */
    public int computeFactorial(int n) {
        int factorial = 1;
        for(int i=1; i<=n; i++){
            factorial *= i;
        }
        return factorial;
    }
}
