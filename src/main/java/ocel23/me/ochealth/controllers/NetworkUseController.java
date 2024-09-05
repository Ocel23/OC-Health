package ocel23.me.ochealth.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
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
            //if for prevent wrong loading of elements
            if (newScene != null) {

                //create sidebar for app
                Menu menu = new Menu();
                menu.create(netUseContainer);

                //pass container for function sidebar
                netUseContainer.getScene().setUserData(netUseContainer);

                //embed network app from web
                networkEmbed.getEngine().load("https://www.metercustom.net/plugin/?hl=en");
            }
        });
        
    }
}
