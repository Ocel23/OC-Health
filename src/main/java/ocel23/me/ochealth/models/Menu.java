package ocel23.me.ochealth.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Menu {

    public void create(BorderPane pane) {
        try {
            pane.setTop(FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Navbar.fxml")));
            pane.setLeft(FXMLLoader.load(getClass().getResource("/ocel23/me/ochealth/views/Sidebar.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
