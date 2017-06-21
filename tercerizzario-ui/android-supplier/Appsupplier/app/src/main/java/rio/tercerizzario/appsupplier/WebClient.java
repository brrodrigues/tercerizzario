package rio.tercerizzario.appsupplier;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by CsGo on 06/04/2017.
 */

public class WebClient {

    public String post (String json) {
        try {
            URL url = new URL("http://45.55.14.13:8080/suppliers");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type","application/json");
            connection.setRequestProperty("Accept","application/json");
            ///connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);
            connection.connect();
            Scanner scanner = new Scanner(connection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String get (String email) {
        try {
            URL url = new URL("http://45.55.14.13:8080/suppliers/search/byEmail?email="+email);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type","application/json");
            connection.connect();
            Scanner sacanner = new Scanner(connection.getInputStream());

            String resposta = sacanner.next();
            while (sacanner.hasNext()) {
                resposta = resposta + sacanner.next();
            }

            return resposta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

