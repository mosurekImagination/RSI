package com.company;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyClient {

    public static void main(String[] args) {
	double wynik = 0;
	CalcObject zObiekt = null;
	CalcObject2 zObiekt2 = null;
	ResultType wynik2 = null;
	InputType inObj;
	
	if (args.length == 0){
        System.out.println("You have to enter RMI object address in the form: // host_address/service_name ");
        return;
    }
    
    String adres1 = args[0];
	String adres2 = args[1];
	
//	//use this if needed
//        if(System.getSecurityManager() == null){
//            System.setSecurityManager(new SecurityManager());
//        }
        
        try {
            zObiekt = (CalcObject) java.rmi.Naming.lookup(adres1);
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac referencji do: " + adres1);
            e.printStackTrace();
        }
        System.out.println("Referencja do " + adres1 + " jest pobrana.");

        try {
            zObiekt2 = (CalcObject2) java.rmi.Naming.lookup(adres2);
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac referencji do: " + adres2);
            e.printStackTrace();
        }
        System.out.println("Referencja do " + adres2 + " jest pobrana.");
        
        try{
            wynik = zObiekt.calculate(1.1,2.2);
        } catch (Exception e) {
            System.out.println("Blad zdalnego wywolania");
            e.printStackTrace();
        }

        System.out.println("Wynik1 = " + wynik);
        
        inObj = new InputType();
        inObj.x1 = 2.24;
        inObj.x2 = 2.456;
        inObj.operation = "add";

        try{
            wynik2 = zObiekt2.calculate(inObj);
        } catch (Exception e) {
            System.out.println("Blad zdalnego wywolania");
            e.printStackTrace();
        }

        System.out.println("Wynik2 = " + wynik2.result + "opis: " + wynik2.result_description);

        
    }
}
