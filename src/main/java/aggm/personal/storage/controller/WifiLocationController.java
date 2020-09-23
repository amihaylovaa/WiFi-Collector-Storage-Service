package aggm.personal.storage.controller;

import aggm.personal.storage.domain.WifiLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import aggm.personal.storage.service.WifiLocationService;

import java.util.List;

@RestController
@RequestMapping("/wifi/locations")
public class WifiLocationController {

    private WifiLocationService wifiLocationService;

    @Autowired
    public WifiLocationController(WifiLocationService wifiLocationService) {
        this.wifiLocationService = wifiLocationService;
    }

    @PostMapping
    public List<WifiLocation> saveAll(@RequestBody List<WifiLocation> wifiLocations) {
        return wifiLocationService.saveAll(wifiLocations);
    }
}