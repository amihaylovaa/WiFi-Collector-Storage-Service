package aggm.personal.storage.service;

import aggm.personal.storage.domain.*;
import aggm.personal.storage.exception.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aggm.personal.storage.repository.WifiLocationRepository;

import java.time.LocalDateTime;
import java.util.List;

import static aggm.personal.storage.utility.Constants.*;

@Service
public class WifiLocationService {

    private final WifiLocationRepository wifiLocationRepository;

    @Autowired
    public WifiLocationService(WifiLocationRepository wifiLocationRepository) {
        this.wifiLocationRepository = wifiLocationRepository;
    }

    public List<WifiLocation> saveAll(List<WifiLocation> wifiLocations) {
        if (ObjectUtils.isEmpty(wifiLocations)) {
            throw new InvalidArgumentException(INVALID_WIFI_LOCATION_LIST);
        }

        for (WifiLocation wifiLocation : wifiLocations) {
            validateWifiLocation(wifiLocation);
        }

        return wifiLocationRepository.saveAll(wifiLocations);
    }

    private void validateWifiLocation(WifiLocation wifiLocation) {
        if (ObjectUtils.isEmpty(wifiLocation)) {
            throw new InvalidArgumentException(INVALID_WIFI_LOCATION);
        }

        double latitude = wifiLocation.getLatitude();
        double longitude = wifiLocation.getLongitude();
        LocalDateTime localDateTime = wifiLocation.getLocalDateTime();
        List<WifiScanResult> wifiScanResults = wifiLocation.getWifiScanResults();

        if (latitude > MAX_LATITUDE || latitude < MIN_LATITUDE) {
            throw new InvalidFieldException(INVALID_LATITUDE);
        }
        if (longitude > MAX_LONGITUDE || longitude < MIN_LONGITUDE) {
            throw new InvalidFieldException(INVALID_LONGITUDE);
        }
        if (localDateTime == null) {
            throw new InvalidFieldException(INVALID_DATE_TIME);
        }

        validateWifiScanResults(wifiScanResults);
    }

    public void validateWifiScanResults(List<WifiScanResult> wifiScanResults) {
        if (wifiScanResults == null) {
            throw new InvalidArgumentException(WIFI_SCAN_RESULT_LIST_NULL_MESSAGE);
        }
        for (WifiScanResult wifiScanResult : wifiScanResults) {
            validateWifiScanResult(wifiScanResult);
        }
    }

    public void validateWifiScanResult(WifiScanResult wifiScanResult) {
        if (ObjectUtils.isEmpty(wifiScanResult)) {
            throw new InvalidArgumentException(WIFI_SCAN_RESULT_NULL_MESSAGE);
        }

        String ssid = wifiScanResult.getSsid();
        int rssi = wifiScanResult.getRssi();
        String bssid = wifiScanResult.getBssid();
        String capabilities = wifiScanResult.getCapabilities();
        int frequency = wifiScanResult.getFrequency();

        if (ssid == null) {
            throw new InvalidFieldException(INVALID_SSID);
        }
        if (StringUtils.isEmpty(bssid)) {
            throw new InvalidFieldException(INVALID_BSSID);
        }
        if (capabilities == null) {
            throw new InvalidFieldException(INVALID_CAPABILITIES);
        }
        if (frequency <= NumberUtils.INTEGER_ZERO) {
            throw new InvalidFieldException(INVALID_FREQUENCY);
        }
    }
}