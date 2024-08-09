package ocel23.me.ochealth.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.hardware.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnotherComponentsController implements Initializable {

    @FXML
    private BorderPane anotherContainer;

    @FXML
    private FlowPane contentContainer;

    private boolean isLoadedSmall = true;
    private boolean isLoadedBig = false;

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

        anotherContainer.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {

            if (newScene != null) {

                Menu menu = new Menu();
                menu.create(anotherContainer);

                SystemInfo si = new SystemInfo();
                HardwareAbstractionLayer hw = si.getHardware();

                anotherContainer.getScene().setUserData(anotherContainer);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                String language = configHandler.getSettingsFromConfig().getLanguage();

                String vTitle = "ANOTHER COMPONENTS";
                String vFirmwareName = "Name:";
                String vFirmwareVersion = "Version:";
                String vFirmwareManufacturer = "Manufacturer:";
                String vFirmwareDescription = "Description:";
                String vFirmwareReleaseDate = "Release date:";
                String vSoundCardName = "Name:";
                String vSoundCardCodec = "Codec:";
                String vSoundCardDriverVersion = "Driver version:";
                if (language.equalsIgnoreCase("Czech")) {
                    vTitle = languageHandler.getLanguageValues().getAnother().getTitle();
                    vFirmwareName = languageHandler.getLanguageValues().getAnother().getFirmware().getName();
                    vFirmwareVersion = languageHandler.getLanguageValues().getAnother().getFirmware().getVersion();
                    vFirmwareManufacturer = languageHandler.getLanguageValues().getAnother().getFirmware().getManufacturer();
                    vFirmwareDescription = languageHandler.getLanguageValues().getAnother().getFirmware().getDescription();
                    vFirmwareReleaseDate = languageHandler.getLanguageValues().getAnother().getFirmware().getReleaseDate();
                    vSoundCardName = languageHandler.getLanguageValues().getAnother().getSoundCard().getName();
                    vSoundCardCodec = languageHandler.getLanguageValues().getAnother().getSoundCard().getCodec();
                    vSoundCardDriverVersion = languageHandler.getLanguageValues().getAnother().getSoundCard().getDriverVersion();

                }

                String [] text2 = vTitle.split(" ");
                title.setText(text2[0]);

                String finalVTitle = vTitle;
                anotherContainer.getScene().widthProperty().addListener((observableValue1, number, t1) -> {
                    Scene scene = anotherContainer.getScene();
                    double width = scene.getWidth();

                    contentContainer.setPrefWidth(width - 500);

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
                            String [] text = finalVTitle.split(" ");
                            title.setText(text[0]);
                            isLoadedSmall = true;
                        }
                    }
                });

                Firmware firmware = hw.getComputerSystem().getFirmware();
                firmwareName.setText(vFirmwareName + " " + firmware.getName());
                firmwareDescription.setText(vFirmwareDescription + " " + firmware.getDescription());
                firmwareManufacturer.setText(vFirmwareManufacturer + " " + firmware.getManufacturer());
                firmwareReleaseDate.setText(vFirmwareReleaseDate + " " + firmware.getReleaseDate());
                firmwareVersion.setText(vFirmwareVersion + " " + firmware.getVersion());

                List<SoundCard> soundCards = hw.getSoundCards();

                for (SoundCard soundCard : soundCards) {
                    soundCardName.setText(vSoundCardName + " " + soundCard.getName());
                    soundCardDriverV.setText(vSoundCardDriverVersion + " " + soundCard.getDriverVersion());
                    soundCardCodec.setText(vSoundCardCodec + " " + soundCard.getCodec());
                    break;
                }
            }

        }));


    }
}
