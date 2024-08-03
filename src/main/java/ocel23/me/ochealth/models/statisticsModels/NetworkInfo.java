package ocel23.me.ochealth.models.statisticsModels;

import java.io.Serializable;

public class NetworkInfo implements Serializable {

    private String ipv4Address;
    private String ipv6Address;
    private String macAddress;
    private String ipv4Gateway;
    private String ipv6Gateway;
    private String vInterface;
    private long speed;
    private String [] dnsServers;

    public NetworkInfo(String ipv4Address, String ipv6Address, String macAddress, String ipv4Gateway, String ipv6Gateway, String vInterface, String [] dnsServers, long speed) {
        this.ipv4Address = ipv4Address;
        this.ipv6Address = ipv6Address;
        this.macAddress = macAddress;
        this.ipv4Gateway = ipv4Gateway;
        this.ipv6Gateway = ipv6Gateway;
        this.vInterface = vInterface;
        this.speed = speed;
        this.dnsServers = dnsServers;
    }

    public String getIpv4Address() {
        return ipv4Address;
    }

    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpv4Gateway() {
        return ipv4Gateway;
    }

    public void setIpv4Gateway(String ipv4Gateway) {
        this.ipv4Gateway = ipv4Gateway;
    }

    public String getIpv6Gateway() {
        return ipv6Gateway;
    }

    public void setIpv6Gateway(String ipv6Gateway) {
        this.ipv6Gateway = ipv6Gateway;
    }

    public String getvInterface() {
        return vInterface;
    }

    public void setvInterface(String vInterface) {
        this.vInterface = vInterface;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public String[] getDnsServers() {
        return dnsServers;
    }

    public void setDnsServers(String[] dnsServers) {
        this.dnsServers = dnsServers;
    }
}
