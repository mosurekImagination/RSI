/**
 * AUTOR: MARTYNA KUMASZKA I TOMASZ MOSUR
 */
package tomek;
import RMIServer.src.CombinePiTask;

import java.util.*;

import static java.lang.Math.round;

/**
 * Implementacja farmera - przyjmuje zadania i rozdziela na workery
 */
public class FarmerImpl
        extends java.rmi.server.UnicastRemoteObject
        implements tomek.Farmer {

    private tomek.Worker[] workers;

    /**
     * Konstruktor dla farmera
     * @param args lista argumentów, adresy serwerów-workerów
     * @throws java.rmi.RemoteException
     * @throws Exception
     */
    public FarmerImpl(String[] args)
            throws java.rmi.RemoteException,
            Exception {
        super();

        workers = new tomek.Worker[args.length - 1];

        try {
            for (int i = 1; i < args.length; i++) {
                workers[i - 1] = ((tomek.Worker) java.rmi.Naming.lookup(args[i]));
            }
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac referencji do " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda do wykonywania zadania, rozdziela zadanie na asynchroniczne workery, łączy dane i zwraca
     * @param t zadanie do wykonanie
     * @param params parametry wykonywanego zadania
     * @return
     * @throws java.rmi.RemoteException
     */
    public Object compute(tomek.Task t, Object params) throws java.rmi.RemoteException {
        tomek.WorkerThread[] threads = new tomek.WorkerThread[workers.length];
        Vector results = new Vector();

        if (t instanceof tomek.TaskPrimes) {
            results = getResultFromPrimeTask(t, (int[]) params, threads);
        }
        if (t instanceof CombinePiTask || t instanceof PiTask) {
            results = getResultFromPrimeTask(t, (int[]) params, threads);

            synchronized (results) {
                while (results.size() < workers.length) {
                    try {
                        results.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                results.add(((int[])params)[1]);
                results = (Vector)workers[0].compute(new CombinePiTask(), results);
                return results;
            }
        }

        synchronized (results) {
            while (results.size() < workers.length) {
                try {
                    results.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }

    private Vector getResultFromPrimeTask(tomek.Task t, int[] params, tomek.WorkerThread[] threads) {
        Vector results = new Vector();
        int last = params[1];
        int first = params[0];
        int point = last / workers.length;
        for (int i = 0; i < workers.length; i++) {
            Vector<Integer> vInput = new Vector<>();
            if (i == 0)
                vInput.add(first);
                else
                vInput.add(i * point);
            vInput.add((i + 1) * point);

            threads[i] = new tomek.WorkerThread(
                    workers[i], t, vInput, results
            );
            threads[i].start();
        }
        return results;
    }

    private Vector getResultFromSortTask(tomek.Task t, int[] params, tomek.WorkerThread[] threads) {
        Vector results = new Vector();
        int point = params.length / workers.length;
        for (int i = 0; i < workers.length; i++) {
            Vector<Integer> vInput = new Vector<>();
            if (i == 0)
                vInput.add(0);
            else
                vInput.add(i * point);
            vInput.add((i + 1) * point);

            threads[i] = new tomek.WorkerThread(
                    workers[i], t, vInput, results
            );
            threads[i].start();
        }
        return results;
    }


    /**
     * Klasa głowna, uruchamiająca farmera i rejestrująca go w registry.
     * @param args argumenty
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("You have to enter RMI object address in the form: //host_address/service_name");
            return;
        }
        String accFile = System.getProperty("user.dir");
        String filepath = "file:/"+accFile+"/srv.policy";
        System.setProperty("java.security.policy", filepath);

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            FarmerImpl instance = new FarmerImpl(args);
            java.rmi.Naming.rebind(args[0], instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Farmer ready.");
    }

}