package com.currency.server;

import com.currency.shared.CurrencyConverterInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverterImpl extends UnicastRemoteObject implements CurrencyConverterInterface {

    private static final String API_KEY = "53f8b7ad891b213d1b1b18a3";

    public CurrencyConverterImpl() throws RemoteException {
        super();
    }

    @Override
    public double convert(String from, String to, double amount) throws RemoteException {
        try {
            String apiUrl = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to;

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());

            if (!json.getString("result").equals("success")) {
                throw new RemoteException("Erreur API : " + json.getString("error-type"));
            }

            double rate = json.getDouble("conversion_rate");
            return amount * rate;

        } catch (Exception e) {
            throw new RemoteException("Erreur lors de l'appel à l'API : " + e.getMessage());
        }
    }
}
