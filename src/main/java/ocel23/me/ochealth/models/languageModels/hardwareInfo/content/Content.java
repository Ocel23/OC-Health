package ocel23.me.ochealth.models.languageModels.hardwareInfo.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Content {

    private Cpu cpu;
    private Gpu gpu;
    private Ram ram;

}
