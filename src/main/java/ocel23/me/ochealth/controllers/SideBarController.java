package ocel23.me.ochealth.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Accordion sidebar;
    @FXML
    private TitledPane home;
    @FXML
    private TitledPane network;
    @FXML
    private TitledPane another;
    @FXML
    private TitledPane devices;
    @FXML
    private TitledPane powerSource;
    @FXML
    private TitledPane settings;
    @FXML
    private Button hardwareUse;
    @FXML
    private Button hardwareInfo;
    @FXML
    private Button hardwareStatistics;
    @FXML
    private Button softwareUse;
    @FXML
    private Button softwareInfo;
    @FXML
    private Button devicesInfo;
    @FXML
    private Button anotherInfo;
    @FXML
    private Button networkUse;
    @FXML
    private Button networkInfo;
    @FXML
    private Button powerSourceInfoAndUse;
    @FXML
    private Button subSettings;

    public void switchToHardwareUseScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/HardwareUse.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHardwareInfoScene(ActionEvent e) throws IOException {
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

    public void switchToHardwareStatisticsController(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/HardwareUseStatistics.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Accordion getSidebar() {
        return sidebar;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebar.sceneProperty().addListener((observable, oldScene, newScene) -> {

            ConfigHandler configHandler = new ConfigHandler();

            LanguageHandler languageHandler = new LanguageHandler();

            String vHome = "Home";
            String vNetwork = "Network";
            String vDevices = "Devices";
            String vPowerSource = "Power source";
            String vSettings = "Settings";
            String vAnother = "Another";

            String vNetworkUse = "Use";
            String vNetworkInfo = "Information";
            String vPowerSourceInfoAndUse = "Information and Use";
            String vSubSettings = "Settings use";
            String vAnotherInfo = "Another use";
            String vDevicesInfo = "Information";
            String vSoftwareInfo = "Information";
            String vSoftwareUse = "Use";
            String vHardwareUse = "Use";
            String vHardwareInfo = "Information";
            String vHardwareStatistics = "Statistics";

            if (configHandler.getSettingsFromConfig().getLanguage().equalsIgnoreCase("Czech")) {
                vHome = languageHandler.getLanguageValues().getSidebar().getHome();
                vNetwork = languageHandler.getLanguageValues().getSidebar().getNetwork().getTitle();
                vDevices = languageHandler.getLanguageValues().getSidebar().getDevices().getTitle();
                vPowerSource = languageHandler.getLanguageValues().getSidebar().getPowerSource().getTitle();
                vSettings = languageHandler.getLanguageValues().getSidebar().getSettings().getTitle();
                vAnother = languageHandler.getLanguageValues().getSidebar().getAnother().getTitle();
                vNetworkUse = languageHandler.getLanguageValues().getSidebar().getNetwork().getUse();
                vNetworkInfo = languageHandler.getLanguageValues().getSidebar().getNetwork().getInformation();
                vPowerSourceInfoAndUse = languageHandler.getLanguageValues().getSidebar().getPowerSource().getInformationAndUse();
                vSubSettings = languageHandler.getLanguageValues().getSidebar().getSettings().getSettings();
                vAnotherInfo = languageHandler.getLanguageValues().getSidebar().getAnother().getInformation();
                vDevicesInfo = languageHandler.getLanguageValues().getSidebar().getDevices().getInformation();
                vSoftwareInfo = languageHandler.getLanguageValues().getSidebar().getSoftware().getInformation();
                vSoftwareUse = languageHandler.getLanguageValues().getSidebar().getSoftware().getUse();
                vHardwareInfo = languageHandler.getLanguageValues().getSidebar().getHardware().getInformation();
                vHardwareUse = languageHandler.getLanguageValues().getSidebar().getHardware().getUse();
                vHardwareStatistics = languageHandler.getLanguageValues().getSidebar().getHardware().getStatistics();
            }

            home.setText(vHome);
            network.setText(vNetwork);
            devices.setText(vDevices);
            powerSource.setText(vPowerSource);
            settings.setText(vSettings);
            another.setText(vAnother);

            networkUse.setText(vNetworkUse);
            networkInfo.setText(vNetworkInfo);
            powerSourceInfoAndUse.setText(vPowerSourceInfoAndUse);
            subSettings.setText(vSubSettings);
            anotherInfo.setText(vAnotherInfo);
            devicesInfo.setText(vDevicesInfo);
            hardwareInfo.setText(vHardwareInfo);
            hardwareUse.setText(vHardwareUse);
            hardwareStatistics.setText(vHardwareStatistics);
            softwareInfo.setText(vSoftwareInfo);
            softwareUse.setText(vSoftwareUse);

        });
    }
}
