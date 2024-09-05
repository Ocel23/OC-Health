package ocel23.me.ochealth.fileHandlers;

import ocel23.me.ochealth.Main2;
import ocel23.me.ochealth.models.Language;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.io.InputStream;
/**
 * This class handle language file, set and get to language file
 */
public class LanguageHandler {


    public LanguageHandler() {
        getLanguageValues();
    }

    public Language getLanguageValues() {
        LoaderOptions loaderOptions = new LoaderOptions();
        TagInspector tagInspector = tag -> tag.getClassName().equalsIgnoreCase(Language.class.getName());
        loaderOptions.setTagInspector(tagInspector);
        Yaml config = new Yaml(new Constructor(Language.class, loaderOptions));
        InputStream inputStream = Main2.class.getResourceAsStream("language.yaml");
        return config.load(inputStream);
    }
}
