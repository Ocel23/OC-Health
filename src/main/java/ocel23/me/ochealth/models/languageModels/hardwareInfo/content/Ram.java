package ocel23.me.ochealth.models.languageModels.hardwareInfo.content;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Ram {

    private String totalMemory;
    private String virtualMemory;
    private String physicalMemoryCount;
    private String typesOfMemories;

}
