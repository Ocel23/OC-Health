package ocel23.me.ochealth.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import ocel23.me.ochealth.ConfigHandler;
import ocel23.me.ochealth.models.Menu;
import ocel23.me.ochealth.models.Settings;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private BorderPane settingsContainer;

    @FXML
    private ComboBox<String> defaultSectionInput;

    @FXML
    private CheckBox collectStatisticDataInput;

    @FXML
    private ComboBox<String> collectStatisticsDataInterval;

    @FXML
    private CheckBox showNotificationsOnWarningValuesInput;

    @FXML
    private ComboBox<String> languageInput;

    @FXML
    private CheckBox enableLoggingInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu menu = new Menu();
        menu.create(settingsContainer);

        String [] items = {"Home", "Devices", "HardwareInfo"
                , "HardwareUse", "Another", "NetworkInfo",
                "NetworkUse", "SoftwareInfo", "SoftwareUse",
                "PowerSource"
        };

        Settings settings = new Settings();

        ConfigHandler configHandler = new ConfigHandler();

        defaultSectionInput.getItems().addAll(items);

        defaultSectionInput.setOnAction(event -> {
            String text = defaultSectionInput.getSelectionModel().getSelectedItem();
            settings.setDefaultSectionOnStart(text);
        });

        collectStatisticDataInput.setOnAction(event -> {
            String value = collectStatisticDataInput.getText();
            settings.setCollectStatisticData(Boolean.parseBoolean(value));
        });

        String [] items2 = {"1 second", "1 minute", "5 minute", "15 minutes", "30 minutes"};

        collectStatisticsDataInterval.getItems().addAll(items2);

        collectStatisticsDataInterval.setOnAction(event -> {
            String text2 = defaultSectionInput.getSelectionModel().getSelectedItem();
            settings.setCollectStatisticsDataInterval(text2);
        });

        showNotificationsOnWarningValuesInput.setOnAction(event -> {
            String value2 = collectStatisticDataInput.getText();
            settings.setShowNotificationOnWarningValues(Boolean.parseBoolean(value2));
        });

        String [] items3 = {"English", "Czech"};

        languageInput.getItems().addAll(items3);

        languageInput.setOnAction(event -> {
            String text3 = defaultSectionInput.getSelectionModel().getSelectedItem();
            settings.setLanguage(text3);
        });

        enableLoggingInput.setOnAction(event -> {
            String value3 = collectStatisticDataInput.getText();
            settings.setCollectStatisticData(Boolean.parseBoolean(value3));
        });

        configHandler.setSettingsToConfig(settings);
    }
}
