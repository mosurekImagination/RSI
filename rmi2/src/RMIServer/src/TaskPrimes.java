/**
 * AUTOR: PIOTR CHOLEWA
 */
package tomek;

import java.util.Vector;
/**
 * Klasa wyliczająca liczby pierwsze z podanego zakresu
 */
public class TaskPrimes implements tomek.Task {
    /**
     * Funkcja uruchamiająca dzialanie klasy
     * @param args Vector z danymi - wartości od, do wyliczania liczb pierwszzych (int)
     * @return Vector z liczbami pierwszymi
     */
    @Override
    public Object compute(Object args) {
        int from = ((Vector<Integer>) args).get(0);
        int to = ((Vector<Integer>) args).get(1);

        Vector<Integer> v = new Vector<>();

        for (; from < to; from++) {
            if (isPrime(from))
                v.add(from);
        }

        return v;
    }

    /**
     * Funkcja sprawdzajaca czy dana liczba jest liczbą pierwszą
     * @param n liczba do sprawdzenia
     * @return czy jest l. pierwszą
     */
    public boolean isPrime(long n) {
        if(n > 2 && (n & 1) == 0)
            return false;
        for(int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }
}
