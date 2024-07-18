package ocel23.me.ochealth.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import ocel23.me.ochealth.ConfigHandler;
import ocel23.me.ochealth.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

public class AnotherComponentsController implements Initializable {

    @FXML
    private BorderPane anotherCControllers;

    @FXML
    private FlowPane contentContainer;

    private boolean isLoadedSmall;
    private boolean isLoadedBig;

    @FXML
    private Text title;

    @FXML
    private Text firmwareName;
    @FXML
    private Text firmwareDescription;
    @FXML
    private Text firmwareManufacturer;
    @FXML
    private Text firmwareReleaseDate;
    @FXML
    private Text firmwareVersion;

    @FXML
    private Text soundCardName;
    @FXML
    private Text soundCardDriverV;
    @FXML
    private Text soundCardCodec;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu menu = new Menu();
        menu.create(anotherCControllers);

        anotherCControllers.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            if (newScene != null) {

                SystemInfo si = new SystemInfo();
                OperatingSystem os = si.getOperatingSystem();
                HardwareAbstractionLayer hw = si.getHardware();
                CentralProcessor cpu = hw.getProcessor();
                GlobalMemory memory = hw.getMemory();
                Sensors sensors = hw.getSensors();
                Timer timer = new Timer();

                anotherCControllers.getScene().setUserData(anotherCControllers);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                String language = configHandler.getSettingsFromConfig().getLanguage();

                String fName = "Name:";
                String fVersion = "Version:";
                String fManufacturer = "Manufacturer:";
                String fDescription = "Description:";
                String fReleaseDate = "Release date:";
                String sCardName = "Name:";
                String sCardCodec = "Codec:";
                String sCardDriveV = "Driver version:";
                if (language.equalsIgnoreCase("Czech")) {
                    title.setText(languageHandler.getLanguageValues().getAnother().getTitle());
                    fName = languageHandler.getLanguageValues().getAnother().getFirmware().getName();
                    firmwareName.setText(fName);
                    fVersion = languageHandler.getLanguageValues().getAnother().getFirmware().getVersion();
                    firmwareVersion.setText(fVersion);
                    fManufacturer = languageHandler.getLanguageValues().getAnother().getFirmware().getManufacturer();
                    firmwareManufacturer.setText(fManufacturer);
                    fDescription = languageHandler.getLanguageValues().getAnother().getFirmware().getDescription();
                    firmwareDescription.setText(fDescription);
                    fReleaseDate = languageHandler.getLanguageValues().getAnother().getFirmware().getReleaseDate();
                    firmwareReleaseDate.setText(fReleaseDate);
                    sCardName = languageHandler.getLanguageValues().getAnother().getSoundCard().getName();
                    soundCardName.setText(sCardName);
                    sCardCodec = languageHandler.getLanguageValues().getAnother().getSoundCard().getCodec();
                    soundCardCodec.setText(sCardCodec);
                    sCardDriveV = languageHandler.getLanguageValues().getAnother().getSoundCard().getDriverVersion();
                    soundCardDriverV.setText(sCardDriveV);
                }

                anotherCControllers.getScene().widthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Scene scene = anotherCControllers.getScene();
                        double width = scene.getWidth();

                        contentContainer.setPrefWidth(width - 500);
                        if (width > 1600) {
                            isLoadedSmall = false;
                            if (!isLoadedBig) {
                                isLoadedBig = true;
                            }
                        } else if (width > 1200) {
                            title.setText("ANOTHER COMPONENTS");
                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {
                                title.setText("ANOTHER");
                                isLoadedSmall = true;
                            }
                        }
                    }
                });

                Firmware firmware = hw.getComputerSystem().getFirmware();
                firmwareName.setText(fName + " " + firmware.getName());
                firmwareDescription.setText(fDescription + " " + firmware.getDescription());
                firmwareManufacturer.setText(fManufacturer + " " + firmware.getManufacturer());
                firmwareReleaseDate.setText(fReleaseDate + " " + firmware.getReleaseDate());
                firmwareVersion.setText(fVersion + " " + firmware.getVersion());

                List<SoundCard> soundCards = hw.getSoundCards();

                for (SoundCard soundCard : soundCards) {
                    soundCardName.setText(sCardName + " " + soundCard.getName());
                    soundCardDriverV.setText(sCardDriveV + " " + soundCard.getDriverVersion());
                    soundCardCodec.setText(sCardCodec + " " + soundCard.getCodec());
                    break;
                }
            }

        }));


    }
}
