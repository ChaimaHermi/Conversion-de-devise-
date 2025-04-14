package com.currency.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import com.currency.shared.CurrencyConverterInterface;

public class CurrencyConverterClient {
    public static void main(String[] args) {
        try {
            // 🔄 Récupérer le nom du serveur via variable d’environnement (définie dans Docker Compose)
            String serverHost = System.getenv("SERVER_HOST");
            if (serverHost == null || serverHost.isEmpty()) {
                serverHost = "localhost"; // fallback pour exécution locale
            }

            Registry registry = LocateRegistry.getRegistry(serverHost, 1099);
            CurrencyConverterInterface stub = (CurrencyConverterInterface) registry.lookup("CurrencyConverterService");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n💱 === Conversion de devise ===");
                System.out.print("Devise source (ex: USD): ");
                String from = scanner.nextLine().toUpperCase();

                System.out.print("Devise cible (ex: TND): ");
                String to = scanner.nextLine().toUpperCase();

                System.out.print("Montant à convertir : ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // consommer la fin de ligne

                try {
                    double result = stub.convert(from, to, amount);
                    System.out.println("✅ Résultat : " + amount + " " + from + " = " + result + " " + to);
                } catch (Exception ex) {
                    System.out.println("❌ Erreur : " + ex.getMessage());
                }

                System.out.print("↻ Refaire une autre conversion ? (o/n) : ");
                String again = scanner.nextLine();
                if (!again.equalsIgnoreCase("o")) break;
            }

        } catch (Exception e) {
            System.out.println("❌ Impossible de se connecter au serveur.");
            e.printStackTrace();
        }
    }
}
