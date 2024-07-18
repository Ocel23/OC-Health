package ocel23.me.ochealth.controllers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SideBarController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Accordion sidebar;

    public void switchToHardwareUseScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Use.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHarwareInfoScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/HardwareInfo.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSoftwareUseScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/SotwareUse.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSoftwareInfoScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/SoftwareInfo.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNetworkUseScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/NetworkUse.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNetworkInfoScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/NetworkInfo.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDevicesScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Devices.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPowerSourceScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/PowerSource.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAnotherComponentsController(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/AnotherComponents.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSettingsController(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Settings.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Accordion getSidebar() {
        return sidebar;
    }
}
