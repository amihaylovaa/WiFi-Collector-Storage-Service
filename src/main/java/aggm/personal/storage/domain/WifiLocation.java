package aggm.personal.storage.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wifi_locations")
@Getter
@Setter
@NoArgsConstructor
public class WifiLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "date_time")
    @NotNull(message = "Date and time must not be null")
    private LocalDateTime localDateTime;
    @OneToMany(targetEntity = WifiScanResult.class,
            mappedBy = "wifiLocation",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WifiScanResult> wifiScanResults;

    public WifiLocation(@NotEmpty(message = "Latitude must not be empty") double latitude,
                        @NotEmpty(message = "Longitude must not be empty") double longitude,
                          @NotNull(message = "Date and time must not be null") LocalDateTime localDateTime,
                        List<WifiScanResult> wifiScanResults) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.localDateTime = localDateTime;
        this.wifiScanResults = new ArrayList<>(wifiScanResults);
    }

    public WifiLocation(long id,
                        @NotEmpty(message = "Latitude must not be empty") double latitude,
                        @NotEmpty(message = "Longitude must not be empty") double longitude,
                        @NotNull(message = "Date and time must not be null") LocalDateTime localDateTime,
                        List<WifiScanResult> wifiScanResults) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.localDateTime = localDateTime;
        this.wifiScanResults = wifiScanResults;
    }
}