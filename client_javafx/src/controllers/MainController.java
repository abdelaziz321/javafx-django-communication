package controllers;

import models.Field;
import javafx.fxml.FXML;
import org.json.JSONObject;
import org.json.JSONException;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button addBtnA;

    @FXML
    private Button subtractBtnA;

    @FXML
    private Button addBtnB;

    @FXML
    private Button subtractBtnB;

    @FXML
    private Button addBtnC;

    @FXML
    private Button subtractBtnC;

    @FXML
    private Button addBtnD;

    @FXML
    private Button subtractBtnD;

    @FXML
    private Label appStatus;

    @FXML
    private Label resultA;

    @FXML
    private Label resultB;

    @FXML
    private Label resultC;

    @FXML
    private Label resultD;

    @FXML
    private Label channelA;

    @FXML
    private Label channelB;

    @FXML
    private Label channelC;

    @FXML
    private Label channelD;

    private Field fieldModel;

    public void initialize()
    {
        this.fieldModel = new Field(this);

        this.setInitialFieldsValues();

        this.addBtnA.setOnAction(e -> this.add("A", 1));
        this.addBtnB.setOnAction(e -> this.add("B", 1));
        this.addBtnC.setOnAction(e -> this.add("C", 1));
        this.addBtnD.setOnAction(e -> this.add("D", 1));

        this.subtractBtnA.setOnAction(e -> this.add("A", -1));
        this.subtractBtnB.setOnAction(e -> this.add("B", -1));
        this.subtractBtnC.setOnAction(e -> this.add("C", -1));
        this.subtractBtnD.setOnAction(e -> this.add("D", -1));
    }

    private void setInitialFieldsValues()
    {
        // retrieve the initial values
        JSONObject jsonFields = null;

        try {
            jsonFields = this.fieldModel.getInitialValues();
            this.updateAppStatus("retrieved Json is " + jsonFields.toString() + "\n\n");
        }
        catch (Exception e) {
            this.updateAppStatus("Can't retrieve the initial fields values\n\n");
        }

        // show the initial values
        try {
            this.setResultLabels(jsonFields);
        }
        catch (JSONException e) {
            this.updateAppStatus("Can't parse the initial fields values\n\n");
        }
    }

    /**
     * use the given json {"A":3,"B":14,"C":56,"D":37} to show the fields results
     *
     * @param jsonFields
     */
    private void setResultLabels(JSONObject jsonFields) throws JSONException
    {
        this.resultA.setText( jsonFields.getString("A") );
        this.resultB.setText( jsonFields.getString("B") );
        this.resultC.setText( jsonFields.getString("C") );
        this.resultD.setText( jsonFields.getString("D") );
    }

    @FXML
    public void add(String field, int value)
    {
        // send a request to update the value of the given field
        JSONObject jsonFields = null;

        try {
            jsonFields = this.fieldModel.updateField(field, value);
            this.updateAppStatus("retrieved Json is " + jsonFields.toString() + "\n\n");
        }
        catch (Exception e) {
            this.updateAppStatus("Can't update the value of the field: " + field + "\n\n");
        }

        // show the updated values
        try {
            this.setResultLabel(field, jsonFields);
        }
        catch (JSONException e) {
            this.updateAppStatus("Can't parse the retrieved value of the field: " + field + "\n\n");
        }
    }

    /**
     * use the given json {"field":44} to show the fields results
     *
     * @param jsonFields
     */
    private void setResultLabel(String field, JSONObject jsonFields) throws JSONException
    {
        int newValue = jsonFields.getInt("field");

        switch (field) {
            case "A":
                resultA.setText(Integer.toString(newValue));
                break;

            case "B":
                resultB.setText(Integer.toString(newValue));
                break;

            case "C":
                resultC.setText(Integer.toString(newValue));
                break;

            case "D":
                resultD.setText(Integer.toString(newValue));
                break;
        }
    }

    /**
     * updating the app status "the little bar at the bottom of the window".
     *
     * @param paragraph String
     */
    public void updateAppStatus(String paragraph)
    {
        // log the message
        System.out.println("Status: " + paragraph);

        // update the status bar
        this.appStatus.setText("Status: " + paragraph);
    }
}
