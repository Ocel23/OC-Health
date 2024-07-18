package ocel23.me.ochealth.models.languageModels;

public class PowerSource {

    private String title;
    private String capacity;
    private String powerUsageRate;
    private String name;
    private String temperature;
    private String amperage;
    private String timeRemaining;
    private String voltage;
    private String manufacturer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPowerUsageRate() {
        return powerUsageRate;
    }

    public void setPowerUsageRate(String powerUsageRate) {
        this.powerUsageRate = powerUsageRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAmperage() {
        return amperage;
    }

    public void setAmperage(String amperage) {
        this.amperage = amperage;
    }
}
