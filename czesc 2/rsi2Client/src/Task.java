/**
 * AUTOR: MARTYNA KUMASZKA I TOMASZ MOSUR
 */
package tomek;

/**
 * Interfejs zadania
 */
public interface Task extends java.io.Serializable {
    /**
     * Metoda do uruchamiania zadania
     * @param args argumenty funkcji
     * @return wynik funkcji
     */
    public Object compute(Object args);
}