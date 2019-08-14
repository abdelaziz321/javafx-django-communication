package models;

import controllers.MainController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Field {
    private MainController mainController;

    /**
     * inject the Main Controller
     *
     * @param mainController
     */
    public Field(MainController mainController)
    {
        this.mainController = mainController;
    }
    
    public String getInitialValues() throws Exception {
        this.mainController.updateAppStatus("sending a request 'GET http://127.0.0.1:8000/fields' to get the fields values");

        // starting the connection
        URL obj = new URL("http://127.0.0.1:8000/fields");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        // response
        int responseCode = con.getResponseCode();
        this.mainController.updateAppStatus("returning with response code: " + responseCode);

        String inputLine;
        StringBuffer response = new StringBuffer();
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream())
        );

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
