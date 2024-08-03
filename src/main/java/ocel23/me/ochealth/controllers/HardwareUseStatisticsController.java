package ocel23.me.ochealth.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ocel23.me.ochealth.ConfigHandler;
import ocel23.me.ochealth.LanguageHandler;
import ocel23.me.ochealth.models.Menu;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class HardwareUseStatisticsController implements Initializable {

    @FXML
    private BorderPane hardwareStatisticsUseContainer;
    @FXML
    private GridPane gridContainer;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu menu = new Menu();
        menu.create(hardwareStatisticsUseContainer);

        hardwareStatisticsUseContainer.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                hardwareStatisticsUseContainer.getScene().setUserData(hardwareStatisticsUseContainer);

                LanguageHandler languageHandler = new LanguageHandler();

                ConfigHandler configHandler = new ConfigHandler();

                String language = configHandler.getSettingsFromConfig().getLanguage();

                XYChart.Series<Number, Number> series = new XYChart.Series();

                chart.getData().add(series);

                Timer timer = new Timer();

                SystemInfo si = new SystemInfo();
                CentralProcessor cpu = si.getHardware().getProcessor();
                GlobalMemory memory = si.getHardware().getMemory();

                String intervalValue = configHandler.getSettingsFromConfig().getCollectStatisticsDataInterval();

                long interval = 0;

                if (intervalValue.equalsIgnoreCase("1  minute")) {
                    interval = 60000L;
                } else if (intervalValue.equalsIgnoreCase("5 minutes")) {
                    interval = 3600000L;
                } else if (intervalValue.equalsIgnoreCase("15 minutes")) {
                    interval = 10800000L;
                } else if (intervalValue.equalsIgnoreCase("30 minutes")) {
                    interval = 21600000L;
                } else {
                    interval = 1000L;
                }

                if (configHandler.getSettingsFromConfig().isCollectStatisticData()) {
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            FileWriter writer;
                            try {
                                writer = new FileWriter(Paths.get(getClass().getResource("/ocel23/me/ochealth/statistics.txt").toURI()).toFile());
                            } catch (IOException | URISyntaxException e) {
                                throw new RuntimeException(e);
                            }

                            try {
                                double cpuUsage = cpu.getSystemCpuLoad(1000L) * 100;
                                cpuUsage = (double) Math.round(cpuUsage * 100) / 100;
                                double totalMemory = (double) memory.getTotal() / 1073741824;
                                totalMemory = (double) Math.round(totalMemory * 100) / 100;
                                double availableMemory = (double) memory.getAvailable() / 1073741824;
                                availableMemory = (double) Math.round(availableMemory * 100) / 100;
                                double currentMemory = totalMemory - availableMemory;
                                currentMemory = (double) Math.round(currentMemory * 100) / 100;
                                writer.write("[HARDWARE] Memory Gb is " + currentMemory + " (" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
                                writer.write("[HARDWARE] Processor Ghz is " + cpuUsage + " (" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, 0L, interval);
                }

                latestTimer = new Timer();

                latestTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        updateRam(series, memory);
                    }
                }, 0L, 1000L);


                cpuButton.setOnAction(event -> {
                    latestTimer.cancel();
                    latestTimer = new Timer();
                    resetData(series);
                    chart.getYAxis().setLabel("GHZ");
                    chart.setTitle("PROCESSOR USE");
                    time = 1;
                    latestTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            updateCpu(series, cpu);
                        }
                    }, 0L, 1000L);
                });

                ramButton.setOnAction(event -> {
                    latestTimer.cancel();
                    latestTimer = new Timer();
                    resetData(series);
                    chart.getYAxis().setLabel("GB");
                    chart.setTitle("MEMORY USE");
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
            }
        });
    }

    private void updateRam(XYChart.Series<Number, Number> series, GlobalMemory memory) {
        double totalMemory = (double) memory.getTotal() / 1073741824;
        totalMemory = (double) Math.round(totalMemory * 100) / 100;
        double availableMemory = (double) memory.getAvailable() / 1073741824;
        availableMemory = (double) Math.round(availableMemory * 100) / 100;
        double currentMemory = totalMemory - availableMemory;
        currentMemory = (double) Math.round(currentMemory * 100) / 100;
        updateData(series, currentMemory);
    }


    private void resetData(XYChart.Series<Number, Number> series) {
        series.getData().clear();
    }

    private void updateData(XYChart.Series<Number, Number> series, double value) {
        if (time < 60) {
            time++;
        }
        if (time >= 60) {
            int i = 0;
            series.getData().add(new XYChart.Data<>(time, value));
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

    private void updateCpu(XYChart.Series<Number, Number> series, CentralProcessor cpu) {
        double cpuUsage = cpu.getSystemCpuLoad(1000L) * 100;
        cpuUsage = (double) Math.round(cpuUsage * 100) / 100;
        updateData(series, cpuUsage);
    }
}
