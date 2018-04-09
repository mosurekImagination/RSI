/**
 * AUTOR: MARTYNA KUMASZKA I TOMASZ MOSUR
 */
package tomek;
import java.util.Scanner;
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
        String accFile = System.getProperty("user.dir");
        String filepath = "file:/"+accFile+"/srv.policy";
        System.setProperty("java.security.policy", filepath);

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
//            int[] values = {1,2000000};
            int option=-1;
            int[] values = {1, 1000000};
            int param;

            while(option != 0)
            {
                System.out.println("Witaj, przyjacielu! \n" +
                        "0. Wyjście z programu\n" +
                        "1. Liczenie liczb pierwszych w zakresie (param = zakres) \n" +
                        "2. obliczenie liczby PI metodą monte Carlo (param = liczbaRzutow)\n" +
                        "3. Liczenie silni\n" +
                        "Wybierz opcje:");
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();

//                System.out.println("Podaj parametr:")
//                param = sc.nextInt();
                switch (option){
                    case 1: System.out.println(vFarmers.get(0).compute(new tomek.TaskPrimes(), values)); break;
                    case 2: System.out.println(vFarmers.get(0).compute(new tomek.PiTask(), values)); break;
                    case 3: System.out.println(vFarmers.get(0).compute(new tomek.FactorialTask(), values)); break;
                }
            }
        } catch (Exception e) {
            System.out.println("Blad zdalnego wywolania.");
            e.printStackTrace();
        }

    }
}
