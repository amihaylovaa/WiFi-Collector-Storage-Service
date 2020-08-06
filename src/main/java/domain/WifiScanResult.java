package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wifi_scan_results")
@Getter
@Setter
public class WifiScanResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @NotNull(message = "Frequency must not be null")
    @NotEmpty(message = "Frequency must not be empty")
    private int frequency;
    @ManyToOne
    @JoinColumn(name = "wifi_location_id")
    private WifiLocation wifiLocation;
}