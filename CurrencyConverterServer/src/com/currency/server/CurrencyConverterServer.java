package com.currency.server;
import com.currency.shared.CurrencyConverterInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CurrencyConverterServer {
    public static void main(String[] args) {
        try {
            CurrencyConverterImpl obj = new CurrencyConverterImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CurrencyConverterService", obj);
            System.out.println("✅ Serveur de conversion de devise démarré !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
