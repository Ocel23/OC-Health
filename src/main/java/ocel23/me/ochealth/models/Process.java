package ocel23.me.ochealth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import oshi.software.os.OSProcess;

@Getter
@AllArgsConstructor
public class Process {

    private String name;
    private String cpuUsage;
    private String memoryUsage;
    private OSProcess.State processState;

}
