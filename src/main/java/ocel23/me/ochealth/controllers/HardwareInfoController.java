package ocel23.me.ochealth.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.PhysicalMemory;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HardwareInfoController implements Initializable {

    @FXML
    private BorderPane hardwareInfoContainer;

    @FXML
    private Text cpuFamily;
    @FXML
    private Text cpuName;
    @FXML
    private Text cpuModel;
    @FXML
    private Text cpuVendor;
    @FXML
    private Text cpuFrequency;
    @FXML
    private Text cpuCore;

    @FXML
    private Text gpuName;
    @FXML
    private Text gpuVendor;
    @FXML
    private Text gpuVersion;
    @FXML
    private Text gpuRam;

    @FXML
    private Text totalMemory;
    @FXML
    private Text virtualMemory;
    @FXML
    private Text memoryCount;
    @FXML
    private Text memoryTypes;

    @FXML
    private Text title;
    @FXML
    private TabPane tabPane;
    @FXML
    private Rectangle tabPaneLine1;
    @FXML
    private Rectangle tabPaneLine2;
    @FXML
    private Rectangle tabPaneLine3;
    @FXML
    private HBox cpuContent;
    @FXML
    private HBox gpuContent;
    @FXML
    private HBox memoryContent;

    private boolean isLoadedSmall = true;
    private boolean isLoadedBig = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        hardwareInfoContainer.sceneProperty().addListener((((observableValue, oldScene, newScene) -> {
            if (newScene != null) {

                Menu menu = new Menu();
                menu.create(hardwareInfoContainer);

                hardwareInfoContainer.getScene().setUserData(hardwareInfoContainer);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                String language = configHandler.getSettingsFromConfig().getLanguage();

                String vCpuCores = "Cores:";
                String vCpuFamily = "Family:";
                String vCpuName = "Name:";
                String vCpuVendor = "Vendor:";
                String vCpuFrequency = "Frequency:";

                String vGpuVendor = "Vendor:";
                String vGpuName = "Name:";
                String vGpuVersion = "Version:";

                String vRamTotal = "Total memory:";
                String vRamVirtual = "Virtual memory:";
                String vRamPhysicalCount = "Physical memory count:";
                String vRamTypesOfMemories = "Types of memories:";

                String vTitle = "HARDWARE INFO";

                if (language.equalsIgnoreCase("Czech")) {
                    vTitle = languageHandler.getLanguageValues().getHardwareInfo().getTitle();
                    vCpuCores = languageHandler.getLanguageValues().getHardwareInfo().getContent().getCpu().getCoreCount();
                    vCpuFamily = languageHandler.getLanguageValues().getHardwareInfo().getContent().getCpu().getFamily();
                    vCpuName = languageHandler.getLanguageValues().getHardwareInfo().getContent().getCpu().getName();
                    vCpuVendor = languageHandler.getLanguageValues().getHardwareInfo().getContent().getCpu().getVendor();
                    vCpuFrequency = languageHandler.getLanguageValues().getHardwareInfo().getContent().getCpu().getFrequency();

                    vGpuVendor = languageHandler.getLanguageValues().getHardwareInfo().getContent().getGpu().getVendor();
                    vGpuName = languageHandler.getLanguageValues().getHardwareInfo().getContent().getGpu().getName();
                    vGpuVersion = languageHandler.getLanguageValues().getHardwareInfo().getContent().getGpu().getVersion();

                    vRamTotal = languageHandler.getLanguageValues().getHardwareInfo().getContent().getRam().getTotalMemory();
                    vRamVirtual = languageHandler.getLanguageValues().getHardwareInfo().getContent().getRam().getVirtualMemory();
                    vRamPhysicalCount = languageHandler.getLanguageValues().getHardwareInfo().getContent().getRam().getPhysicalMemoryCount();
                    vRamTypesOfMemories = languageHandler.getLanguageValues().getHardwareInfo().getContent().getRam().getTypesOfMemories();
                }

                String finalVTitle = vTitle;

                String [] text2 = vTitle.split(" ");
                title.setText(text2[0]);

                hardwareInfoContainer.getScene().widthProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Scene scene = hardwareInfoContainer.getScene();

                        double width = scene.getWidth();

                        if (width > 1600 ) {
                            isLoadedSmall = false;
                            title.setText(finalVTitle);
                            if (!isLoadedBig) {
                                Image image;
                                Image image1;
                                Image image2;
                                try {
                                    image2 = new Image(getClass().getResource("/ocel23/me/ochealth/images/memoryIcon.png").toURI().toString());
                                    image1 = new Image(getClass().getResource("/ocel23/me/ochealth/images/gpuIcon.png").toURI().toString());
                                    image = new Image(getClass().getResource("/ocel23/me/ochealth/images/cpuIcon.png").toURI().toString());
                                } catch (URISyntaxException e) {
                                    throw new RuntimeException(e);
                                }
                                ImageView imageView = new ImageView();
                                imageView.setImage(image);
                                imageView.setFitHeight(365);
                                imageView.setFitWidth(365);
                                ImageView imageView1 = new ImageView();
                                imageView1.setImage(image1);
                                imageView1.setFitWidth(365);
                                imageView1.setFitHeight(365);
                                ImageView imageView2 = new ImageView();
                                imageView2.setImage(image2);
                                imageView2.setFitWidth(365);
                                imageView2.setFitHeight(365);
                                memoryContent.getChildren().add(imageView2);
                                memoryContent.setSpacing(200);
                                cpuContent.getChildren().add(imageView);
                                cpuContent.setSpacing(200);
                                gpuContent.getChildren().add(imageView1);
                                gpuContent.setSpacing(200);
                                isLoadedBig = true;
                            }

                        } else if (width >= 1200) {
                            tabPane.setPrefWidth(width - 400);
                            tabPane.setTabMinWidth((tabPane.getWidth() / 3) - 50);
                            double tabWidth = tabPane.getTabMinWidth();
                            tabPane.setPadding(new Insets(0, 0, 0, 53));
                            tabPaneLine1.setWidth((tabWidth * 3) + 50);
                            tabPaneLine2.setWidth((tabWidth * 3) + 50);
                            tabPaneLine3.setWidth((tabWidth * 3) + 50);
                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {
                                String [] text = finalVTitle.split(" ");
                                title.setText(text[0]);
                                tabPane.setPrefWidth(475);
                                tabPaneLine1.setWidth(384);
                                tabPaneLine2.setWidth(384);
                                tabPaneLine3.setWidth(384);
                                tabPane.setTabMinWidth(115);
                                tabPane.setPadding(new Insets(0, 0, 0, 45));
                                if (cpuContent.getChildren().size() == 2) {
                                    gpuContent.setSpacing(0);
                                    gpuContent.getChildren().remove(1);
                                    cpuContent.setSpacing(0);
                                    cpuContent.getChildren().remove(1);
                                    memoryContent.setSpacing(0);
                                    memoryContent.getChildren().remove(1);
                                }
                                isLoadedSmall = true;
                            }
                        }
                    }
                });

                SystemInfo si = new SystemInfo();
                CentralProcessor cpu = si.getHardware().getProcessor();
                CentralProcessor.ProcessorIdentifier processorIdentifier = cpu.getProcessorIdentifier();

                cpuCore.setText(vCpuCores + " " + cpu.getPhysicalProcessorCount());
                cpuFamily.setText(vCpuFamily + " " + processorIdentifier.getFamily());
                cpuName.setText(vCpuName + " " + processorIdentifier.getName());
                cpuModel.setText("Model: " + processorIdentifier.getModel());
                cpuVendor.setText(vCpuVendor + " " + processorIdentifier.getVendor());
                double maxFreqConverted = (double) processorIdentifier.getVendorFreq() / 1000000000;
                cpuFrequency.setText(vCpuFrequency + " " + maxFreqConverted + "GHz");

                List<GraphicsCard> graphicsCards = si.getHardware().getGraphicsCards();

                for (GraphicsCard gpu : graphicsCards) {
                    gpuName.setText(vGpuName + " " + gpu.getName());
                    gpuVendor.setText(vGpuVendor + " " + gpu.getVendor());
                    gpuVersion.setText(vGpuVersion + " " + gpu.getVersionInfo());
                    double ram = (double) gpu.getVRam() / 1073741824;
                    gpuRam.setText("Ram: " + ram + "GB");
                }

                GlobalMemory memory = si.getHardware().getMemory();

                double total = (double) memory.getTotal() / 1073741824;
                total = (double) Math.round(total * 100) / 100;
                double virtual = (double) memory.getVirtualMemory().getVirtualMax() / 1073741824;
                virtual = (double) Math.round(virtual * 100) / 100;

                int count = 0;
                StringBuilder types = new StringBuilder();

                for (PhysicalMemory physicalMemory : memory.getPhysicalMemory()) {
                    count++;
                    types.append(physicalMemory.getMemoryType()).append(",");
                }

                totalMemory.setText(vRamTotal + " " + total +"GB");
                virtualMemory.setText(vRamVirtual + " " + virtual + "GB");
                memoryCount.setText(vRamPhysicalCount + " " + count);
                memoryTypes.setText(vRamTypesOfMemories + " " + types);
            }
        })));

    }
}
