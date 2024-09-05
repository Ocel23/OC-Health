package ocel23.me.ochealth.models.statisticsModels.another;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class Firmware implements Serializable {

    private String name;
    private String description;
    private String manufacturer;
    private String releaseDate;
    private String version;

}
