package ocel23.me.ochealth.models.statisticsModels;

import java.io.Serializable;

public class PowerSource implements Serializable {

    private int capacity;
    private double powerUsageRate;
    private String deviceName;
    private double temperature;
    private double amperage;
    private double timeRemaining;
    private double voltage;
    private String manufacturer;

    public PowerSource(int capacity, double powerUsageRate, String deviceName, double temperature, double amperage, double timeRemaining, double voltage, String manufacturer) {
        this.capacity = capacity;
        this.powerUsageRate = powerUsageRate;
        this.deviceName = deviceName;
        this.temperature = temperature;
        this.amperage = amperage;
        this.timeRemaining = timeRemaining;
        this.voltage = voltage;
        this.manufacturer = manufacturer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public double getAmperage() {
        return amperage;
    }

    public void setAmperage(double amperage) {
        this.amperage = amperage;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getPowerUsageRate() {
        return powerUsageRate;
    }

    public void setPowerUsageRate(double powerUsageRate) {
        this.powerUsageRate = powerUsageRate;
    }
}
