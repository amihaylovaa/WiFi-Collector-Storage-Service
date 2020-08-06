package controller;

import domain.WifiLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.WifiLocationService;

import java.util.List;

@RestController
@RequestMapping("wifi/locations")
public class WifiLocationController {

    private WifiLocationService wifiLocationService;

    @Autowired
    public WifiLocationController(WifiLocationService wifiLocationService) {
        this.wifiLocationService = wifiLocationService;
    }

    public List<WifiLocation> saveAll(List<WifiLocation> wifiLocations) {
        return wifiLocationService.saveAll(wifiLocations);
    }
}