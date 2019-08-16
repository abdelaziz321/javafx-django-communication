package models;

import org.json.JSONObject;
import org.asynchttpclient.Dsl;
import controllers.MainController;
import java.util.concurrent.Future;
import org.asynchttpclient.Response;
import org.asynchttpclient.AsyncHttpClient;

/**
 * the data source
 * here will be all the api calls.
 */
public class Field {
    private MainController mainController;
    private final String BASE_URL = "http://127.0.0.1:8000";

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
        this.mainController.updateAppStatus("sending a request 'GET " + BASE_URL + "/fields' to get the fields values");

        AsyncHttpClient client = Dsl.asyncHttpClient();

        Future<Response> responseFuture = client.prepareGet(BASE_URL + "/fields")
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .setFollowRedirect(true)
            .execute();

        Response response = responseFuture.get();
        client.close();

        this.mainController.updateAppStatus("returning with status code: " + response.getStatusCode());

        return new JSONObject(response.getResponseBody());
    }

    /**
     * Send a request to 'POST /fields/<field>'
     *
     * @throws Exception e
     */
    public JSONObject updateField(String field, int value) throws Exception
    {
        this.mainController.updateAppStatus("sending a request 'POST " + BASE_URL + "/fields/" + field + "' to get the fields values");

        String body = new JSONObject()
            .put("value", value)
            .toString();

        this.mainController.updateAppStatus("sending the body: " + body);

        AsyncHttpClient client = Dsl.asyncHttpClient();

        Future<Response> responseFuture = client.preparePost(BASE_URL + "/fields/" + field)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .setBody(body)
                .setFollowRedirect(true)
                .execute();

        Response response = responseFuture.get();
        client.close();

        this.mainController.updateAppStatus("returning with status code: " + response.getStatusCode());

        return new JSONObject(response.getResponseBody());
    }
}
