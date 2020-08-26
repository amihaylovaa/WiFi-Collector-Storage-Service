package aggm.personal.storage.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wifi")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class WifiScanResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    @Column(name = "bssid")
    @NotNull(message = "BSSID must not be null")
    @NotEmpty(message = "BSSID must not be empty")
    private String bssid;
    @Column(name = "ssid")
    @NotNull(message = "SSID must not be null")
    private String ssid;
    @Column(name = "rssi")
    @NotNull(message = "RSSI must not be null")
    @NotEmpty(message = "RSSI must not be empty")
    private String rssi;
    @Column(name = "capabilities")
    @NotNull(message = "Capabilities must not be null")
    @NotEmpty(message = "Capabilities must not be empty")
    private String capabilities;
    @Column(name = "frequency")
    private int frequency;

    public WifiScanResult(long id,
                          @NotNull(message = "BSSID must not be null")
                          @NotEmpty(message = "BSSID must not be empty")
                                  String bssid,
                          @NotNull(message = "SSID must not be null")
                          @NotEmpty(message = "SSID must not be empty")
                                  String ssid,
                          @NotNull(message = "RSSI must not be null")
                          @NotEmpty(message = "RSSI must not be empty")
                                  String rssi,
                          @NotNull(message = "Capabilities must not be null")
                          @NotEmpty(message = "Capabilities must not be empty")
                                  String capabilities,
                          int frequency) {
        this.id = id;
        this.bssid = bssid;
        this.ssid = ssid;
        this.rssi = rssi;
        this.capabilities = capabilities;
        this.frequency = frequency;
    }

    public WifiScanResult(@NotNull(message = "BSSID must not be null")
                          @NotEmpty(message = "BSSID must not be empty")
                                  String bssid,
                          @NotNull(message = "SSID must not be null")
                                  String ssid,
                          @NotNull(message = "RSSI must not be null")
                          @NotEmpty(message = "RSSI must not be empty")
                                  String rssi,
                          @NotNull(message = "Capabilities must not be null")
                          @NotEmpty(message = "Capabilities must not be empty")
                                  String capabilities,
                          int frequency) {
        this(0, bssid, ssid, rssi, capabilities, frequency);
    }
}