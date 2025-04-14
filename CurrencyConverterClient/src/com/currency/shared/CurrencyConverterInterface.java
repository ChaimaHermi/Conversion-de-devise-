package com.currency.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CurrencyConverterInterface extends Remote {
    double convert(String fromCurrency, String toCurrency, double amount) throws RemoteException;
}
