package ocel23.me.ochealth.controllers;

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
import ocel23.me.ochealth.Utils;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Device;
import ocel23.me.ochealth.models.Menu;
import ocel23.me.ochealth.enums.DeviceType;
import oshi.SystemInfo;
import oshi.hardware.*;

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

    private final List<Device> devices = List.of(
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

    String vParentName = "Parent name:";
    String vChildName = "Child name:";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu menu = new Menu();
        menu.create(devicesController);

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hw = si.getHardware();
        Timer timer = new Timer();


        devicesController.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            if (newScene != null) {

                devicesController.getScene().setUserData(devicesController);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                String language = configHandler.getSettingsFromConfig().getLanguage();

                if (language.equalsIgnoreCase("Czech")) {
                    title.setText(languageHandler.getLanguageValues().getDevices().getTitle());
                    vParentName = languageHandler.getLanguageValues().getDevices().getParentName();
                    vChildName = languageHandler.getLanguageValues().getDevices().getChildName();
                }

                devicesController.getScene().widthProperty().addListener((observableValue1, number, t1) -> {
                    Scene scene = devicesController.getScene();
                    double width = scene.getWidth();

                    scrollContainer.setPrefWidth(width - 500);

                    contentContainer.setPrefWidth(scrollContainer.getPrefWidth() - 2);
                });


                long interval = Utils.getInterval(configHandler);

                if (configHandler.getSettingsFromConfig().isCollectStatisticData()) {

                    FileWriter writer;

                    try {
                        writer = new FileWriter(Paths.get(getClass().getResource("/ocel23/me/ochealth/statistics.txt").toURI()).toFile());
                    } catch (IOException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            writeLogs(writer, hw);

                        }
                    }, 0L, interval);
                }

                createDevicesView(hw);

                FileWriter writer2;

                try {
                    writer2 = new FileWriter(Paths.get(getClass().getResource("/ocel23/me/ochealth/logs.txt").toURI()).toFile());
                } catch (IOException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }

                try {
                    writer2.write("[DEVICES] Now is connected " + hw.getUsbDevices(true).size() + "devices");
                    writer2.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));


    }

    private void writeLogs(FileWriter writer, HardwareAbstractionLayer hw) {

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

    private void createDevicesView(HardwareAbstractionLayer hw) {

        for (UsbDevice usbDevice : hw.getUsbDevices(true)) {
            for (UsbDevice usbDevice1 : usbDevice.getConnectedDevices()) {
                for (UsbDevice usbDevice2 : usbDevice1.getConnectedDevices()) {
                    for (UsbDevice usbDevice3 : usbDevice2.getConnectedDevices()) {
                        VBox vBox = new VBox();
                        vBox.setAlignment(Pos.CENTER);
                        Text text = new Text(vParentName + " " + usbDevice3.getName());
                        text.setFill(Color.WHITE);
                        text.setFont(Font.font("Poppins Medium", 25));
                        String childName = "None";
                        if (!usbDevice3.getConnectedDevices().isEmpty()) {
                            childName = usbDevice3.getConnectedDevices().get(0).getName();
                        }
                        Image image;
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
                        Text text1 = new Text(vChildName + " " + childName);
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
    }
}
