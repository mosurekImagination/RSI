package com.company;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcObjImpl2 extends UnicastRemoteObject implements CalcObject2 {
    public CalcObjImpl2() throws RemoteException {
        super();
    }

    @Override
    public ResultType calculate(InputType inputParam) throws RemoteException {
        double zm1, zm2;
        ResultType wynik = new ResultType();

        zm1 = inputParam.getx1();
        zm2 = inputParam.getx2();
        wynik.result_description = "Operacja " + inputParam.operation;

        switch (inputParam.operation) {
            case "add":
                wynik.result = zm1 + zm2;
                break;
            case "sub":
                wynik.result = zm1 - zm2;
                break;
            default:
                wynik.result = 0;
                wynik.result_description = "Podano zla operacje";
        }
        return wynik;
    }
}
