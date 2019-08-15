package models;

import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import controllers.MainController;

/**
 * the data source
 * here will be all the api calls.
 */
public class Field {
    private MainController mainController;
    private final String BASE_URL = "http://127.0.0.1:8000/";

    /**
     * inject the Main Controller
     *
     * @param mainController MainController
     */
    public Field(MainController mainController)
    {
        this.mainController = mainController;
    }

    /**
     * Send a request to 'GET /fields'
     *
     * @throws Exception e
     */
    public JSONObject getInitialValues() throws Exception
    {
        this.mainController.updateAppStatus("sending a request 'GET " + BASE_URL + "fields' to get the fields values");

        // starting the request connection
        URL url = new URL(BASE_URL + "fields");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; utf-8");

        // handling the response
        int responseCode = con.getResponseCode();
        this.mainController.updateAppStatus("returning with response code: " + responseCode);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) response.append(inputLine);
        in.close();

        return new JSONObject(response.toString());
    }

    /**
     * Send a request to 'POST /fields/<field>'
     *
     * @throws Exception e
     */
    public JSONObject updateField(String field, int value) throws Exception
    {
        this.mainController.updateAppStatus("sending a request 'POST " + BASE_URL + "fields/" + field + "' to get the fields values");

        // starting the request
        String encodedData = URLEncoder.encode( "value=" + value, "UTF-8" );

        URL url = new URL(BASE_URL + "fields/" + field);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));

        this.mainController.updateAppStatus("the body is: " + encodedData);

        con.setDoOutput(true);
        con.getOutputStream().write(encodedData.getBytes("UTF8"));

        // handling the response
        int responseCode = con.getResponseCode();
        this.mainController.updateAppStatus("returning with response code: " + responseCode);


        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) response.append(inputLine);
        in.close();

        return new JSONObject(response.toString());
    }
}
