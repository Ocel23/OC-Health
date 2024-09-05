package ocel23.me.ochealth.models.statisticsModels.hardwareInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class Processor implements Serializable {

    private String model;
    private String family;
    private String name;
    private String vendor;
    private int frequency;
    private int coreCount;
    private String microArchitecture;

}
