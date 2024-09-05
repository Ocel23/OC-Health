package ocel23.me.ochealth.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import org.controlsfx.control.Notifications;
import oshi.SystemInfo;
import oshi.hardware.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PowerSourceController implements Initializable {

    @FXML
    private BorderPane powerSourceContainer;
    @FXML
    private FlowPane contentContainer;
    @FXML
    private ScrollPane scrollContainer;
    @FXML
    private Text title;

    @FXML
    private Text capacity;
    @FXML
    private Text powerUsageRate;
    @FXML
    private Text deviceName;
    @FXML
    private Text temperature;
    @FXML
    private Text amperage;
    @FXML
    private Text timeRemaining;
    @FXML
    private Text voltage;
    @FXML
    private Text manufacturer;

    @FXML
    private Rectangle batteryFill;
    @FXML
    private Text batteryUse;

    private boolean isLoadedBig = false;
    private boolean isLoadedSmall = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        powerSourceContainer.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {

            //if for prevent wrong loading of elements
            if (newScene != null) {

                //create sidebar for app
                Menu menu = new Menu();
                menu.create(powerSourceContainer);

                SystemInfo si = new SystemInfo();
                HardwareAbstractionLayer hw = si.getHardware();
                Timer timer = new Timer();

                //pass container for function sidebar
                powerSourceContainer.getScene().setUserData(powerSourceContainer);

                //handling language values
                LanguageHandler languageHandler = new LanguageHandler();
                ConfigHandler configHandler = new ConfigHandler();
                String language = configHandler.getSettingsFromConfig().getLanguage();

                String vTitle = "POWER SOURCE";
                if (language.equals("Czech")) {
                    vTitle = languageHandler.getLanguageValues().getPowerSource().getTitle();
                }

                String finalVTitle = vTitle;

                String [] text = finalVTitle.split(" ");
                title.setText(text[0]);

                //change elements by screen width
                powerSourceContainer.getScene().widthProperty().addListener((observableValue1, number, t1) -> {
                    Scene scene = powerSourceContainer.getScene();

                    double width = scene.getWidth();
                    scrollContainer.setPrefWidth(width - 500);
                    contentContainer.setPrefWidth(scrollContainer.getPrefWidth() - 2);

                    if (width > 1600) {
                        isLoadedSmall = false;
                        if (!isLoadedBig) {
                            isLoadedBig = true;
                        }
                    } else if (width > 1200) {
                        title.setText(finalVTitle);
                    } else if (width < 1200) {
                        isLoadedBig = false;
                        if (!isLoadedSmall) {
                            String [] text2 = finalVTitle.split(" ");
                            title.setText(text2[0]);
                            isLoadedSmall = true;
                        }
                    }
                });

                List<PowerSource> powerSource = hw.getPowerSources();

                //update battery data values
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        getBatteryData(languageHandler, language, powerSource, configHandler);
                    }
                }, 0L, 1000L);
            }
        }));


    }

    /**
     * this method get battery data, update them and display them for user
     * @param languageHandler
     * @param language
     * @param powerSource
     * @param configHandler
     */
    private void getBatteryData(LanguageHandler languageHandler, String language, List<PowerSource> powerSource, ConfigHandler configHandler) {
        String vCapacity = "Capacity:";
        String vPowerUsageRate = "Power usage rate:";
        String vDeviceName = "Device name:";
        String vTemperature = "Temperature:";
        String vAmperage = "Amperage:";
        String vTimeRemaining = "Time remaining:";
        String vVoltage = "Voltage:";
        String vManufacturer = "Manufacturer:";

        if (language.equalsIgnoreCase("Czech")) {
            vCapacity = languageHandler.getLanguageValues().getPowerSource().getCapacity();
            vPowerUsageRate = languageHandler.getLanguageValues().getPowerSource().getPowerUsageRate();
            vDeviceName = languageHandler.getLanguageValues().getPowerSource().getName();
            vTemperature = languageHandler.getLanguageValues().getPowerSource().getTemperature();
            vAmperage = languageHandler.getLanguageValues().getPowerSource().getAmperage();
            vTimeRemaining = languageHandler.getLanguageValues().getPowerSource().getTimeRemaining();
            vVoltage = languageHandler.getLanguageValues().getPowerSource().getVoltage();
            vManufacturer = languageHandler.getLanguageValues().getPowerSource().getManufacturer();
        }

        for (PowerSource powerSource1 : powerSource) {
            String capacityString = powerSource1.getCurrentCapacity() + "/" + powerSource1.getMaxCapacity() + "mAh";
            capacity.setText(vCapacity + " " + capacityString);
            powerUsageRate.setText(vPowerUsageRate + " " + powerSource1.getPowerUsageRate() + "mW");
            deviceName.setText(vDeviceName + " " + powerSource1.getDeviceName());
            temperature.setText(vTemperature + " " + powerSource1.getTemperature() +  "°C");
            amperage.setText(vAmperage + " " + powerSource1.getAmperage() + "mA");
            double minutes = powerSource1.getTimeRemainingInstant() / 60;
            timeRemaining.setText(vTimeRemaining + " " + minutes);
            voltage.setText(vVoltage + " " + powerSource1.getVoltage() + "V");
            manufacturer.setText(vManufacturer + " " + powerSource1.getManufacturer());
            double percents = 100 * ((double) powerSource1.getCurrentCapacity() / powerSource1.getMaxCapacity());
            double widthOfFillBattery = 203 * (percents / 100);
            batteryUse.setText(percents + "%");
            if (percents <= 80 && percents > 0) {
                if (configHandler.getSettingsFromConfig().isShowNotificationOnWarningValues()) {
                    Platform.runLater(() -> {
                        Notifications.create()
                                .title("Power source")
                                .text("Power source usage is lower than 80%.")
                                .showWarning();

                    });
                }
            }
            batteryFill.setWidth(widthOfFillBattery);
            if (percents > 70) {
                batteryFill.setFill(Color.web("#00ff2a"));
            } else if (percents < 70 && percents > 40) {
                batteryFill.setFill(Color.web("#ffa200"));
            } else {
                batteryFill.setFill(Color.web("#ff3300"));
            }

            //write data to logs file
            FileWriter writer;

            String path = System.getProperty("user.home") + File.separator + "Oc-Health";
            File customDir = new File(path);
            File logsFile = new File(customDir.getAbsolutePath() + "/logs.txt");

            try {
                writer = new FileWriter(logsFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (percents < 40 && percents != 0) {
                try {
                    writer.write("[POWER SOURCE] Battery has critical low power! Only " + percents + "%");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
