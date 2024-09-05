package ocel23.me.ochealth.models.languageModels.hardwareInfo.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Cpu {

    private String family;
    private String name;
    private String vendor;
    private String frequency;
    private String coreCount;

}
