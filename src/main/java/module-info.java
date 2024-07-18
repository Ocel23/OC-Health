module ocel23.me.ochealth {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.github.oshi;
    requires org.yaml.snakeyaml;

    opens ocel23.me.ochealth to javafx.fxml;
    exports ocel23.me.ochealth;
    exports ocel23.me.ochealth.models;
    exports ocel23.me.ochealth.models.languageModels;
    exports ocel23.me.ochealth.models.languageModels.another;
    exports ocel23.me.ochealth.models.languageModels.hardwareInfo.content;
    exports ocel23.me.ochealth.models.languageModels.hardwareInfo;
    opens ocel23.me.ochealth.models to javafx.fxml;
    exports ocel23.me.ochealth.controllers;
    opens ocel23.me.ochealth.controllers to javafx.fxml;
}