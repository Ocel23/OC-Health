package ocel23.me.ochealth.models.languageModels;

public class SoftwareInfo {

    private String title;
    private String family;
    private String version;
    private String bitness;
    private String manufacturer;
    private String processCount;
    private String isElevated;
    private String desktopCount;

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

    public String getBitness() {
        return bitness;
    }

    public void setBitness(String bitness) {
        this.bitness = bitness;
    }

    public String getProcessCount() {
        return processCount;
    }

    public void setProcessCount(String processCount) {
        this.processCount = processCount;
    }

    public String getIsElevated() {
        return isElevated;
    }

    public void setIsElevated(String isElevated) {
        this.isElevated = isElevated;
    }

    public String getDesktopCount() {
        return desktopCount;
    }

    public void setDesktopCount(String desktopCount) {
        this.desktopCount = desktopCount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
