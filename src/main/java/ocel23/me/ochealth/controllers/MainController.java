package ocel23.me.ochealth.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private BorderPane mainContainer;
    @FXML
    private Rectangle tittleBg;
    @FXML
    private Rectangle bottomLine;
    @FXML
    private Text bottomText;
    @FXML
    private Text firstContentArticle;
    @FXML
    private Text secondContentArticle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainContainer.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {

            //if for prevent wrong loading of elements
            if (newScene != null) {

                //create sidebar for app
                Menu menu = new Menu();
                menu.create(mainContainer);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                //pass container for function sidebar
                mainContainer.getScene().setUserData(mainContainer);

                //handling language values
                String language = configHandler.getSettingsFromConfig().getLanguage();

                if (language.equalsIgnoreCase("Czech")) {
                    bottomText.setText(languageHandler.getLanguageValues().getHome().getFooterText());
                    firstContentArticle.setText(languageHandler.getLanguageValues().getHome().getDescription1());
                    secondContentArticle.setText(languageHandler.getLanguageValues().getHome().getDescription2());
                }

                //change elements by screen width
                mainContainer.getScene().widthProperty().addListener((observableValue1, oldValue, newValue) -> {
                    Scene scene = mainContainer.getScene();
                    tittleBg.widthProperty().bind(scene.widthProperty().divide(2));
                    bottomLine.widthProperty().bind(scene.widthProperty().divide(2));
                    secondContentArticle.wrappingWidthProperty().bind(scene.widthProperty().divide(2));
                    firstContentArticle.wrappingWidthProperty().bind(scene.widthProperty().divide(2));
                    if (scene.getWidth() > 1200) {
                        secondContentArticle.setFont(Font.font("Poppins Medium", 31.3));
                        firstContentArticle.setFont(Font.font("Poppins Medium", 31.3));
                        bottomText.setFont(Font.font("Poppins Medium", 25));
                    } else if (scene.getWidth() < 1200) {
                        firstContentArticle.setFont(Font.font("Poppins Medium", 25));
                        secondContentArticle.setFont(Font.font("Poppins Medium", 25));
                        bottomText.setFont(Font.font("Poppins Medium", 20));
                    }
                });
            }
        }));


    }
}