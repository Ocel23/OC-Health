package ocel23.me.ochealth.models;

import ocel23.me.ochealth.models.statisticsModels.*;

import java.io.Serializable;

public class Statistics implements Serializable {

    private HardwareInfo hardwareInfo;

    private SoftwareInfo softwareInfo;

    private NetworkInfo networkInfo;

    private PowerSource powerSource;

    private Another another;

    public Statistics(HardwareInfo hardwareInfo, SoftwareInfo softwareInfo, NetworkInfo networkInfo, PowerSource powerSource) {
        this.hardwareInfo = hardwareInfo;
        this.softwareInfo = softwareInfo;
        this.networkInfo = networkInfo;
        this.powerSource = powerSource;
    }

    public HardwareInfo getHardwareInfo() {
        return hardwareInfo;
    }

    public void setHardwareInfo(HardwareInfo hardwareInfo) {
        this.hardwareInfo = hardwareInfo;
    }

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

    public NetworkInfo getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(NetworkInfo networkInfo) {
        this.networkInfo = networkInfo;
    }

    public SoftwareInfo getSoftwareInfo() {
        return softwareInfo;
    }

    public void setSoftwareInfo(SoftwareInfo softwareInfo) {
        this.softwareInfo = softwareInfo;
    }
}
