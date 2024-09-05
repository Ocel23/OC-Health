package ocel23.me.ochealth.models.languageModels.hardwareInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ocel23.me.ochealth.models.languageModels.hardwareInfo.content.Content;

@Setter
@Getter
@NoArgsConstructor
public class HardwareInfo {

    private String title;
    private Navbar navbar;
    private Content content;

}
