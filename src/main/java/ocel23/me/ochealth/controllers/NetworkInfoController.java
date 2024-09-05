package ocel23.me.ochealth.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class NetworkInfoController implements Initializable {

    @FXML
    private BorderPane networkInfoContainer;
    @FXML
    private Text ipv4Address;
    @FXML
    private Text ipv4Gateway;
    @FXML
    private Text ipv6Address;
    @FXML
    private Text ipv6Gateway;
    @FXML
    private Text macAddress;
    @FXML
    private Text interface1;
    @FXML
    private Text speed;
    @FXML
    private Text dns;

    @FXML
    private Text title;
    @FXML
    private HBox contentContainer;

    private boolean isLoadedSmall = true;
    private boolean isLoadedBig = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        networkInfoContainer.sceneProperty().addListener((observableValue, oldScene, newScene) -> {

            //if for prevent wrong loading of elements
            if (newScene != null) {

                //create sidebar for app
                Menu menu = new Menu();
                menu.create(networkInfoContainer);

                //pass container for function sidebar
                networkInfoContainer.getScene().setUserData(networkInfoContainer);

                SystemInfo si = new SystemInfo();
                OperatingSystem os = si.getOperatingSystem();
                HardwareAbstractionLayer hw = si.getHardware();

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                //handling language values
                String language = configHandler.getSettingsFromConfig().getLanguage();

                String vIpv4Address = "Ipv4 address:";
                String vIpv4Gateway = "Ipv4 gateway:";
                String vIpv6Address = "Ipv6 address:";
                String vIpv6Gateway = "Ipv6 gateway:";
                String vMacAddress = "Mac address:";
                String vInterface = "Interface:";
                String vSpeed = "Speed:";
                String vDns = "DNS servers:";

                String vTitle = "NETWORK INFO";

                if (language.equalsIgnoreCase("Czech")) {
                    vTitle = languageHandler.getLanguageValues().getNetworkInfo().getTitle();
                    vIpv4Address = languageHandler.getLanguageValues().getNetworkInfo().getIpv4address();
                    vIpv6Address = languageHandler.getLanguageValues().getNetworkInfo().getIpv6address();
                    vIpv4Gateway = languageHandler.getLanguageValues().getNetworkInfo().getIpv4gateway();
                    vIpv6Gateway= languageHandler.getLanguageValues().getNetworkInfo().getIpv6gateway();
                    vMacAddress = languageHandler.getLanguageValues().getNetworkInfo().getMacAddress();
                    vInterface = languageHandler.getLanguageValues().getNetworkInfo().get_interface();
                    vSpeed = languageHandler.getLanguageValues().getNetworkInfo().getSpeed();
                    vDns = languageHandler.getLanguageValues().getNetworkInfo().getDnsServers();
                }

                String finalVTitle = vTitle;

                String [] text = finalVTitle.split(" ");
                title.setText(text[0]);

                //change elements by screen width
                networkInfoContainer.widthProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Scene scene = networkInfoContainer.getScene();
                        double width = scene.getWidth();

                        if (width > 1600) {
                            isLoadedSmall = false;
                            if (!isLoadedBig) {
                                Image image;
                                try {
                                    image = new Image(getClass().getResource("/ocel23/me/ochealth/images/networkIcon.png").toURI().toString());
                                } catch (URISyntaxException e) {
                                    throw new RuntimeException(e);
                                }
                                ImageView imageView = new ImageView(image);
                                imageView.setFitWidth(512);
                                imageView.setFitHeight(512);
                                contentContainer.getChildren().add(imageView);
                                isLoadedBig = true;
                            }
                        } else if (width > 1200) {
                            title.setText(finalVTitle);
                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {
                                if (contentContainer.getChildren().size() == 2) {
                                    contentContainer.getChildren().remove(1);
                                }
                                String [] text = finalVTitle.split(" ");
                                title.setText(text[0]);
                                isLoadedSmall = true;
                            }
                        }
                    }
                });

                //set values for texts
                for (NetworkIF networkIF : hw.getNetworkIFs()) {
                    StringBuilder ipv4 = new StringBuilder();
                    for (int j = 0; j < networkIF.getIPv4addr().length; j++) {
                        ipv4.append(networkIF.getIPv4addr()[j]);
                        if (j == networkIF.getIPv4addr().length - 1) continue;
                        ipv4.append(",");
                    }
                    ipv4Address.setText(vIpv4Address + " " + ipv4);

                    StringBuilder ipv6 = new StringBuilder();
                    for (int k = 0; k < networkIF.getIPv6addr().length; k++) {
                        ipv6.append(networkIF.getIPv6addr()[k]);
                        if (k == networkIF.getIPv6addr().length - 1) continue;
                        ipv6.append(",");
                    }
                    ipv6Address.setText(vIpv6Address + " " + ipv6);

                    double speed2 = (double) networkIF.getSpeed() / 1000000;
                    speed2 = Math.floor(speed2 * 100) / 100;
                    speed.setText(vSpeed + " " + speed2 + "Mbps");

                    interface1.setText(vInterface + " " + networkIF.getName());

                    macAddress.setText(vMacAddress + " " + networkIF.getMacaddr());

                }

                StringBuilder dnsServers = new StringBuilder();

                for (int i = 0; i < os.getNetworkParams().getDnsServers().length; i++) {
                    dnsServers.append(os.getNetworkParams().getDnsServers()[i]);
                    if (i == os.getNetworkParams().getDnsServers().length - 1) continue;
                    dnsServers.append(",");
                }

                dns.setText(vDns + " " + dnsServers);

                ipv4Gateway.setText(vIpv4Gateway + " " + os.getNetworkParams().getIpv4DefaultGateway());

                String ipv6Gateway2 = os.getNetworkParams().getIpv6DefaultGateway();

                if (os.getNetworkParams().getIpv6DefaultGateway().equalsIgnoreCase("")) {
                    ipv6Gateway2 = "None";
                }

                ipv6Gateway.setText(vIpv6Gateway + " " + ipv6Gateway2);
            }




        });
    }
}
