module ocel23.me.ochealth {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.github.oshi;
    requires org.yaml.snakeyaml;
    requires java.sql;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.prefs;
    requires org.apache.commons.io;
    requires static lombok;

    opens ocel23.me.ochealth to javafx.fxml;
    exports ocel23.me.ochealth;
    exports ocel23.me.ochealth.models;
    exports ocel23.me.ochealth.models.languageModels;
    exports ocel23.me.ochealth.models.languageModels.another;
    exports ocel23.me.ochealth.models.languageModels.hardwareInfo.content;
    exports ocel23.me.ochealth.models.languageModels.hardwareInfo;
    exports ocel23.me.ochealth.models.statisticsModels.another;
    exports ocel23.me.ochealth.models.statisticsModels.hardwareInfo;
    exports ocel23.me.ochealth.models.statisticsModels;
    opens ocel23.me.ochealth.models to javafx.fxml, com.google.gson;
    opens ocel23.me.ochealth.models.statisticsModels to com.google.gson;
    opens ocel23.me.ochealth.models.statisticsModels.another to com.google.gson;
    opens ocel23.me.ochealth.models.statisticsModels.hardwareInfo to com.google.gson;
    exports ocel23.me.ochealth.controllers;
    opens ocel23.me.ochealth.controllers to javafx.fxml;
    exports ocel23.me.ochealth.fileHandlers;
    opens ocel23.me.ochealth.fileHandlers to javafx.fxml;
    exports ocel23.me.ochealth.models.languageModels.sidebar;
}