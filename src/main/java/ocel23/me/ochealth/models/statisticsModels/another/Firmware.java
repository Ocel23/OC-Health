package ocel23.me.ochealth.models.statisticsModels.another;

import java.io.Serializable;

public class Firmware implements Serializable {

    private String name;
    private String description;
    private String manufacturer;
    private String releaseDate;
    private String version;

    public Firmware(String name, String description, String manufacturer, String releaseDate, String version) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.releaseDate = releaseDate;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
