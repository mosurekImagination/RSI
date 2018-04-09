/**
 * AUTOR: PIOTR CHOLEWA
 */
package tomek;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 * Klasa workera implementująca interfejs workera
 */
public class WorkerImp extends java.rmi.server.UnicastRemoteObject implements tomek.Worker {

    /**
     * Konstruktor główny
     * @throws java.rmi.RemoteException
     */
    public WorkerImp() throws java.rmi.RemoteException {
        super();
    }

    /**
     * Metoda uruchamiająca zadaną funkcję
     * @param task funkcja zadania
     * @param params parametry do wywołania funkcji
     * @return wynik działania funkcji
     * @throws java.rmi.RemoteException
     */
    public Object compute(tomek.Task task, Object params)
            throws java.rmi.RemoteException {
        System.out.println("RUNNIN");
        return task.compute(params);
    }

    /**
     * Głowna metoda workera rejestrująca serwer
     * @param args adres pod jakim ma nasłuchiwać serwer
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You have to enter RMI object address in the form: //host_address/service_name");
            return;
        }
        String accFile = System.getProperty("user.dir");
        String filepath = "file:/"+accFile+"/srv.policy";
        System.setProperty("java.security.policy", filepath);

        try {
            Registry reg = LocateRegistry.createRegistry(1099);
        } catch (RemoteException e1) {
            System.out.println("Registry already exists");
            //e1.printStackTrace();
        }

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            WorkerImp instance = new WorkerImp();
            java.rmi.Naming.rebind(args[0], instance);
            System.out.println("Worker gotowy.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
