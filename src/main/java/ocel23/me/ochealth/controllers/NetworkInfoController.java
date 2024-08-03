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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ocel23.me.ochealth.ConfigHandler;
import ocel23.me.ochealth.LanguageHandler;
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

    private boolean isLoadedSmall;
    private boolean isLoadedBig;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu menu = new Menu();
        menu.create(networkInfoContainer);

        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        HardwareAbstractionLayer hw = si.getHardware();

        networkInfoContainer.sceneProperty().addListener((observableValue, scene, t1) -> {

            if (scene != null) {

                networkInfoContainer.getScene().setUserData(networkInfoContainer);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                String language = configHandler.getSettingsFromConfig().getLanguage();

                String ivp4Add = "Ipv4 address:";
                String ivp4Gate = "Ipv4 gateway:";
                String ivp6Add = "Ipv6 address:";
                String ivp6Gate = "Ipv6 gateway:";
                String macAdd = "Mac address:";
                String vInterface = "Interface:";
                String vSpeed = "Speed:";
                String vDns = "DNS servers:";


                if (language.equalsIgnoreCase("Czech")) {
                    ivp4Add = languageHandler.getLanguageValues().getNetworkInfo().getIpv4address();
                    ivp6Add = languageHandler.getLanguageValues().getNetworkInfo().getIpv6address();
                    ivp4Gate = languageHandler.getLanguageValues().getNetworkInfo().getIpv4gateway();
                    ivp6Gate = languageHandler.getLanguageValues().getNetworkInfo().getIpv6gateway();
                    macAdd = languageHandler.getLanguageValues().getNetworkInfo().getMacAddress();
                    vInterface = languageHandler.getLanguageValues().getNetworkInfo().get_interface();
                    vSpeed = languageHandler.getLanguageValues().getNetworkInfo().getSpeed();
                    vDns = languageHandler.getLanguageValues().getNetworkInfo().getDnsServers();
                }

                networkInfoContainer.widthProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Scene scene = networkInfoContainer.getScene();
                        double width = scene.getWidth();

                        if (width > 1600) {
                            isLoadedSmall = false;
                            if (!isLoadedBig) {
                                Image image = null;
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
                            title.setText("NETWORK INFO");
                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {
                                if (contentContainer.getChildren().size() == 2) {
                                    contentContainer.getChildren().remove(1);
                                }
                            }
                            title.setText("INFO");
                            isLoadedSmall = true;
                        }
                    }
                });

                for (NetworkIF networkIF : hw.getNetworkIFs()) {
                    String ipv4 = "";
                    for (int j = 0; j < networkIF.getIPv4addr().length; j++) {
                        ipv4 += networkIF.getIPv4addr()[j];
                        if (j == networkIF.getIPv4addr().length - 1) continue;
                        ipv4 += ",";
                    }
                    ipv4Address.setText(ivp4Add + " " + ipv4);

                    String ipv6 = "";
                    for (int k = 0; k < networkIF.getIPv6addr().length; k++) {
                        ipv6 += networkIF.getIPv6addr()[k];
                        if (k == networkIF.getIPv6addr().length - 1) continue;
                        ipv6 += ",";
                    }
                    ipv6Address.setText(ivp6Add + " " + ipv6);

                    double speed2 = (double) networkIF.getSpeed() / 1000000;
                    speed2 = Math.floor(speed2 * 100) / 100;
                    speed.setText(vSpeed + " " + speed2 + "Mbps");

                    interface1.setText(vInterface + " " + networkIF.getName());

                    macAddress.setText(macAdd + " " + networkIF.getMacaddr());

                }

                String dnsServers = "";

                for (int i = 0; i < os.getNetworkParams().getDnsServers().length; i++) {
                    dnsServers += os.getNetworkParams().getDnsServers()[i];
                    if (i == os.getNetworkParams().getDnsServers().length - 1) continue;
                    dnsServers += ",";
                }

                dns.setText(vDns + " " + dnsServers);

                ipv4Gateway.setText(ivp4Gate + " " + os.getNetworkParams().getIpv4DefaultGateway());

                String ipv6Gateway2 = os.getNetworkParams().getIpv6DefaultGateway();

                if (os.getNetworkParams().getIpv6DefaultGateway().equalsIgnoreCase("")) {
                    ipv6Gateway2 = "None";
                }

                ipv6Gateway.setText(ivp6Gate + " " + ipv6Gateway2);
            }




        });
    }
}
