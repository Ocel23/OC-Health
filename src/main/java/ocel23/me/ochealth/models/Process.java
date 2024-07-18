package ocel23.me.ochealth.models;

import oshi.software.os.OSProcess;

public class Process {

    private String name;
    private String cpuUsage;
    private String memoryUsage;
    private OSProcess.State processState;

    public Process(String name, String  cpuUsage, String memoryUsage, OSProcess.State processState) {
        this.cpuUsage = cpuUsage;
        this.name = name;
        this.memoryUsage = memoryUsage;
        this.processState = processState;
    }

    public String getName() {
        return name;
    }

    public String getCpuUsage() {
        return cpuUsage;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public OSProcess.State getProcessState() {
        return processState;
    }
}
