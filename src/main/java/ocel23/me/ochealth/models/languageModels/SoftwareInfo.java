package ocel23.me.ochealth.models.languageModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SoftwareInfo {

    private String title;
    private String family;
    private String version;
    private String bitness;
    private String manufacturer;
    private String processCount;
    private String isElevated;
    private String desktopCount;

}
