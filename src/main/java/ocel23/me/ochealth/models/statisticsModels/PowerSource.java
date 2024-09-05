package ocel23.me.ochealth.models.statisticsModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class PowerSource implements Serializable {

    private int capacity;
    private double powerUsageRate;
    private String deviceName;
    private double temperature;
    private double amperage;
    private double timeRemaining;
    private double voltage;
    private String manufacturer;

}
