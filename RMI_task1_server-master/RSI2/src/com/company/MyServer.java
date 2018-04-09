package com.company;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class MyServer {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if(args.length == 0){
            System.out.println("You have to enter RMI object address in the form: " +
                    "//host_address/service_name ");
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

        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        try{
            CalcObjImpl implObiektu = new CalcObjImpl();
            java.rmi.Naming.rebind(args[0], implObiektu);
            System.out.println("Server is registered now :-)");
            System.out.println("Press Crl+C to stop...");
        }
        catch(Exception e){
            System.out.println("SERVER CAN'T BE REGISTERED");
            e.printStackTrace();
        }

        try{
            CalcObjImpl2 implObiektu2 = new CalcObjImpl2();
            java.rmi.Naming.rebind(args[1], implObiektu2);
            System.out.println("Server is registered now :-)");
            System.out.println("Press Crl+C to stop...");
        }
        catch(Exception e){
            System.out.println("SERVER CAN'T BE REGISTERED");
            e.printStackTrace();
        }
    }
}
