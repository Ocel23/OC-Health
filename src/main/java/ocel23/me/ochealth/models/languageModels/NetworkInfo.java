package ocel23.me.ochealth.models.languageModels;

public class NetworkInfo {

    private String title;
    private String ipv4address;
    private String ipv4gateway;
    private String ipv6address;
    private String ipv6gateway;
    private String macAddress;
    private String _interface;
    private String speed;
    private String dnsServers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIpv4address() {
        return ipv4address;
    }

    public void setIpv4address(String ipv4address) {
        this.ipv4address = ipv4address;
    }

    public String getIpv4gateway() {
        return ipv4gateway;
    }

    public void setIpv4gateway(String ipv4gateway) {
        this.ipv4gateway = ipv4gateway;
    }

    public String getIpv6address() {
        return ipv6address;
    }

    public void setIpv6address(String ipv6address) {
        this.ipv6address = ipv6address;
    }

    public String getIpv6gateway() {
        return ipv6gateway;
    }

    public void setIpv6gateway(String ipv6gateway) {
        this.ipv6gateway = ipv6gateway;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String get_interface() {
        return _interface;
    }

    public void set_interface(String _interface) {
        this._interface = _interface;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDnsServers() {
        return dnsServers;
    }

    public void setDnsServers(String dnsServers) {
        this.dnsServers = dnsServers;
    }
}
