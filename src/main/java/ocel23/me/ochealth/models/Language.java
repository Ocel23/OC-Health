package ocel23.me.ochealth.models;


import lombok.Getter;
import lombok.Setter;
import ocel23.me.ochealth.models.languageModels.*;
import ocel23.me.ochealth.models.languageModels.Settings;
import ocel23.me.ochealth.models.languageModels.another.Another;
import ocel23.me.ochealth.models.languageModels.hardwareInfo.HardwareInfo;
import ocel23.me.ochealth.models.languageModels.SoftwareInfo;
import ocel23.me.ochealth.models.languageModels.SoftwareUse;
import ocel23.me.ochealth.models.languageModels.sidebar.Sidebar;

@Setter
@Getter
public class Language {

    private Another another;
    private PowerSource powerSource;
    private Devices devices;
    private NetworkInfo networkInfo;
    private NetworkUse networkUse;
    private SoftwareUse softwareUse;
    private SoftwareInfo softwareInfo;
    private HardwareInfo hardwareInfo;
    private HardwareUse hardwareUse;
    private HardwareStatistics hardwareStatistics;
    private Settings settings;
    private Home home;
    private Sidebar sidebar;

}
