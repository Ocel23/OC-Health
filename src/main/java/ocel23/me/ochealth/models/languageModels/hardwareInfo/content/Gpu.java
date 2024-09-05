package ocel23.me.ochealth.models.languageModels.hardwareInfo.content;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Gpu {

    private String name;
    private String vendor;
    private String version;

}
