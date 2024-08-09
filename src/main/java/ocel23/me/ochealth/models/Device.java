package ocel23.me.ochealth.models;

import ocel23.me.ochealth.enums.DeviceType;

public class Device {

    private String name;

    private DeviceType deviceType;

    public Device(String name, DeviceType deviceType) {
        this.name = name;
        this.deviceType = deviceType;
    }

    public String getName() {
        return name;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }
}
