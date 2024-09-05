package ocel23.me.ochealth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ocel23.me.ochealth.enums.DeviceType;

@Getter
@AllArgsConstructor
public class Device {

    private String name;
    private DeviceType deviceType;

}
