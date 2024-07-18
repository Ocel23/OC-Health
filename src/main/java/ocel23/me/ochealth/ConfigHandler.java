package ocel23.me.ochealth;
import ocel23.me.ochealth.models.Settings;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.io.InputStream;
import java.io.StringWriter;

public class ConfigHandler {

    private Yaml config;


    public ConfigHandler() {
        getSettingsFromConfig();
    }

    public Settings getSettingsFromConfig() {
        LoaderOptions loaderOptions = new LoaderOptions();
        TagInspector tagInspector = tag -> tag.getClassName().equalsIgnoreCase(Settings.class.getName());
        loaderOptions.setTagInspector(tagInspector);
        config = new Yaml(new Constructor(Settings.class, loaderOptions));
        InputStream inputStream = getClass().getResourceAsStream("/ocel23/me/ochealth/config.yaml");
        return config.load(inputStream);
    }

    public void setSettingsToConfig(Settings settings) {
        config = new Yaml();
        StringWriter stringWriter = new StringWriter();
        config.dump(settings, stringWriter);
    }
}