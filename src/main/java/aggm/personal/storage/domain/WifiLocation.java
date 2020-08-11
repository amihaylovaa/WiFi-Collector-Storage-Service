package aggm.personal.storage.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
public class WifiLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @DecimalMin(value = "-90", message = "Latitude must not be less than -90 degrees")
    @DecimalMax(value = "90", message = "Latitude must not be more than +90 degrees")
    @Column(name = "latitude")
    private double latitude;
    @DecimalMin(value = "-180", message = "Longitude must not be less than -180 degrees")
    @DecimalMax(value = "180", message = "Longitude must not be more than +189 degrees")
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "date_time")
    @NotNull(message = "Date and time must not be null")
    private LocalDateTime localDateTime;
    @OneToMany(targetEntity = WifiScanResult.class,
            mappedBy = "wifiLocation",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @NotNull(message = "Wifi scan results list must not be null")
    private List<WifiScanResult> wifiScanResults;

    public WifiLocation(long id, @DecimalMin(value = "-90", message = "Latitude must not be less than -90 degrees") @DecimalMax(value = "90", message = "Latitude must not be more than +90 degrees") double latitude, @DecimalMin(value = "-180", message = "Longitude must not be less than -180 degrees") @DecimalMax(value = "180", message = "Longitude must not be more than +189 degrees") double longitude, @NotNull(message = "Date and time must not be null") LocalDateTime localDateTime, @NotNull(message = "Wifi scan results list must not be null") List<WifiScanResult> wifiScanResults) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.localDateTime = localDateTime;
        this.wifiScanResults = wifiScanResults;
    }

    public WifiLocation(@DecimalMin(value = "-90", message = "Latitude must not be less than -90 degrees") @DecimalMax(value = "90", message = "Latitude must not be more than +90 degrees") double latitude, @DecimalMin(value = "-180", message = "Longitude must not be less than -180 degrees") @DecimalMax(value = "180", message = "Longitude must not be more than +189 degrees") double longitude, @NotNull(message = "Date and time must not be null") LocalDateTime localDateTime, @NotNull(message = "Wifi scan results list must not be null") List<WifiScanResult> wifiScanResults) {
        this(0, latitude, longitude, localDateTime, wifiScanResults);
    }
}