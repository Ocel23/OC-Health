package ocel23.me.ochealth.models.statisticsModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ocel23.me.ochealth.models.statisticsModels.another.Firmware;
import ocel23.me.ochealth.models.statisticsModels.another.SoundCard;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class Another implements Serializable {

    private Firmware firmware;
    private SoundCard soundCard;

}
