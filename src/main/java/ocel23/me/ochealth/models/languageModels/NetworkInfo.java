package ocel23.me.ochealth.models.languageModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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

}
