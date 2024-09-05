package ocel23.me.ochealth.models.statisticsModels.hardwareInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Memory implements Serializable {

    private long total;
    private long virtual;
    private List<String> typesOfMemories;
    private long countOfPhysicalsMemories;

}
