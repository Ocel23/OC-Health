package ocel23.me.ochealth.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ocel23.me.ochealth.Utils;
import ocel23.me.ochealth.fileHandlers.ConfigHandler;
import ocel23.me.ochealth.fileHandlers.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HardwareUseStatisticsController implements Initializable {

    @FXML
    private BorderPane hardwareStatisticsUseContainer;
    @FXML
    private Text title;
    @FXML
    private Button cpuButton;
    @FXML
    private Button ramButton;
    @FXML
    private Button gpuButton;
    @FXML
    private StackedAreaChart<Number, Number> chart;

    private int time = 1;

    private Timer latestTimer = null;

    private boolean isLoadedSmall = true;
    private boolean isLoadedBig = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        hardwareStatisticsUseContainer.sceneProperty().addListener((observable, oldScene, newScene) -> {

            //if for prevent wrong loading of elements
            if (newScene != null) {

                //create sidebar for app
                Menu menu = new Menu();
                menu.create(hardwareStatisticsUseContainer);

                //pass container for function sidebar
                hardwareStatisticsUseContainer.getScene().setUserData(hardwareStatisticsUseContainer);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                String language = configHandler.getSettingsFromConfig().getLanguage();

                XYChart.Series<Number, Number> series = new XYChart.Series<>();

                chart.getData().add(series);

                Timer timer = new Timer();

                SystemInfo si = new SystemInfo();
                CentralProcessor cpu = si.getHardware().getProcessor();
                GlobalMemory memory = si.getHardware().getMemory();

                long interval = Utils.getInterval(configHandler);

                //handling language values
                String vTitle = "HARDWARE STATISTICS";
                String vProcessor = "PROCESSOR USE";
                String vMemory = "MEMORY USE";

                if (language.equalsIgnoreCase("Czech")) {
                    vTitle = languageHandler.getLanguageValues().getHardwareStatistics().getTitle();
                    vProcessor = languageHandler.getLanguageValues().getHardwareStatistics().getProcessor();
                    vMemory = languageHandler.getLanguageValues().getHardwareStatistics().getMemory();
                }

                String finalVTitle = vTitle;
                String [] text2 = vTitle.split(" ");

                chart.setTitle(vMemory);

                title.setText(text2[0]);

                latestTimer = new Timer();

                //timer which update on start ram chart
                latestTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        updateRam(series, memory);
                    }
                }, 0L, 1000L);

                //buttons event for handle switch type of chart
                String finalVProcessor = vProcessor;
                cpuButton.setOnAction(event -> {
                    latestTimer.cancel();
                    latestTimer = new Timer();
                    resetData(series);
                    chart.getYAxis().setLabel("GHZ");
                    chart.setTitle(finalVProcessor);
                    time = 1;
                    latestTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            updateCpu(series, cpu);
                        }
                    }, 0L, 1000L);
                });

                String finalVMemory = vMemory;
                ramButton.setOnAction(event -> {
                    latestTimer.cancel();
                    latestTimer = new Timer();
                    resetData(series);
                    chart.getYAxis().setLabel("GB");
                    chart.setTitle(finalVMemory);
                    time = 1;
                    latestTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            updateRam(series,memory);
                        }
                    }, 0L, 1000L);
                });

                gpuButton.setOnAction(event -> {

                });

                //change elements by screen width
                hardwareStatisticsUseContainer.widthProperty().addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Scene scene = hardwareStatisticsUseContainer.getScene();
                        double width = scene.getWidth();

                        if (width > 1600) {
                            isLoadedSmall = false;
                            if (!isLoadedBig) {
                                isLoadedBig = true;
                            }
                        } else if (width > 1200) {
                            title.setText(finalVTitle);
                        } else if (width < 1200) {
                            isLoadedBig = false;
                            if (!isLoadedSmall) {
                                String [] text = finalVTitle.split(" ");
                                title.setText(text[0]);
                            }
                            isLoadedSmall = true;
                        }
                    }
                });

                //handle write logs
                if (configHandler.getSettingsFromConfig().isCollectStatisticData()) {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            FileWriter writer;
                            String path = System.getProperty("user.home") + File.separator + "Oc-Health";
                            File customDir = new File(path);
                            File statisticsFile = new File(customDir.getAbsolutePath() + "/statistics.txt");
                            try {
                                writer = new FileWriter(statisticsFile);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            try {
                                double cpuUsage = cpu.getSystemCpuLoad(1000L) * 100;
                                cpuUsage = (double) Math.round(cpuUsage * 100) / 100;
                                getCurrentMemory(memory);
                                writer.write("[HARDWARE] Memory Gb is " + getCurrentMemory(memory) + " (" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
                                writer.write("[HARDWARE] Processor Ghz is " + cpuUsage + " (" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, 0L, interval);
                }
            }
        });
    }

    /**
     * this method get current memory
     * @param memory - object for get values from memory
     * @return current memory
     */
    private double getCurrentMemory(GlobalMemory memory) {
        double totalMemory = (double) memory.getTotal() / 1073741824;
        totalMemory = (double) Math.round(totalMemory * 100) / 100;
        double availableMemory = (double) memory.getAvailable() / 1073741824;
        availableMemory = (double) Math.round(availableMemory * 100) / 100;
        return totalMemory - availableMemory;
    }

    /**
     * this method update ram
     * @param series - piece of chart
     * @param memory - object for get values from memory
     */
    private void updateRam(XYChart.Series<Number, Number> series, GlobalMemory memory) {
        getCurrentMemory(memory);
        updateData(series, getCurrentMemory(memory));
    }

    /**
     * this method reset data
     * @param series - piece of chart
     */
    private void resetData(XYChart.Series<Number, Number> series) {
        series.getData().clear();
    }

    /**
     * this method update for serie
     * @param series - piece of chart
     * @param value
     */
    private void updateData(XYChart.Series<Number, Number> series, double value) {
        if (time < 60) {
            time++;
        }
        series.getData().add(new XYChart.Data<>(time, value));
        if (time >= 60) {
            int i = 0;
            Iterator<XYChart.Data<Number, Number>> iterator = series.getData().iterator();
            while (iterator.hasNext()) {
                XYChart.Data<Number, Number> data = iterator.next();
                if (i == 0) {
                    iterator.remove();
                    i = 1;
                    iterator.next();
                }
                data.setXValue(data.getXValue().intValue() - 1);
            }
        }
    }

    /**
     * this method update cpu
     * @param series - piece of chart
     * @param cpu - object for get values from cpu
     */
    private void updateCpu(XYChart.Series<Number, Number> series, CentralProcessor cpu) {
        double cpuUsage = cpu.getSystemCpuLoad(1000L) * 100;
        cpuUsage = (double) Math.round(cpuUsage * 100) / 100;
        updateData(series, cpuUsage);
    }
}
