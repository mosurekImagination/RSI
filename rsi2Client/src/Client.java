/**
 * AUTOR: PIOTR CHOLEWA
 */
package tomek;
import java.util.Vector;

/**
 * Klasa farmera
 */
public class Client {
    /**
     * Funkcja uruchamiajaca działanie farmera
     * @param args lista serwerów //host/resource
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You have to enter RMI object address in the form: // host_address/service_name ");
            return;
        }
        System.setProperty("java.security.policy","file:/C:/Users/Tomek/IdeaProjects/rmi2/src/RMIServer/src/srv.policy");

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        Vector<tomek.Farmer> vFarmers = new Vector<>();


        try {
            for (String arg : args) {
                vFarmers.add((tomek.Farmer) java.rmi.Naming.lookup(arg));
            }
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac referencji do " + e);
            e.printStackTrace();
        }

        try {
            int[] values = {1,2000000};
            System.out.println(vFarmers.get(0).compute(new tomek.TaskPrimes(), values));
        } catch (Exception e) {
            System.out.println("Blad zdalnego wywolania.");
            e.printStackTrace();
        }

    }
}
