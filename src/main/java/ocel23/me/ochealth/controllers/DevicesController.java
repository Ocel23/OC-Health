package ocel23.me.ochealth.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ocel23.me.ochealth.ConfigHandler;
import ocel23.me.ochealth.LanguageHandler;
import ocel23.me.ochealth.models.Device;
import ocel23.me.ochealth.models.Menu;
import ocel23.me.ochealth.models.enums.DeviceType;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DevicesController implements Initializable {

    @FXML
    private BorderPane devicesController;
    @FXML
    private HBox contentContainer;
    @FXML
    private ScrollPane scrollContainer;
    @FXML
    private Text title;

    private boolean isLoadedSmall;

    private boolean isLoadedBig;

    private List<Device> devices = List.of(
            new Device("sound", DeviceType.SOUND),
            new Device("microphone", DeviceType.SOUND),
            new Device("headphones", DeviceType.SOUND),
            new Device("mikrofon", DeviceType.SOUND),
            new Device("sluchátka", DeviceType.SOUND),
            new Device("audio", DeviceType.SOUND),
            new Device("keyboard", DeviceType.KEYBOARD),
            new Device("klávesnice", DeviceType.KEYBOARD),
            new Device("mouse", DeviceType.MOUSE),
            new Device("myš", DeviceType.MOUSE)

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu menu = new Menu();
        menu.create(devicesController);

        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        HardwareAbstractionLayer hw = si.getHardware();
        CentralProcessor cpu = hw.getProcessor();
        GlobalMemory memory = hw.getMemory();
        Sensors sensors = hw.getSensors();
        Timer timer = new Timer();


        devicesController.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            if (newScene != null) {

                devicesController.getScene().setUserData(devicesController);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                String language = configHandler.getSettingsFromConfig().getLanguage();

                String pName = "Parent name:";
                String cName = "Child name:";
                if (language.equalsIgnoreCase("Czech")) {
                    title.setText(languageHandler.getLanguageValues().getDevices().getTitle());
                    pName = languageHandler.getLanguageValues().getDevices().getParentName();
                    cName = languageHandler.getLanguageValues().getDevices().getChildName();
                }

                devicesController.getScene().widthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Scene scene = devicesController.getScene();
                        double width = scene.getWidth();
                        scrollContainer.setPrefWidth(width - 500);
                        contentContainer.setPrefWidth(scrollContainer.getPrefWidth() - 2);
                        if (width > 1600) {
                            isLoadedSmall = false;
                            if (!isLoadedBig) {
                                isLoadedBig = true;
                            }

                        } else if (width > 1200) {

                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {

                                isLoadedSmall = true;
                            }
                        }
                    }
                });

                String intervalValue = configHandler.getSettingsFromConfig().getCollectStatisticsDataInterval();

                long interval = 0;

                if (intervalValue.equalsIgnoreCase("1  minute")) {
                    interval = 60000L;
                } else if (intervalValue.equalsIgnoreCase("5 minutes")) {
                    interval = 3600000L;
                } else if (intervalValue.equalsIgnoreCase("15 minutes")) {
                    interval = 10800000L;
                } else if (intervalValue.equalsIgnoreCase("30 minutes")) {
                    interval = 21600000L;
                } else {
                    interval = 1000L;
                }

                if (configHandler.getSettingsFromConfig().isCollectStatisticData()) {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            FileWriter writer;
                            try {
                                writer = new FileWriter(Paths.get(getClass().getResource("/ocel23/me/ochealth/statistics.txt").toURI()).toFile());
                            } catch (IOException | URISyntaxException e) {
                                throw new RuntimeException(e);
                            }

                            try {
                                writer.write("[DEVICES] CONNECTED DEVICES" + " (" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            for (UsbDevice usbDevice : hw.getUsbDevices(true)) {
                                for (UsbDevice usbDevice1 : usbDevice.getConnectedDevices()) {
                                    for (UsbDevice usbDevice2 : usbDevice1.getConnectedDevices()) {
                                        for (UsbDevice usbDevice3 : usbDevice2.getConnectedDevices()) {
                                            try {
                                                writer.write("[DEVICES] " + usbDevice3.getName() + " is connected (" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }, 0L, interval);
                }

                for (UsbDevice usbDevice : hw.getUsbDevices(true)) {
                    for (UsbDevice usbDevice1 : usbDevice.getConnectedDevices()) {
                        for (UsbDevice usbDevice2 : usbDevice1.getConnectedDevices()) {
                            for (UsbDevice usbDevice3 : usbDevice2.getConnectedDevices()) {
                                VBox vBox = new VBox();
                                vBox.setAlignment(Pos.CENTER);
                                Text text = new Text(pName + " " + usbDevice3.getName());
                                text.setFill(Color.WHITE);
                                text.setFont(Font.font("Poppins Medium", 25));
                                String childName = "None";
                                if (!usbDevice3.getConnectedDevices().isEmpty()) {
                                    childName = usbDevice3.getConnectedDevices().get(0).getName();
                                }
                                Image image = null;
                                Enum<DeviceType> deviceType = DeviceType.USB;
                                for (Device device : devices) {
                                    if (childName.toLowerCase().contains(device.getName())) {
                                        deviceType = device.getDeviceType();
                                    }
                                }
                                try {
                                    if (deviceType.equals(DeviceType.MOUSE)) {
                                        image = new Image(getClass().getResource("/ocel23/me/ochealth/images/mouseIcon.png").toURI().toString());
                                    } else if (deviceType.equals(DeviceType.KEYBOARD)) {
                                        image = new Image(getClass().getResource("/ocel23/me/ochealth/images/keyboardIcon.png").toURI().toString());
                                    } else if (deviceType.equals(DeviceType.SOUND)) {
                                        image = new Image(getClass().getResource("/ocel23/me/ochealth/images/soundIcon.png").toURI().toString());
                                    } else {
                                        image = new Image(getClass().getResource("/ocel23/me/ochealth/images/usbIcon.png").toURI().toString());
                                    }
                                } catch (URISyntaxException e) {
                                    throw new RuntimeException(e);
                                }
                                ImageView imageView = new ImageView(image);
                                imageView.setFitHeight(320);
                                imageView.setFitWidth(320);
                                vBox.getChildren().add(imageView);
                                vBox.getChildren().add(text);
                                Text text1 = new Text(cName + " " + childName);
                                text1.setFill(Color.WHITE);
                                text1.setFont(Font.font("Poppins Medium", 25));
                                vBox.getChildren().add(text1);
                                contentContainer.getChildren().add(vBox);
                                Rectangle rectangle = new Rectangle();
                                rectangle.setHeight(400);
                                rectangle.setWidth(8);
                                rectangle.setFill(Color.web("#144272"));
                                contentContainer.getChildren().add(rectangle);
                            }
                        }
                    }
                }

                FileWriter writer;

                try {
                    writer = new FileWriter(Paths.get(getClass().getResource("/ocel23/me/ochealth/logs.txt").toURI()).toFile());
                } catch (IOException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }

                try {
                    writer.write("[DEVICES] Now is connected " + hw.getUsbDevices(true).size() + "devices");
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));


    }
}
