package ocel23.me.ochealth.models.statisticsModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class NetworkInfo implements Serializable {

    private String ipv4Address;
    private String ipv6Address;
    private String macAddress;
    private String ipv4Gateway;
    private String ipv6Gateway;
    private String vInterface;
    private String [] dnsServers;
    private long speed;

}
