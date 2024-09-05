package ocel23.me.ochealth.models.statisticsModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class SoftwareInfo implements Serializable {

    private String family;
    private String version;
    private int bitness;
    private String manufacturer;
    private int countOfProcesses;
    private boolean isElevated;
    private int openDesktopWindowsCount;

}
