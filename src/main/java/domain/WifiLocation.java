package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "wifi_locations")
@Getter
@Setter
public class WifiLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "latitude")
    @NotEmpty(message = "Latitude must not be empty")
    private double latitude;
    @Column(name = "longitude")
    @NotEmpty(message = "Longitude must not be empty")
    private double longitude;
    @Column(name = "date_time")
    @NotNull(message = "Date and time must not be null")
    private LocalDateTime localDateTime;
    private List<WifiScanResult> wifiScanResults;
}