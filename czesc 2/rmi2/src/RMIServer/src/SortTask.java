package tomek;

import java.util.Collections;
import java.util.Vector;
/**
 * Klasa wyliczająca liczby pierwsze z podanego zakresu
 */
public class SortTask implements tomek.Task {
    /**
     * Funkcja uruchamiająca dzialanie klasy
     * @param args Vector z liczbami do posortowania
     * @return Vector posortowany ciąg liczb
     */
    @Override
    public Object compute(Object args) {
        Vector tab = (Vector<Integer>) args;
        Collections.sort(tab);
        return tab;
    }

}
