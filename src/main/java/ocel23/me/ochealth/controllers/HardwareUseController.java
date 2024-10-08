package ocel23.me.ochealth.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import org.controlsfx.control.Notifications;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HardwareUseController implements Initializable {

    @FXML
    private BorderPane useContainer;
    @FXML
    private Arc cpuCircle;
    @FXML
    private Text cpuPercentUsage;
    @FXML
    private Text cpuUsageValue;
    @FXML
    private Rectangle ramBar;
    @FXML
    private Text ramPercentUsage;
    @FXML
    private Text ramUsageValue;
    @FXML
    private GridPane gridContainer;
    @FXML
    private Rectangle useContentMain;
    @FXML
    private Rectangle useContentMainTopR1;
    @FXML
    private Rectangle useContentMainTopR2;
    @FXML
    private HBox useContentTop;
    @FXML
    private Rectangle useContentMainBottomR1;
    @FXML
    private Rectangle useContentMainBottomR2;
    @FXML
    private HBox useContentBottom;
    @FXML
    private Rectangle useContentDisksR1;
    @FXML
    private Rectangle useContentDisksR2;
    @FXML
    private VBox cpuContainer;
    @FXML
    private VBox ramContainer;
    @FXML
    private VBox gpuContainer;
    @FXML
    private VBox bgContent;
    @FXML
    private Text useTitle;

    private boolean isLoadedBig = false;
    private boolean isLoadedSmall = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        useContainer.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            //if for prevent wrong loading of elements
            if (newScene != null) {

                //create sidebar for app
                Menu menu = new Menu();
                menu.create(useContainer);

                Timer timer = new Timer();
                SystemInfo si = new SystemInfo();
                CentralProcessor cpu = si.getHardware().getProcessor();
                GlobalMemory memory = si.getHardware().getMemory();
                FileSystem fs = si.getOperatingSystem().getFileSystem();
                List<OSFileStore> fileStores = fs.getFileStores();

                //pass container for function sidebar
                useContainer.getScene().setUserData(useContainer);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                //handling language values
                String vTitle = "HARDWARE USE";

                if (configHandler.getSettingsFromConfig().getLanguage().equalsIgnoreCase("Czech")) {
                    vTitle = languageHandler.getLanguageValues().getHardwareUse().getTitle();
                }

                String finalVTitle = vTitle;

                //map disks
                for (OSFileStore fileStore : fileStores) {
                    if (fileStore.getDescription().equalsIgnoreCase("Fixed drive")) {
                        VBox disk = getDisk(fileStore);
                        disk.setPadding(new Insets(150, 0, 0, 0));
                        gridContainer.add(disk, 0, gridContainer.getRowCount());
                    }
                }

                String [] text = vTitle.split(" ");
                useTitle.setText(text[0]);

                //change elements by screen width
                useContainer.getScene().widthProperty().addListener((observableValue1, oldValue, newValue) -> {
                    Scene scene = useContainer.getScene();
                    double width = scene.getWidth();
                    if (width >= 1200) {
                        isLoadedSmall = false;
                        if (!isLoadedBig) {
                            bgContent.setAlignment(Pos.TOP_CENTER);
                            useContentMain.widthProperty().bind(scene.widthProperty().subtract(340).subtract(150));
                            useContentMainTopR1.setWidth(200);
                            useContentMainTopR2.setWidth(200);
                            useContentMainBottomR1.setWidth(200);
                            useContentMainBottomR2.setWidth(200);
                            useContentDisksR1.widthProperty().bind(useContentMain.widthProperty());
                            useContentDisksR1.setHeight(300);
                            gridContainer.getChildren().clear();
                            gridContainer.add(cpuContainer, 1, 0);
                            gridContainer.add(ramContainer, 0, 0);
                            gridContainer.add(gpuContainer, 2, 0);
                            gridContainer.setPadding(new Insets(190, 0, 0, 0));
                            cpuContainer.setPadding(new Insets(0));
                            useContentMain.setHeight(702);
                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.TOP_CENTER);
                            hBox.setSpacing(132);
                            hBox.setPadding(new Insets(150, 0, 0, 0));
                            for (OSFileStore fileStore : fileStores) {
                                if (fileStore.getDescription().equalsIgnoreCase("Fixed drive")) {
                                    VBox disk = getDisk(fileStore);
                                    hBox.getChildren().add(disk);
                                }
                            }
                            gridContainer.add(hBox, 0, 1, 3, 1);
                            for (ColumnConstraints column : gridContainer.getColumnConstraints()) {
                                column.setHalignment(HPos.CENTER);
                                column.setPrefWidth(Region.USE_COMPUTED_SIZE);
                                column.setMaxWidth(Region.USE_PREF_SIZE);
                            }
                            for (RowConstraints row : gridContainer.getRowConstraints()) {
                                row.setValignment(VPos.CENTER);
                                row.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            }
                            useTitle.setText(finalVTitle);
                            isLoadedBig = true;
                        }
                        useContentTop.setSpacing(useContentMain.getWidth() - 400);
                        useContentBottom.setSpacing(useContentMain.getWidth() - 400);
                        useContentDisksR2.setWidth(useContentMain.getWidth() - 20);
                    } else if (width < 1200) {
                        isLoadedBig = false;
                        if (!isLoadedSmall) {
                            isLoadedSmall = true;
                            String [] text2 = finalVTitle.split(" ");
                            useTitle.setText(text2[0]);
                            useContentMain.widthProperty().unbind();
                            useContentMain.setWidth(312);
                            gridContainer.getChildren().clear();
                            gridContainer.add(cpuContainer, 0, 0);
                            gridContainer.add(ramContainer, 0, 1);
                            gridContainer.add(gpuContainer, 0, 2);
                            useContentMainTopR1.setWidth(80);
                            useContentMain.setHeight(1480);
                            useContentMainTopR2.setWidth(80);
                            useContentMainBottomR1.setWidth(80);
                            useContentMainBottomR2.setWidth(80);
                            for (OSFileStore fileStore : fileStores) {
                                if (fileStore.getDescription().equalsIgnoreCase("Fixed drive")) {
                                    VBox disk = getDisk(fileStore);
                                    disk.setPadding(new Insets(100, 0, 50, 0));
                                    gridContainer.add(disk, 0, gridContainer.getRowCount());
                                }
                            }
                            useContentDisksR1.setHeight(500);
                        }
                        useContentTop.setSpacing(useContentMain.getWidth() - 160);
                        useContentBottom.setSpacing(useContentMain.getWidth() - 160);
                        useContentDisksR2.setWidth(useContentMain.getWidth() - 20);
                    }
                });

                long maxFreq = cpu.getMaxFreq();
                double maxFreqConverted = (double) maxFreq / 1000000000;

                //timer for update values of hw components and display them for user
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        double totalMemory = (double) memory.getTotal() / 1073741824;
                        totalMemory = (double) Math.round(totalMemory * 100) / 100;
                        double availableMemory = (double) memory.getAvailable() / 1073741824;
                        availableMemory = (double) Math.round(availableMemory * 100) / 100;
                        double currentMemory = totalMemory - availableMemory;
                        currentMemory = (double) Math.round(currentMemory * 100) / 100;
                        double ramUsage = 100 * (currentMemory / totalMemory);
                        ramUsage = (double) Math.round(ramUsage * 100) / 100;
                        ramPercentUsage.setText(ramUsage + "%");
                        ramBar.setHeight((271 * ramUsage) / 100);
                        ramUsageValue.setText(currentMemory  + "/" + totalMemory + "GB");
                        double cpuUsage = cpu.getSystemCpuLoad(1000L) * 100;
                        cpuUsage = (double) Math.round(cpuUsage * 100) / 100;
                        cpuPercentUsage.setText(cpuUsage + "%");
                        cpuCircle.setLength((360 * cpuUsage) / 100);
                        long [] currentFreq = cpu.getCurrentFreq();
                        double currentFreqConverted = (double) currentFreq[0] / 1000000000;
                        cpuUsageValue.setText(currentFreqConverted + "/" + maxFreqConverted + "GHz");

                        //write data to logs file
                        FileWriter writer;

                        String path = System.getProperty("user.home") + File.separator + "Oc-Health";
                        File customDir = new File(path);
                        File logsFile = new File(customDir.getAbsolutePath() + "/logs.txt");

                        try {
                            writer = new FileWriter(logsFile);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        //ifs for showings warnings notifications
                        if (cpuUsage > 80) {
                            if (configHandler.getSettingsFromConfig().isShowNotificationOnWarningValues()) {
                                Platform.runLater(() -> {
                                    Notifications.create()
                                            .title("Hardware use")
                                            .text("Cpu usage is higher than 80%.")
                                            .showWarning();

                                });
                            }
                            try {
                                writer.write("[HARDWARE] Cpu usage is bigger than 80%! (" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        if (ramUsage > 80) {
                            if (configHandler.getSettingsFromConfig().isShowNotificationOnWarningValues()) {
                                Platform.runLater(() -> {
                                    Notifications.create()
                                            .title("Hardware use")
                                            .text("Memory usage is higher than 80%.")
                                            .showWarning();
                                });
                            }
                            try {
                                writer.write("[HARDWARE] Ram usage is bigger than 80%! (" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        try {
                            writer.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, 0, 1000L);
            }

        }));

    }

    /**
     * this method make one disk and display it for user
     * @param fileStore - object for get one disk
     * @return VBox - container
     */
    private VBox getDisk(OSFileStore fileStore) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(72, 0, 0, 0));
        Text text = new Text(fileStore.getLabel());
        text.getStyleClass().add("hardware--use--disk--title");
        Group barContainer = new Group();
        Rectangle ramBg = new Rectangle();
        ramBg.setHeight(44);
        ramBg.setWidth(212);
        ramBg.setFill(Color.web("#f2f2f2"));
        Rectangle ramFill = new Rectangle();
        ramFill.setHeight(44);
        double freeSpace = (double) fileStore.getFreeSpace() / 1073741824;
        freeSpace = (double) Math.round(freeSpace * 100) / 100;
        double totalSpace = (double) fileStore.getTotalSpace() / 1073741824;
        totalSpace = (double) Math.round(totalSpace * 100) / 100;
        double usageSpace = 100 * (freeSpace / totalSpace);
        usageSpace = (double) Math.round(usageSpace * 100) / 100;
        if (usageSpace <= 60) {
            ramFill.setFill(Color.web("#33e77b"));
        } else if (usageSpace > 60 && usageSpace <= 80) {
            ramFill.setFill(Color.web("#fb8314"));
        } else {
            ramFill.setFill(Color.web("#e04949"));
        }
        ramFill.setWidth((212 * usageSpace) / 100);
        barContainer.getChildren().addAll(ramBg, ramFill);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(12);
        hBox.setPadding(new Insets(6, 0, 0, 0));
        Text text1 = new Text(usageSpace + "%");
        text1.getStyleClass().add("hardware--use--usage--text");
        Text text2 = new Text(freeSpace + "/" + totalSpace + "GB");
        text2.getStyleClass().add("hardware--use--usage--value");
        hBox.getChildren().add(text1);
        hBox.getChildren().add(text2);

        vBox.getChildren().add(text);
        vBox.getChildren().add(barContainer);
        vBox.getChildren().add(hBox);

        return vBox;
    }

}
