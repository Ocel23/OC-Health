package ocel23.me.ochealth.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;

import java.net.URL;
import java.util.ResourceBundle;

public class NetworkUseController implements Initializable {

    @FXML
    private BorderPane netUseContainer;
    @FXML
    private WebView networkEmbed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        netUseContainer.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {

                Menu menu = new Menu();
                menu.create(netUseContainer);

                networkEmbed.getEngine().load("https://www.metercustom.net/plugin/?hl=en");
            }
        });
        
    }
}
