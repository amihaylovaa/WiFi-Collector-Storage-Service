package aggm.personal.storage.service;

import aggm.personal.storage.domain.WifiLocation;
import aggm.personal.storage.domain.WifiScanResult;
import aggm.personal.storage.exception.InvalidArgumentException;
import aggm.personal.storage.exception.InvalidFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aggm.personal.storage.repository.WifiLocationRepository;
import aggm.personal.storage.repository.WifiScanResultRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class WifiLocationService {

    private WifiLocationRepository wifiLocationRepository;
    private WifiScanResultRepository wifiScanResultRepository;

    @Autowired
    public WifiLocationService(WifiLocationRepository wifiLocationRepository, WifiScanResultRepository wifiScanResultRepository) {
        this.wifiLocationRepository = wifiLocationRepository;
        this.wifiScanResultRepository = wifiScanResultRepository;
    }

    public List<WifiLocation> saveAll(List<WifiLocation> wifiLocations) {
        validateWifiLocations(wifiLocations);
        List<WifiLocation> wifiLocationsSaved = wifiLocationRepository.saveAll(wifiLocations);
        for (WifiLocation wifiLocation : wifiLocationsSaved) {
            List<WifiScanResult> wifiScanResults = wifiLocation.getWifiScanResults();
            wifiScanResultRepository.saveAll(wifiScanResults);
        }

        return wifiLocationsSaved;
    }

    private void validateWifiLocations(List<WifiLocation> wifiLocations) {
        if (wifiLocations == null || wifiLocations.isEmpty()) {
            throw new InvalidArgumentException("Invalid wifi locations");
        }
        for (WifiLocation wifiLocation : wifiLocations) {
            validateWifiLocation(wifiLocation);
        }
    }

    private void validateWifiLocation(WifiLocation wifiLocation) {
        if (wifiLocation == null) {
            throw new InvalidArgumentException("Invalid wifi location");
        }

        double latitude = wifiLocation.getLatitude();
        double longitude = wifiLocation.getLongitude();
        LocalDateTime localDateTime = wifiLocation.getLocalDateTime();
        List<WifiScanResult> wifiScanResults = wifiLocation.getWifiScanResults();
        validateWifiScanResults(wifiScanResults);

        if (latitude > 90.0 || latitude < (-90.0)) {
            throw new InvalidFieldException("Invalid latitude");
        }
        if (longitude > 180.0 || longitude < (-180.0)) {
            throw new InvalidFieldException("Invalid longitude");
        }
        if (localDateTime == null) {
            throw new InvalidFieldException("Invalid date and time");
        }
    }

    public void validateWifiScanResults(List<WifiScanResult> wifiScanResults) {
        if (wifiScanResults == null || wifiScanResults.isEmpty()) {
            throw new InvalidArgumentException("Invalid Wifi scan results");
        }
        for (WifiScanResult wifiScanResult : wifiScanResults) {
            validateWifiScanResult(wifiScanResult);
        }
    }

    public void validateWifiScanResult(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            throw new InvalidArgumentException("Invalid wifi scan result");
        }

        String SSID = wifiScanResult.getSSID();
        String RSSI = wifiScanResult.getRSSI();
        String BSSID = wifiScanResult.getBSSID();
        String capabilities = wifiScanResult.getCapabilities();
        int frequency = wifiScanResult.getFrequency();

        if (SSID == null || SSID.isEmpty()) {
            throw new InvalidFieldException("Invalid SSID");
        }
        if (RSSI == null || RSSI.isEmpty()) {
            throw new InvalidFieldException("Invalid RSSI");
        }
        if (BSSID == null || BSSID.isEmpty()) {
            throw new InvalidFieldException("Invalid BSSID");
        }
        if (capabilities == null || capabilities.isEmpty()) {
            throw new InvalidFieldException("Invalid capabilities");
        }
        if (frequency <= 0) {
            throw new InvalidFieldException("Invalid frequency");
        }
    }
}