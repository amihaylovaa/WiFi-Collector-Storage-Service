package aggm.personal.storage.service;

import aggm.personal.storage.entity.WifiLocation;
import aggm.personal.storage.entity.WifiScanResult;
import aggm.personal.storage.exception.InvalidArgumentException;
import aggm.personal.storage.exception.InvalidFieldException;
import aggm.personal.storage.repository.WifiLocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WifiLocationServiceTest {
    @Mock
    private WifiLocationRepository wifiLocationRepository;
    @InjectMocks
    private WifiLocationService wifiLocationService;

    @Test
    void saveAll_WifiLocationListIsNull_ExceptionThrown() {
        List<WifiLocation> wifiLocations = null;

        assertThrows(InvalidArgumentException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_WifiLocationListIsEmpty_ExceptionThrown() {
        List<WifiLocation> wifiLocations = new ArrayList<>();

        assertThrows(InvalidArgumentException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_WifiLocationListHasNullElement_ExceptionThrown() {
        List<WifiLocation> wifiLocations = new ArrayList<>();

        wifiLocations.add(null);

        assertThrows(InvalidArgumentException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_LatitudeIsAboveNinetyDegrees_ExceptionThrown() {
        double latitude = 122.136097;
        double longitude = 24.742168;
        LocalDateTime localDateTime = LocalDateTime.now();
        List<WifiScanResult> wifiScanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, wifiScanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_LongitudeIsBelowMinusOneHundredAndEightyDegrees_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = -250.742168;
        LocalDateTime localDateTime = LocalDateTime.now();
        List<WifiScanResult> wifiScanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, wifiScanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_LocalDateTimeIsNull_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        LocalDateTime localDateTime = null;
        List<WifiScanResult> wifiScanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, wifiScanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_WifiScanResultListIsNull_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        LocalDateTime localDateTime = LocalDateTime.now();
        List<WifiScanResult> wifiScanResults = null;
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, wifiScanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        wifiLocations.add(wifiLocation);

        assertThrows(InvalidArgumentException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_WifiScanResultListHasNullElement_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        LocalDateTime localDateTime = LocalDateTime.now();
        List<WifiScanResult> wifiScanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, wifiScanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        wifiScanResults.add(null);
        wifiLocations.add(wifiLocation);

        assertThrows(InvalidArgumentException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_SSIDIsNull_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        int frequency = 31;
        String ssid = null;
        String rssi = "-32";
        String bssid = "12:34:56:d8:81";
        String capabilities = "[WPA2-PSK-CCMP][RSN-PSK-CCMP][ESS][WPS][WFA-HT]";
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        List<WifiScanResult> scanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, scanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        scanResults.add(wifiScanResult);
        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_BSSIDIsNull_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        int frequency = 31;
        String ssid = "someName";
        String rssi = "-32";
        String bssid = null;
        String capabilities = "[WPA2-PSK-CCMP][RSN-PSK-CCMP][ESS][WPS][WFA-HT]";
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        List<WifiScanResult> scanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, scanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        scanResults.add(wifiScanResult);
        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_BSSIDIsEmpty_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        String ssid = "someName";
        String rssi = "-32";
        String bssid = "";
        String capabilities = "[WPA2-PSK-CCMP][RSN-PSK-CCMP][ESS][WPS][WFA-HT]";
        int frequency = 31;
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        List<WifiScanResult> scanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, scanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        scanResults.add(wifiScanResult);
        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_RSSIIsNull_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        String ssid = "someName";
        String rssi = null;
        String bssid = "12:34:56:d8:81";
        String capabilities = "[WPA2-PSK-CCMP][RSN-PSK-CCMP][ESS][WPS][WFA-HT]";
        int frequency = 31;
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        List<WifiScanResult> scanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, scanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        scanResults.add(wifiScanResult);
        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_RSSIIsEmpty_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        String ssid = "someName";
        String rssi = "";
        String bssid = "12:34:56:d8:81";
        String capabilities = "[WPA2-PSK-CCMP][RSN-PSK-CCMP][ESS][WPS][WFA-HT]";
        int frequency = 31;
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        List<WifiScanResult> scanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, scanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        scanResults.add(wifiScanResult);
        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_CapabilitiesAreNull_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        String ssid = "someName";
        String rssi = "-32";
        String bssid = "12:34:56:d8:81";
        String capabilities = null;
        int frequency = 31;
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        List<WifiScanResult> scanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, scanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        scanResults.add(wifiScanResult);
        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_CapabilitiesAreEmpty_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        String ssid = "someName";
        String rssi = "-32";
        String bssid = "12:34:56:d8:81";
        String capabilities = "";
        int frequency = 31;
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        List<WifiScanResult> scanResults = new ArrayList<>();
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, scanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        scanResults.add(wifiScanResult);
        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_FrequencyIsNegative_ExceptionThrown() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        String ssid = "someName";
        String rssi = "-32";
        String bssid = "12:34:56:d8:81";
        String capabilities = "[WPA2-PSK-CCMP][RSN-PSK-CCMP][ESS][WPS][WFA-HT]";
        int frequency = -31;
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        List<WifiScanResult> scanResults = new ArrayList<>();
        scanResults.add(wifiScanResult);
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime, scanResults);
        List<WifiLocation> wifiLocations = new ArrayList<>();

        wifiLocations.add(wifiLocation);

        assertThrows(InvalidFieldException.class, () -> wifiLocationService.saveAll(wifiLocations));
    }

    @Test
    void saveAll_validWifiLocationList_ReturnSavedWifiLocationList() {
        double latitude = 42.136097;
        double longitude = 24.742168;
        String ssid = "someName";
        String rssi = "-32";
        String bssid = "12:34:56:d8:81";
        String capabilities = "[WPA2-PSK-CCMP][RSN-PSK-CCMP][ESS][WPS][WFA-HT]";
        int frequency = 31;
        LocalDateTime localDateTime = LocalDateTime.now();
        WifiScanResult wifiScanResult = new WifiScanResult(bssid, ssid, rssi, capabilities, frequency);
        WifiLocation wifiLocation = new WifiLocation(latitude, longitude, localDateTime);
        List<WifiScanResult> expectedWifiScanResults = new ArrayList<>();
        List<WifiLocation> expectedWifiLocationList = new ArrayList<>();
        wifiScanResult.setWifiLocation(wifiLocation);
        expectedWifiScanResults.add(wifiScanResult);
        wifiLocation.setWifiScanResults(expectedWifiScanResults);
        expectedWifiLocationList.add(wifiLocation);

        when(wifiLocationRepository.saveAll(expectedWifiLocationList)).thenReturn(expectedWifiLocationList);

        List<WifiLocation> actualWifiLocationList = wifiLocationService.saveAll(expectedWifiLocationList);

        assertEquals(expectedWifiLocationList, actualWifiLocationList);
    }
}
