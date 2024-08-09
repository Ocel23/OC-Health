package ocel23.me.ochealth.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SoftwareInfoController implements Initializable {

    @FXML
    private BorderPane infoContainer;

    @FXML
    private Text familyText;
    @FXML
    private Text versionText;
    @FXML
    private Text bitnessText;
    @FXML
    private Text manuFacturerText;
    @FXML
    private Text processCountText;
    @FXML
    private Text isElevatedText;
    @FXML
    private Text deskopCountText;

    @FXML
    private Text title;
    @FXML
    private ScrollPane scrollContainer;
    @FXML
    private HBox contentContainer;

    private boolean isLoadedSmall = true;
    private boolean isLoadedBig = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        infoContainer.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            if (newScene != null) {

                Menu menu = new Menu();
                menu.create(infoContainer);

                SystemInfo si = new SystemInfo();
                OperatingSystem os = si.getOperatingSystem();

                infoContainer.getScene().setUserData(infoContainer);

                infoContainer.getScene().widthProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Scene scene = infoContainer.getScene();
                        double width = scene.getWidth();
                        scrollContainer.setPrefWidth(width - 400);
                        contentContainer.setPrefWidth(scrollContainer.getPrefWidth() - 2);


                        LanguageHandler languageHandler = new LanguageHandler();

                        ConfigHandler configHandler = new ConfigHandler();

                        String language = configHandler.getSettingsFromConfig().getLanguage();

                        String vFamily = "Family:";
                        String vVersion = "Version:";
                        String vBitness = "Bitness:";
                        String vManufacturer = "Manufacturer:";
                        String vProcessCount = "Process count:";
                        String vIsElevated = "Is elevated:";
                        String vDeskopCount = "Open deskop window count:";
                        String vTitle = "SOFTWARE INFO:";

                        if (language.equalsIgnoreCase("Czech")) {
                            vTitle = languageHandler.getLanguageValues().getSoftwareInfo().getTitle();
                            vFamily = languageHandler.getLanguageValues().getSoftwareInfo().getFamily();
                            vVersion = languageHandler.getLanguageValues().getSoftwareInfo().getVersion();
                            vBitness = languageHandler.getLanguageValues().getSoftwareInfo().getBitness();
                            vManufacturer = languageHandler.getLanguageValues().getSoftwareInfo().getManufacturer();
                            vProcessCount = languageHandler.getLanguageValues().getSoftwareInfo().getProcessCount();
                            vIsElevated = languageHandler.getLanguageValues().getSoftwareInfo().getIsElevated();
                            vDeskopCount = languageHandler.getLanguageValues().getSoftwareInfo().getDesktopCount();
                        }

                        familyText.setText(vFamily + " " + os.getFamily());
                        versionText.setText(vVersion + " " + os.getVersionInfo());
                        bitnessText.setText(vBitness + " " + os.getBitness());
                        manuFacturerText.setText(vManufacturer + " " + os.getManufacturer());
                        processCountText.setText(vProcessCount + " " + os.getProcessCount());
                        isElevatedText.setText(vIsElevated + " " + os.isElevated());
                        deskopCountText.setText(vDeskopCount + " " + os.getDesktopWindows(true).size());

                        String [] text4 = vTitle.split(" ");
                        title.setText(text4[0]);

                        String osFamilyType = os.getFamily();
                        if (width > 1600) {
                            isLoadedSmall = false;
                            if (!isLoadedBig) {
                                Image image;
                                try {
                                    if (osFamilyType.equalsIgnoreCase("Windows")) {
                                        image = new Image(getClass().getResource("/ocel23/me/ochealth/images/windowsIcon.png").toURI().toString());
                                    } else if (osFamilyType.equalsIgnoreCase("Linux")) {
                                        image = new Image(getClass().getResource("/ocel23/me/ochealth/images/linuxIcon.png").toURI().toString());
                                    } else if (osFamilyType.equalsIgnoreCase("MacOS")) {
                                        image = new Image(getClass().getResource("/ocel23/me/ochealth/images/macOSIcon.png").toURI().toString());
                                    } else {
                                        image = new Image(getClass().getResource("/ocel23/me/ochealth/images/questionMark.png").toURI().toString());
                                    }
                                } catch (URISyntaxException e) {
                                    throw new RuntimeException(e);
                                }
                                ImageView imageView = new ImageView(image);
                                imageView.setFitHeight(400);
                                imageView.setFitWidth(400);
                                contentContainer.getChildren().add(imageView);
                                isLoadedBig = true;
                            }

                        } else if (width > 1200) {
                            title.setText(vTitle);
                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {
                                if (contentContainer.getChildren().size() == 2) {
                                    contentContainer.getChildren().remove(1);
                                }
                                String [] text = vTitle.split(" ");
                                title.setText(text[0]);
                                isLoadedSmall = true;
                            }
                        }
                    }
                });
            }
        }));

    }
}
