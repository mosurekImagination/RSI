/**
 * AUTOR: PIOTR CHOLEWA
 */

/**
 * Interfejs Workera
 */
public interface Worker extends java.rmi.Remote {
    /**
     * Metoda do uruchamiania funkcji
     * @param task funkcja zadania
     * @param params parametry do wywołania funkcji
     * @return wynik funkcji
     * @throws java.rmi.RemoteException
     */
    public Object compute(tomek.Task task, Object params)
            throws java.rmi.RemoteException;
}