/**
 * AUTOR: MARTYNA KUMASZKA I TOMASZ MOSUR
 */
package tomek;

import java.util.Vector;
/**
 * Klasa wyliczająca liczby pierwsze z podanego zakresu
 */
public class PiTask implements tomek.Task {
    /**
     * Funkcja uruchamiająca dzialanie klasy
     * @param args Vector z danymi - wartości od, do wyliczania ilości uderzeń
     * @return ilość uderzeń
     */
    @Override
    public Object compute(Object args) {
        int from = ((Vector<Integer>) args).get(0);
        int to = ((Vector<Integer>) args).get(1);
        int hits = getPiHits(from,to);
        return hits;
    }

    /**
     * Funkcja licząca ilość uderzeń w danym przedziale
     * @param from początek przedziału
     * @param to koniec przedziału
     * @return ilość uderzeń
     */
    private int getPiHits(int from, int to) {
        int hits = 0;
        for (int i = from; i < to; i++) {
            double x = Math.random();
            double y = Math.random();

            if ((x * x + y * y) <= 1) {
                hits++;
            }
        }
        return hits;
    }
}
