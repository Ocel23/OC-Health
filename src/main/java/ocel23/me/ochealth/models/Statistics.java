package ocel23.me.ochealth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ocel23.me.ochealth.models.statisticsModels.*;

import java.io.Serializable;

@Setter
@Getter

public class Statistics implements Serializable {

    private HardwareInfo hardwareInfo;
    private SoftwareInfo softwareInfo;
    private NetworkInfo networkInfo;
    private PowerSource powerSource;
    private Another another;

    public Statistics(HardwareInfo hardwareInfo, SoftwareInfo softwareInfo, NetworkInfo networkInfo, PowerSource powerSource, Another another) {
        this.hardwareInfo = hardwareInfo;
        this.softwareInfo = softwareInfo;
        this.networkInfo = networkInfo;
        this.powerSource = powerSource;
        this.another = another;
    }
}
