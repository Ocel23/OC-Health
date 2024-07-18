package ocel23.me.ochealth.controllers;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ocel23.me.ochealth.models.Navbar;

import java.net.URL;
import java.util.ResourceBundle;

public class NavbarController implements Initializable {

    private Navbar navbar;

    @FXML
    private ImageView navbarIcon;

    @FXML
    public void toggleSideBar() {

        if (navbarIcon.getRotate() == -90) {
            navbar.showSideBar(navbarIcon);
            RotateTransition rotate = new RotateTransition();
            rotate.setDuration(Duration.millis(500));
            rotate.setFromAngle(-90);
            rotate.setToAngle(0);
            rotate.setCycleCount(1);
            rotate.setNode(navbarIcon);
            rotate.play();
        } else {
            navbar.hideSideBar(navbarIcon);
            RotateTransition rotate2 = new RotateTransition();
            rotate2.setDuration(Duration.millis(500));
            rotate2.setFromAngle(0);
            rotate2.setToAngle(-90);
            rotate2.setCycleCount(1);
            rotate2.setNode(navbarIcon);
            rotate2.play();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navbar = new Navbar();
    }
}
