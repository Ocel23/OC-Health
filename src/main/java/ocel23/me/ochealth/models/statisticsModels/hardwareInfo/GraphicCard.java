package ocel23.me.ochealth.models.statisticsModels.hardwareInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class GraphicCard implements Serializable {

    private String name;
    private String manufacturer;
    private String version;
    private long ram;

}
