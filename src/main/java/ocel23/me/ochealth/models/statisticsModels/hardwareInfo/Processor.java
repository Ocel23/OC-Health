package ocel23.me.ochealth.models.statisticsModels.hardwareInfo;

import java.io.Serializable;

public class Processor implements Serializable {

    private String model;
    private String family;
    private String name;
    private String vendor;
    private int frequency;
    private int coreCount;
    private String microArchitecture;

    public Processor(String model, String family, String name, String vendor, int frequency, int coreCount) {
        this.model = model;
        this.family = family;
        this.name = name;
        this.vendor = vendor;
        this.frequency = frequency;
        this.coreCount = coreCount;
    }

    public String getMicroArchitecture() {
        return microArchitecture;
    }

    public void setMicroArchitecture(String microArchitecture) {
        this.microArchitecture = microArchitecture;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
