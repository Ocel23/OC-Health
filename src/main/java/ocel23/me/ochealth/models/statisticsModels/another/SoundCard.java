package ocel23.me.ochealth.models.statisticsModels.another;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class SoundCard implements Serializable {

    private String name;
    private String driver;
    private String codec;
}
