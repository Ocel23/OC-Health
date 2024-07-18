package ocel23.me.ochealth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        try {

            ConfigHandler configHandler = new ConfigHandler();

            Parent root;

            String section = configHandler.getSettingsFromConfig().getDefaultSectionOnStart();

            if (section.equalsIgnoreCase("Home")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Main.fxml"));
            } else if (section.equalsIgnoreCase("Devices")) {
                root =  FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Devices.fxml"));
            } else if (section.equalsIgnoreCase("HardwareInfo")){
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/HardwareInfo.fxml"));
            } else if (section.equalsIgnoreCase("HardwareUse")) {
                root = FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Use.fxml.fxml"));
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

            Font.loadFont(this.getClass().getResource("fonts/Poppins-Regular.ttf").openStream(), 16);
            Font.loadFont(getClass().getResource("fonts/Poppins-Medium.ttf").openStream(), 16);
            Font.loadFont(getClass().getResource("fonts/Poppins-Bold.ttf").openStream(), 16);

            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);

            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(980);

            primaryStage.setTitle("OC Health");
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