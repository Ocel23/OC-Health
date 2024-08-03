package ocel23.me.ochealth.models.statisticsModels;

import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.GraphicCard;
import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.Memory;
import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.Processor;

import java.io.Serializable;

public class HardwareInfo implements Serializable {

    private Processor processor;

    private GraphicCard graphicCard;

    private Memory memory;

    public HardwareInfo(Processor processor, GraphicCard graphicCard, Memory memory) {
        this.processor = processor;
        this.graphicCard = graphicCard;
        this.memory = memory;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public GraphicCard getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(GraphicCard graphicCard) {
        this.graphicCard = graphicCard;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
}
