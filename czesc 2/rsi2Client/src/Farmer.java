/**
 * AUTOR: MARTYNA KUMASZKA I TOMASZ MOSUR
 */
package tomek;

/**
 * Interfejs farmera
 */
public interface Farmer extends java.rmi.Remote {
    /**
     * Metoda obliczająca dla farmera
     * @param t zadanie do wykonanie
     * @param params parametry wykonywanego zadania
     * @return wynik zadania
     * @throws java.rmi.RemoteException
     */
    public Object compute(tomek.Task t, Object params)
            throws java.rmi.RemoteException;
}