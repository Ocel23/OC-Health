package ocel23.me.ochealth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 *This class provide core functions of app
 *like settings basic settings of app and loading files
 * @author Ocel23
 * @version 1.0
 * @since 2024-05-09
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        try {

            //create files in user home directory for config, logs, statistics
            String path = System.getProperty("user.home") + File.separator + "Oc-Health";
            File customDir = new File(path);

            File logs = new File(customDir.getAbsolutePath() + "/logs.txt");
            logs.createNewFile();
            File statistics = new File(customDir.getAbsolutePath() + "/statistics.txt");
            statistics.createNewFile();

            File config = new File(customDir.getAbsolutePath() + "/config.yaml");

            if (!(config.exists())) {
                FileUtils.copyFile(new File(Main2.class.getResource("config.yaml").toURI().getPath()), config);
            }

            ConfigHandler configHandler = new ConfigHandler();

            Parent root;

            String section = configHandler.getSettingsFromConfig().getDefaultSectionOnStart();

            //load document fxml by if, value gets from config
            if (section.equalsIgnoreCase("Home")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Main.fxml"));
            } else if (section.equalsIgnoreCase("Devices")) {
                root =  FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Devices.fxml"));
            } else if (section.equalsIgnoreCase("HardwareInfo")){
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/HardwareInfo.fxml"));
            } else if (section.equalsIgnoreCase("HardwareUse")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/HardwareUse.fxml"));
            } else if (section.equalsIgnoreCase("Another")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/AnotherComponents.fxml"));
            } else if (section.equalsIgnoreCase("NetworkInfo")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/NetworkInfo.fxml"));
            } else if (section.equalsIgnoreCase("NetworkUse")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/NetworkUse.fxml"));
            } else if (section.equalsIgnoreCase("SoftwareInfo")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/SoftwareInfo.fxml"));
            } else if (section.equalsIgnoreCase("SoftwareUse")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/SotwareUse.fxml"));
            } else if (section.equalsIgnoreCase("PowerSource")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/PowerSource.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Main.fxml"));
            }

            Scene scene = new Scene(root);

            //loading fonts
            Font.loadFont(this.getClass().getResource("fonts/Poppins-Regular.ttf").openStream(), 16);
            Font.loadFont(getClass().getResource("fonts/Poppins-Medium.ttf").openStream(), 16);
            Font.loadFont(getClass().getResource("fonts/Poppins-Bold.ttf").openStream(), 16);

            String css = this.getClass().getResource("application.css").toExternalForm();

            scene.getStylesheets().add(css);

            /*
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(800);
            */

            primaryStage.setTitle("OC Health");
            primaryStage.getIcons().add(new Image(getClass().getResource("/ocel23/me/ochealth/images/logo-oc-health.jpg").openStream()));
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

