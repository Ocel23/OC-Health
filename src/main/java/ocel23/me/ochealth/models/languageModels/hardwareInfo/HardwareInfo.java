package ocel23.me.ochealth.models.languageModels.hardwareInfo;

import ocel23.me.ochealth.models.languageModels.hardwareInfo.content.Content;

public class HardwareInfo {

    private String title;
    private Navbar navbar;
    private Content content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Navbar getNavbar() {
        return navbar;
    }

    public void setNavbar(Navbar navbar) {
        this.navbar = navbar;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
