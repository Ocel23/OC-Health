package ocel23.me.ochealth.models;


import ocel23.me.ochealth.models.languageModels.*;
import ocel23.me.ochealth.models.languageModels.Settings;
import ocel23.me.ochealth.models.languageModels.another.Another;
import ocel23.me.ochealth.models.languageModels.hardwareInfo.HardwareInfo;
import ocel23.me.ochealth.models.languageModels.SoftwareInfo;
import ocel23.me.ochealth.models.languageModels.SoftwareUse;
import ocel23.me.ochealth.models.languageModels.sidebar.Sidebar;

public class Language {

    private Another another;
    private PowerSource powerSource;
    private Devices devices;
    private NetworkInfo networkInfo;
    private NetworkUse networkUse;
    private SoftwareUse softwareUse;
    private SoftwareInfo softwareInfo;
    private HardwareInfo hardwareInfo;
    private HardwareUse hardwareUse;
    private HardwareStatistics hardwareStatistics;
    private Settings settings;
    private Home home;
    private Sidebar sidebar;

    public Another getAnother() {
        return another;
    }

    public void setAnother(Another another) {
        this.another = another;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerSource powerSource) {
        this.powerSource = powerSource;
    }

    public Devices getDevices() {
        return devices;
    }

    public void setDevices(Devices devices) {
        this.devices = devices;
    }

    public NetworkInfo getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(NetworkInfo networkInfo) {
        this.networkInfo = networkInfo;
    }

    public NetworkUse getNetworkUse() {
        return networkUse;
    }

    public void setNetworkUse(NetworkUse networkUse) {
        this.networkUse = networkUse;
    }

    public SoftwareUse getSoftwareUse() {
        return softwareUse;
    }

    public void setSoftwareUse(SoftwareUse softwareUse) {
        this.softwareUse = softwareUse;
    }

    public SoftwareInfo getSoftwareInfo() {
        return softwareInfo;
    }

    public void setSoftwareInfo(SoftwareInfo softwareInfo) {
        this.softwareInfo = softwareInfo;
    }

    public HardwareInfo getHardwareInfo() {
        return hardwareInfo;
    }

    public void setHardwareInfo(HardwareInfo hardwareInfo) {
        this.hardwareInfo = hardwareInfo;
    }

    public HardwareUse getHardwareUse() {
        return hardwareUse;
    }

    public void setHardwareUse(HardwareUse hardwareUse) {
        this.hardwareUse = hardwareUse;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Sidebar getSidebar() {
        return sidebar;
    }

    public void setSidebar(Sidebar sidebar) {
        this.sidebar = sidebar;
    }

    public HardwareStatistics getHardwareStatistics() {
        return hardwareStatistics;
    }

    public void setHardwareStatistics(HardwareStatistics hardwareStatistics) {
        this.hardwareStatistics = hardwareStatistics;
    }
}
