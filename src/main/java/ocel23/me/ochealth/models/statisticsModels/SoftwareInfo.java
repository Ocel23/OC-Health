package ocel23.me.ochealth.models.statisticsModels;

import java.io.Serializable;

public class SoftwareInfo implements Serializable {

    private String family;
    private String version;
    private int bitness;
    private String manufacturer;
    private int countOfProcesses;
    private boolean isElevated;
    private int openDesktopWindowsCount;

    public SoftwareInfo(String family, String version, int bitness, String manufacturer, int countOfProcesses, boolean isElevated, int openDesktopWindowsCount) {
        this.family = family;
        this.version = version;
        this.bitness = bitness;
        this.manufacturer = manufacturer;
        this.countOfProcesses = countOfProcesses;
        this.isElevated = isElevated;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getBitness() {
        return bitness;
    }

    public void setBitness(int bitness) {
        this.bitness = bitness;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCountOfProcesses() {
        return countOfProcesses;
    }

    public void setCountOfProcesses(int countOfProcesses) {
        this.countOfProcesses = countOfProcesses;
    }

    public boolean isElevated() {
        return isElevated;
    }

    public void setElevated(boolean elevated) {
        isElevated = elevated;
    }

    public int getOpenDesktopWindowsCount() {
        return openDesktopWindowsCount;
    }

    public void setOpenDesktopWindowsCount(int openDesktopWindowsCount) {
        this.openDesktopWindowsCount = openDesktopWindowsCount;
    }
}
