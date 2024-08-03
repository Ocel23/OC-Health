package ocel23.me.ochealth.models.statisticsModels;

import ocel23.me.ochealth.models.statisticsModels.another.Firmware;
import ocel23.me.ochealth.models.statisticsModels.another.SoundCard;

import java.io.Serializable;

public class Another implements Serializable {

    private Firmware firmware;
    private SoundCard soundCard;

    public Another(Firmware firmware, SoundCard soundCard) {
        this.firmware = firmware;
        this.soundCard = soundCard;
    }

    public Firmware getFirmware() {
        return firmware;
    }

    public void setFirmware(Firmware firmware) {
        this.firmware = firmware;
    }

    public SoundCard getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
    }
}
