package ocel23.me.ochealth.models.languageModels.hardwareInfo.content;

public class Ram {

    private String totalMemory;
    private String virtualMemory;
    private String physicalMemoryCount;
    private String typesOfMemories;

    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getVirtualMemory() {
        return virtualMemory;
    }

    public void setVirtualMemory(String virtualMemory) {
        this.virtualMemory = virtualMemory;
    }

    public String getPhysicalMemoryCount() {
        return physicalMemoryCount;
    }

    public void setPhysicalMemoryCount(String physicalMemoryCount) {
        this.physicalMemoryCount = physicalMemoryCount;
    }

    public String getTypesOfMemories() {
        return typesOfMemories;
    }

    public void setTypesOfMemories(String typesOfMemories) {
        this.typesOfMemories = typesOfMemories;
    }
}
