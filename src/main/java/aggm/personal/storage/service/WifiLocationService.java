package aggm.personal.storage.service;

import aggm.personal.storage.domain.WifiLocation;
import aggm.personal.storage.domain.WifiScanResult;
import aggm.personal.storage.exception.InvalidArgumentException;
import aggm.personal.storage.exception.InvalidFieldException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aggm.personal.storage.repository.WifiLocationRepository;
import aggm.personal.storage.repository.WifiScanResultRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static aggm.personal.storage.utility.Constants.*;

@Service
@Transactional
public class WifiLocationService {

    private final WifiLocationRepository wifiLocationRepository;
    private final WifiScanResultRepository wifiScanResultRepository;

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
        if (ObjectUtils.isEmpty(wifiLocations)) {
            throw new InvalidArgumentException(INVALID_WIFI_LOCATION_LIST);
        }
        for (WifiLocation wifiLocation : wifiLocations) {
            validateWifiLocation(wifiLocation);
        }
    }

    private void validateWifiLocation(WifiLocation wifiLocation) {
        if (ObjectUtils.isEmpty(wifiLocation)) {
            throw new InvalidArgumentException(INVALID_WIFI_LOCATION);
        }

        double latitude = wifiLocation.getLatitude();
        double longitude = wifiLocation.getLongitude();
        LocalDateTime localDateTime = wifiLocation.getLocalDateTime();
        List<WifiScanResult> wifiScanResults = wifiLocation.getWifiScanResults();
        validateWifiScanResults(wifiScanResults);

        if (latitude > POSITIVE_NINETY_DEGREES || latitude < NEGATIVE_NINETY_DEGREES) {
            throw new InvalidFieldException(INVALID_LATITUDE);
        }
        if (longitude > POSITIVE_ONE_HUNDRED_AND_EIGHTY_DEGREES || longitude < NEGATIVE_ONE_HUNDRED_AND_EIGHTY_DEGREES) {
            throw new InvalidFieldException(INVALID_LONGITUDE);
        }
        if (localDateTime == null) {
            throw new InvalidFieldException(INVALID_DATE_TIME);
        }
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
        String rssi = wifiScanResult.getRssi();
        String bssid = wifiScanResult.getBssid();
        String capabilities = wifiScanResult.getCapabilities();
        int frequency = wifiScanResult.getFrequency();

        if (StringUtils.isEmpty(ssid)) {
            throw new InvalidFieldException(INVALID_SSID);
        }
        if (StringUtils.isEmpty(rssi)) {
            throw new InvalidFieldException(INVALID_RSSI);
        }
        if (StringUtils.isEmpty(bssid)) {
            throw new InvalidFieldException(INVALID_BSSID);
        }
        if (StringUtils.isEmpty(capabilities)) {
            throw new InvalidFieldException(INVALID_CAPABILITIES);
        }
        if (frequency <= NumberUtils.INTEGER_ZERO) {
            throw new InvalidFieldException(INVALID_FREQUENCY);
        }
    }
}