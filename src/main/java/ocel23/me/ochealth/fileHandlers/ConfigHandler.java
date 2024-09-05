package ocel23.me.ochealth.fileHandlers;
import ocel23.me.ochealth.models.Settings;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.io.*;

/**
 * This class handle config, set and get to config
 */
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
        String path = System.getProperty("user.home") + File.separator + "Oc-Health";
        File customDir = new File(path);
        File configFile = new File(customDir.getAbsolutePath() + "/config.yaml");
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return config.load(inputStream);
    }

    public void setSettingsToConfig(Settings settings) {
        config = new Yaml();
        PrintWriter printWriter;
        String path = System.getProperty("user.home") + File.separator + "Oc-Health";
        File customDir = new File(path);
        File configFile = new File(customDir.getAbsolutePath() + "/config.yaml");
        try {
            printWriter = new PrintWriter(configFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        config.dump(settings, printWriter);
    }
}