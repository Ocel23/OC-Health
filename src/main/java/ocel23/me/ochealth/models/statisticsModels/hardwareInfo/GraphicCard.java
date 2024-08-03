package ocel23.me.ochealth.models.statisticsModels.hardwareInfo;

import java.io.Serializable;

public class GraphicCard implements Serializable {

    private String name;
    private String manufacturer;
    private String version;
    private long ram;

    public GraphicCard(String name, String manufacturer, String version, long ram) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.version = version;
        this.ram = ram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRam() {
        return ram;
    }

    public void setRam(long ram) {
        this.ram = ram;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
