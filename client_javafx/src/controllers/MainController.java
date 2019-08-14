package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    public void initialize() {
        this.addBtnA.setOnAction(e -> this.add("A", 1));
        this.addBtnB.setOnAction(e -> this.add("B", 1));
        this.addBtnC.setOnAction(e -> this.add("C", 1));
        this.addBtnD.setOnAction(e -> this.add("D", 1));

        this.subtractBtnA.setOnAction(e -> this.add("A", -1));
        this.subtractBtnB.setOnAction(e -> this.add("B", -1));
        this.subtractBtnC.setOnAction(e -> this.add("C", -1));
        this.subtractBtnD.setOnAction(e -> this.add("D", -1));
    }

    @FXML
    public void add(String field, int value) {

    }
}