package ocel23.me.ochealth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ocel23.me.ochealth.models.statisticsModels.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class Statistics implements Serializable {

    private HardwareInfo hardwareInfo;
    private SoftwareInfo softwareInfo;
    private NetworkInfo networkInfo;
    private PowerSource powerSource;
    private Another another;

}
