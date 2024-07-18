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
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;
import oshi.software.os.OperatingSystem;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

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
    private Text softwareTitle;
    @FXML
    private ScrollPane scrollContainer;
    @FXML
    private HBox contentContainer;

    private boolean isLoadedSmall;
    private boolean isLoadedBig;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu menu = new Menu();
        menu.create(infoContainer);

        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        HardwareAbstractionLayer hw = si.getHardware();
        CentralProcessor cpu = hw.getProcessor();
        GlobalMemory memory = hw.getMemory();
        Sensors sensors = hw.getSensors();
        Timer timer = new Timer();

        infoContainer.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            if (newScene != null) {

                infoContainer.getScene().setUserData(infoContainer);

                infoContainer.getScene().widthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Scene scene = infoContainer.getScene();
                        double width = scene.getWidth();
                        scrollContainer.setPrefWidth(width - 400);
                        contentContainer.setPrefWidth(scrollContainer.getPrefWidth() - 2);
                        String osFamilyType = os.getFamily();
                        if (width > 1600) {
                            isLoadedSmall = false;
                            if (!isLoadedBig) {
                                Image image = null;
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
                            softwareTitle.setText("SOFTWARE INFO");
                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {
                                if (contentContainer.getChildren().size() == 2) {
                                    contentContainer.getChildren().remove(1);
                                }
                                softwareTitle.setText("INFO");
                                isLoadedSmall = true;
                            }
                        }
                    }
                });
            }
        }));

        familyText.setText("Family: " + os.getFamily());
        versionText.setText("Version: " + os.getVersionInfo());
        bitnessText.setText("Bitness: " + os.getBitness());
        manuFacturerText.setText("Manu facturer: " + os.getManufacturer());
        processCountText.setText("Process count: " + os.getProcessCount());
        isElevatedText.setText("Is elevated: " + os.isElevated());
        deskopCountText.setText("Open deskop window count: " + os.getDesktopWindows(true).size());

    }
}
