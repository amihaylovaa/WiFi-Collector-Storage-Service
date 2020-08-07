package aggm.personal.storage.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wifi_scan_results")
@Getter
@Setter
@NoArgsConstructor
public class WifiScanResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    @Column(name = "BSSID")
    @NotNull(message = "BSSID must not be null")
    @NotEmpty(message = "BSSID must not be empty")
    private String BSSID;
    @Column(name = "SSID")
    @NotNull(message = "SSID must not be null")
    @NotEmpty(message = "SSID must not be empty")
    private String SSID;
    @Column(name = "RSSI")
    @NotNull(message = "RSSI must not be null")
    @NotEmpty(message = "RSSI must not be empty")
    private String RSSI;
    @Column(name = "capabilities")
    @NotNull(message = "Capabilities must not be null")
    @NotEmpty(message = "Capabilities must not be empty")
    private String capabilities;
    @Column(name = "frequency")
    private int frequency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wifi_location_id")
    private WifiLocation wifiLocation;

    public WifiScanResult(long id, @NotNull(message = "BSSID must not be null") @NotEmpty(message = "BSSID must not be empty") String BSSID, @NotNull(message = "SSID must not be null") @NotEmpty(message = "SSID must not be empty") String SSID, @NotNull(message = "RSSI must not be null") @NotEmpty(message = "RSSI must not be empty") String RSSI, @NotNull(message = "Capabilities must not be null") @NotEmpty(message = "Capabilities must not be empty") String capabilities, @NotNull(message = "Frequency must not be null") @NotEmpty(message = "Frequency must not be empty") int frequency, WifiLocation wifiLocation) {
        this.id = id;
        this.BSSID = BSSID;
        this.SSID = SSID;
        this.RSSI = RSSI;
        this.capabilities = capabilities;
        this.frequency = frequency;
        this.wifiLocation = wifiLocation;
    }
}