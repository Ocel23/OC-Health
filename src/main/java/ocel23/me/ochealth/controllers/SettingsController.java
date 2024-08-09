package ocel23.me.ochealth.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.fileHandlers.StatisticsHandler;
import ocel23.me.ochealth.models.Menu;
import ocel23.me.ochealth.models.Settings;

import java.io.File;
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

    @FXML
    private Text defaultSectionOnStartApp;
    @FXML
    private Text collectStatisticsData;
    @FXML
    private Text collectStatisticDataInterval;
    @FXML
    private Text showNotificationsOnWarningValues;
    @FXML
    private Text exportData;
    @FXML
    private Text language;
    @FXML
    private Text enableLogging;
    @FXML
    private Text collectDataInfo;
    @FXML
    private Text reportBugInfo;
    @FXML
    private Button exportButton;
    @FXML
    private ComboBox<String> exportTypeInput;
    @FXML
    private Text title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        settingsContainer.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldScene, Scene newScene) {
                if (newScene != null) {
                    Menu menu = new Menu();
                    menu.create(settingsContainer);

                    settingsContainer.getScene().setUserData(settingsContainer);

                    String [] items = {"Home", "Devices", "HardwareInfo"
                            , "HardwareUse", "Another", "NetworkInfo",
                            "NetworkUse", "SoftwareInfo", "SoftwareUse",
                            "PowerSource"
                    };

                    LanguageHandler languageHandler = new LanguageHandler();

                    ConfigHandler configHandler = new ConfigHandler();

                    String language2 = configHandler.getSettingsFromConfig().getLanguage();

                    String vTitle = "SETTINGS";
                    String vDefaultSectionOnStartApp = "Default section on start:";
                    String vCollectStatisticsData = "Collect statistics data:";
                    String vCollectStatisticDataInterval = "Collect statistics data interval:";
                    String vShowNotificationsOnWarningValues = "Show notifications on warning values:";
                    String vExportData = "Export data:";
                    String vLanguage = "Language:";
                    String vEnableLogging = "Enable logging:";
                    String vCollectDataInfo = "Oc Health does not collect and store your data and does not publish it anywhere else, the data is only stored on your PC.";
                    String vReportBugInfo = "Please report bugs at github.com/ocel23";

                    if (language2.equalsIgnoreCase("Czech")) {
                        vTitle = languageHandler.getLanguageValues().getSettings().getTitle();
                        vDefaultSectionOnStartApp = languageHandler.getLanguageValues().getSettings().getDefaultSectionOnStartApp();
                        vCollectStatisticsData = languageHandler.getLanguageValues().getSettings().getCollectStatisticsData();
                        vCollectStatisticDataInterval = languageHandler.getLanguageValues().getSettings().getCollectStatisticDataInterval();
                        vShowNotificationsOnWarningValues = languageHandler.getLanguageValues().getSettings().getShowNotificationsOnWarningValues();
                        vExportData = languageHandler.getLanguageValues().getSettings().getExportData();
                        vLanguage = languageHandler.getLanguageValues().getSettings().getLanguage();
                        vEnableLogging = languageHandler.getLanguageValues().getSettings().getEnableLogging();
                        vCollectDataInfo = languageHandler.getLanguageValues().getSettings().getCollectDataInfo();
                        vReportBugInfo = languageHandler.getLanguageValues().getSettings().getReportBugInfo();
                    }

                    defaultSectionOnStartApp.setText(vDefaultSectionOnStartApp);
                    collectStatisticsData.setText(vCollectStatisticsData);
                    collectStatisticDataInterval.setText(vCollectStatisticDataInterval);
                    showNotificationsOnWarningValues.setText(vShowNotificationsOnWarningValues);
                    exportData.setText(vExportData);
                    language.setText(vLanguage);
                    enableLogging.setText(vEnableLogging);
                    collectDataInfo.setText(vCollectDataInfo);
                    reportBugInfo.setText(vReportBugInfo);

                    Settings settings = configHandler.getSettingsFromConfig();

                    String [] text4 = vTitle.split(" ");
                    title.setText(text4[0]);

                    defaultSectionInput.getItems().addAll(items);

                    defaultSectionInput.setOnAction(event -> {
                        String text = defaultSectionInput.getSelectionModel().getSelectedItem();
                        settings.setDefaultSectionOnStart(text);
                        configHandler.setSettingsToConfig(settings);
                    });

                    collectStatisticDataInput.setOnAction(event -> {
                        String value = collectStatisticDataInput.getText();
                        settings.setCollectStatisticData(Boolean.parseBoolean(value));
                        configHandler.setSettingsToConfig(settings);
                    });

                    String [] items2 = {"1 second", "1 minute", "5 minutes", "15 minutes", "30 minutes"};

                    collectStatisticsDataInterval.getItems().addAll(items2);

                    collectStatisticsDataInterval.setOnAction(event -> {
                        String text2 = collectStatisticsDataInterval.getSelectionModel().getSelectedItem();
                        settings.setCollectStatisticsDataInterval(text2);
                        configHandler.setSettingsToConfig(settings);
                    });

                    showNotificationsOnWarningValuesInput.setOnAction(event -> {
                        String value2 = showNotificationsOnWarningValuesInput.getText();
                        settings.setShowNotificationOnWarningValues(Boolean.parseBoolean(value2));
                        configHandler.setSettingsToConfig(settings);
                    });

                    String [] items3 = {"English", "Czech"};

                    languageInput.getItems().addAll(items3);

                    languageInput.setOnAction(event -> {
                        String text3 = languageInput.getSelectionModel().getSelectedItem();
                        settings.setLanguage(text3);
                        configHandler.setSettingsToConfig(settings);
                    });

                    enableLoggingInput.setOnAction(event -> {
                        String value3 = enableLoggingInput.getText();
                        settings.setCollectStatisticData(Boolean.parseBoolean(value3));
                        configHandler.setSettingsToConfig(settings);
                    });

                    String [] items4 = {"JSON", "YAML", "TEXT"};

                    exportTypeInput.getItems().addAll(items4);


                    exportButton.setOnAction(event -> {

                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save");
                        String fileType;
                        if (exportTypeInput.getSelectionModel().getSelectedItem().equals("JSON")) {
                            fileType = "*.json";
                        } else if (exportTypeInput.getSelectionModel().getSelectedItem().equals("YAML")) {
                            fileType = "*.yaml";
                        } else {
                            fileType = "*.txt";
                        }
                        StatisticsHandler statisticsHandler = new StatisticsHandler();
                        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("AllFiles ", fileType));
                        File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());
                        String path = file.getAbsolutePath();
                        if (exportTypeInput.getSelectionModel().getSelectedItem().equals("JSON")) {
                            statisticsHandler.createJsonDataFile(path);
                        } else if (exportTypeInput.getSelectionModel().getSelectedItem().equals("YAML")) {
                            statisticsHandler.createYamlDataFile(path);
                        } else {
                            statisticsHandler.createTextDataFile(path);
                        }
                    });
                }
            }
        });
    }
}
