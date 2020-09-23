package aggm.personal.storage.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "locations")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class WifiLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "date_time")
    @NotNull(message = "Date and time must not be null")
    private LocalDateTime localDateTime;
    @OneToMany(
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    @JoinColumn(name = "location_id")
    @NotNull(message = "Wifi scan result list must not be null")
    private List<WifiScanResult> wifiScanResults;

    public WifiLocation(long id,
                        @NotNull double latitude,
                        @NotNull double longitude,
                        @NotNull(message = "Date and time must not be null")
                                LocalDateTime localDateTime,
                        @NotNull(message = "Wifi scan result list must not be null")
                                List<WifiScanResult> wifiScanResults) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.localDateTime = localDateTime;
        this.wifiScanResults = wifiScanResults;
    }

    public WifiLocation(@NotNull double latitude,
                        @NotNull double longitude,
                        @NotNull(message = "Date and time must not be null") LocalDateTime localDateTime,
                        @NotNull(message = "Wifi scan result list must not be null") List<WifiScanResult> wifiScanResults) {
        this(0, latitude, longitude, localDateTime, wifiScanResults);
    }
}