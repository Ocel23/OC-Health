package ocel23.me.ochealth.models.statisticsModels.another;

import java.io.Serializable;

public class SoundCard implements Serializable {

    private String name;
    private String driver;
    private String codec;

    public SoundCard(String name, String driver, String codec) {
        this.name = name;
        this.driver = driver;
        this.codec = codec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }
}
