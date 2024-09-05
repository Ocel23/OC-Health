package ocel23.me.ochealth.models.statisticsModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.GraphicCard;
import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.Memory;
import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.Processor;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class HardwareInfo implements Serializable {

    private Processor processor;
    private GraphicCard graphicCard;
    private Memory memory;

}
