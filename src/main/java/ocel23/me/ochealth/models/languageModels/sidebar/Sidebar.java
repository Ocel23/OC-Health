package ocel23.me.ochealth.models.languageModels.sidebar;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Sidebar {

    private String home;
    private Network network;
    private Software software;
    private Hardware hardware;
    private Devices devices;
    private PowerSource powerSource;
    private Another another;
    private Settings settings;

}
