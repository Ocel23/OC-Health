package ocel23.me.ochealth;

import ocel23.me.ochealth.models.Language;
import ocel23.me.ochealth.models.Settings;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.io.InputStream;
import java.io.StringWriter;

public class LanguageHandler {

    private Yaml config;


    public LanguageHandler() {
        getLanguageValues();
    }

    public Language getLanguageValues() {
        LoaderOptions loaderOptions = new LoaderOptions();
        TagInspector tagInspector = tag -> tag.getClassName().equalsIgnoreCase(Language.class.getName());
        loaderOptions.setTagInspector(tagInspector);
        config = new Yaml(new Constructor(Language.class, loaderOptions));
        InputStream inputStream = getClass().getResourceAsStream("/ocel23/me/ochealth/language.yaml");
        return config.load(inputStream);
    }

    public void setLanguageValues(Settings settings) {
        config = new Yaml();
        StringWriter stringWriter = new StringWriter();
        config.dump(settings, stringWriter);
    }
}
