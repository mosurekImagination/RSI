/**
 * AUTOR: PIOTR CHOLEWA
 */
package tomek;
import java.util.*;

/**
 * Klasa workera jako rozszerzenie klasy thread do wielowątkowości
 */
public class WorkerThread extends Thread {
    private tomek.Worker w;
    private tomek.Task t;
    private Object params;
    private Vector results;

    /**
     * Konstruktor
     * @param w już podpięty worker(RMI)
     * @param t zadanie do wykonania
     * @param params parametry do wykonania zadania
     * @param result wektor z wynikami
     */
    public WorkerThread(tomek.Worker w, tomek.Task t, Object params, Vector result) {
        super();
        this.w = w;
        this.t = t;
        this.params = params;
        this.results = result;
    }

    /**
     * Metoda wykonująca zadanie na workerze i zwracająca dostęp
     */
    public void run() {
        Object o = null;
        try {
            o = w.compute(t, params);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        synchronized(results) {
            results.add(o);
            results.notify();
        }
    }

}