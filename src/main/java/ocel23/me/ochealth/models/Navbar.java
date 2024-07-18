package ocel23.me.ochealth.models;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ocel23.me.ochealth.controllers.SideBarController;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Navbar {

    public void showSideBar(ImageView navbarIcon) {

        Scene scene1 = navbarIcon.getScene();

        BorderPane borderPane = (BorderPane) scene1.getUserData();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ocel23/me/ochealth/views/Sidebar.fxml"));

        try {
            borderPane.setLeft(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SideBarController sideBarController = fxmlLoader.getController();


        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setDuration(Duration.millis(500));
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.setNode(sideBarController.getSidebar());
        fadeIn.play();

        sideBarController.getSidebar().setPrefWidth(350);
        sideBarController.getSidebar().setMinWidth(350);

    }

    public void hideSideBar(ImageView navbarIcon) {

        Scene scene1 = navbarIcon.getScene();

        BorderPane borderPane = (BorderPane) scene1.getUserData();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ocel23/me/ochealth/views/Sidebar.fxml"));

        try {
            borderPane.setLeft(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SideBarController sideBarController = fxmlLoader.getController();

        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.millis(500));
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
        fadeOut.setNode(sideBarController.getSidebar());
        fadeOut.play();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sideBarController.getSidebar().setPrefWidth(0);
                sideBarController.getSidebar().setMinWidth(0);
            }
        }, 500);

    }
}
