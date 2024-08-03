package ocel23.me.ochealth.models.statisticsModels.hardwareInfo;

import java.io.Serializable;
import java.util.List;

public class Memory implements Serializable {

    private long total;
    private long virtual;
    private int countOfPhysicalsMemories;
    private List<String> typesOfMemories;

    public Memory(long total, int countOfPhysicalsMemories, List<String> typesOfMemories,long virtual) {
        this.total = total;
        this.countOfPhysicalsMemories = countOfPhysicalsMemories;
        this.typesOfMemories = typesOfMemories;
        this.virtual = virtual;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCountOfPhysicalsMemories() {
        return countOfPhysicalsMemories;
    }

    public void setCountOfPhysicalsMemories(int countOfPhysicalsMemories) {
        this.countOfPhysicalsMemories = countOfPhysicalsMemories;
    }

    public long getVirtual() {
        return virtual;
    }

    public void setVirtual(long virtual) {
        this.virtual = virtual;
    }

    public List<String> getTypesOfMemories() {
        return typesOfMemories;
    }

    public void setTypesOfMemories(List<String> typesOfMemories) {
        this.typesOfMemories = typesOfMemories;
    }
}
