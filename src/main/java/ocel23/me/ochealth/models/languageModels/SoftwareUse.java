package ocel23.me.ochealth.models.languageModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SoftwareUse {

    private String title;
    private String name;
    private String processor;
    private String memory;
    private String state;

}
