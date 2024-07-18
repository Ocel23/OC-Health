package ocel23.me.ochealth.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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

    private boolean isLoadedBig;
    private boolean isLoadedSmall;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu menu = new Menu();
        menu.create(powerSourceContainer);

        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        HardwareAbstractionLayer hw = si.getHardware();
        CentralProcessor cpu = hw.getProcessor();
        GlobalMemory memory = hw.getMemory();
        Sensors sensors = hw.getSensors();
        Timer timer = new Timer();

        powerSourceContainer.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            if (newScene != null) {

                powerSourceContainer.getScene().setUserData(powerSourceContainer);

                powerSourceContainer.getScene().widthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
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
                            title.setText("POWER SOURCE");
                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {
                                title.setText("POWER");
                                isLoadedSmall = true;
                            }
                        }
                    }
                });
            }
        }));

        List<PowerSource> powerSource = hw.getPowerSources();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (PowerSource powerSource1 : powerSource) {
                    String capacityString = powerSource1.getCurrentCapacity() + "/" + powerSource1.getMaxCapacity() + "mAh";
                    capacity.setText("Capacity: " + capacityString);
                    powerUsageRate.setText("Power usage rate: " + powerSource1.getPowerUsageRate() + "mW");
                    deviceName.setText("Device name: " + powerSource1.getDeviceName());
                    temperature.setText("Temperature: " + powerSource1.getTemperature() +  "°C");
                    amperage.setText("Amperage: " + powerSource1.getAmperage() + "mA");
                    double minutes = powerSource1.getTimeRemainingInstant() / 60;
                    timeRemaining.setText("Time remaining: " + minutes);
                    voltage.setText("Voltage: " + powerSource1.getVoltage() + "V");
                    manufacturer.setText("Manufacturer: " + powerSource1.getManufacturer());
                    double percents = 100 * ((double) powerSource1.getCurrentCapacity() / powerSource1.getMaxCapacity());
                    double widthOfFillBattery = 203 * (percents / 100);
                    batteryUse.setText(percents + "%");
                    batteryFill.setWidth(widthOfFillBattery);
                    if (percents > 70) {
                        batteryFill.setFill(Color.web("#00ff2a"));
                    } else if (percents < 70 && percents > 40) {
                        batteryFill.setFill(Color.web("#ffa200"));
                    } else {
                        batteryFill.setFill(Color.web("#ff3300"));
                    }

                    FileWriter writer;

                    try {
                        writer = new FileWriter(Paths.get(getClass().getResource("/ocel23/me/ochealth/logs.txt").toURI()).toFile());
                    } catch (IOException | URISyntaxException e) {
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
        }, 0L, 1000L);


    }
}
