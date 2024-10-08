package ocel23.me.ochealth.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import ocel23.me.ochealth.models.Process;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SoftwareUseController implements Initializable {

    @FXML
    private BorderPane softwareUseContainer;

    @FXML
    private TableColumn<Process, String> nameColumn;
    @FXML
    private TableColumn<Process, String> cpuColumn;
    @FXML
    private TableColumn<Process, String> memoryColumn;
    @FXML
    private TableColumn<Process, String> stateColumn;
    @FXML
    private TableView<Process> table;
    @FXML
    private ScrollPane scrollContainer;
    @FXML
    private Text title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        softwareUseContainer.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            //if for prevent wrong loading of elements
            if (newScene != null) {

                //create sidebar for app
                Menu menu = new Menu();
                menu.create(softwareUseContainer);

                //pass container for function sidebar
                softwareUseContainer.getScene().setUserData(softwareUseContainer);

                SystemInfo si = new SystemInfo();
                OperatingSystem os = si.getOperatingSystem();
                HardwareAbstractionLayer hw = si.getHardware();
                GlobalMemory memory = hw.getMemory();

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                //handling language values
                String language = configHandler.getSettingsFromConfig().getLanguage();

                String vName = "Name:";
                String vCpu = "CPU:";
                String vMemory = "Memory:";
                String vState = "State:";
                String vTitle = "SOFTWARE USE";

                if (language.equalsIgnoreCase("Czech")) {
                    vTitle = languageHandler.getLanguageValues().getSoftwareInfo().getTitle();
                    vName = languageHandler.getLanguageValues().getSoftwareUse().getName();
                    vCpu = languageHandler.getLanguageValues().getSoftwareUse().getProcessor();
                    vMemory = languageHandler.getLanguageValues().getSoftwareUse().getMemory();
                    vState = languageHandler.getLanguageValues().getSoftwareUse().getState();
                }

                //set values for texts
                nameColumn.setText(vName);
                cpuColumn.setText(vCpu);
                memoryColumn.setText(vMemory);
                stateColumn.setText(vState);

                String [] text2 = vTitle.split(" ");
                title.setText(text2[0]);

                String finalVTitle = vTitle;
                //change elements by screen width
                softwareUseContainer.getScene().widthProperty().addListener((observableValue1, number, t1) -> {
                    Scene scene = softwareUseContainer.getScene();
                    double width = scene.getWidth();

                    scrollContainer.setPrefWidth(width - 500);
                    table.setPrefWidth(width - 700 + 500);
                    if (width > 1200) {
                        title.setText(finalVTitle);
                    } else if (width < 1200) {
                        String [] text = finalVTitle.split(" ");
                        title.setText(text[0]);
                    }
                });

                List<OSProcess> processes = os.getProcesses();

                //write logs to logs file
                FileWriter writer;

                String path = System.getProperty("user.home") + File.separator + "Oc-Health";
                File customDir = new File(path);
                File logsFile = new File(customDir.getAbsolutePath() + "/logs.txt");

                try {
                    writer = new FileWriter(logsFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    writer.write("[SOFTWARE] Already running " + processes.size() + "processes");
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //create table data and display them for user
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                cpuColumn.setCellValueFactory(new PropertyValueFactory<>("cpuUsage"));
                memoryColumn.setCellValueFactory(new PropertyValueFactory<>("memoryUsage"));
                stateColumn.setCellValueFactory(new PropertyValueFactory<>("processState"));
                for (OSProcess process : processes) {
                    double totalMemory = (double) memory.getTotal() / 1073741824;
                    totalMemory = (double) Math.round(totalMemory * 100) / 100;
                    double cpuUsage = process.getProcessCpuLoadCumulative() * 100;
                    cpuUsage = (double) Math.round(cpuUsage * 100) / 100;
                    double currentMemory = (double) process.getVirtualSize() / 1073741824;
                    double ramUsage = (currentMemory / totalMemory) * 100;
                    ramUsage = (double) Math.round(ramUsage * 100) / 100;
                    String cpuString = cpuUsage + "%";
                    String memoryString = ramUsage + "%";
                    table.getItems().add(new Process(process.getName(), cpuString, memoryString, process.getState()));
                }
            }
        }));


    }
}
