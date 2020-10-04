**Wi-Fi Collector storage service ** is a simple rest service powered and managed by Spring boot.
Its purpose is to present interface between the mobile application [Wi-Fi collector](https://github.com/amihaylovaa/WiFi-Collector) and PostreSQL - stores data in 
the database that the mobile app has received and sent to the web server.

The service is consisting of :

* Domain layer - contains two entities without which the service could not exist (mapped as relations in the database)

* Persistence layer - contains the database operation

* Service layer - contains the business logic which is to store the data received from mobile applicaiton to the database

There is a controller that handles a single HTTP method -  POST.

## Example:

End point - `/wifi/locations`

Request body :

````
[
    {
    "latitude":"42.698334",
    "longitude":"23.319941",
    "localDateTime":"2020-08-03T10:32:42.851",
"wifiScanResults":
[
    {
  "ssid":"somevalue",
  "rssi":"-50",
  "bssid":"so:me:bs:s:id",
  "capabilities":"somecapabilities",
  "frequency":"2437"
    }
 ]
}
]
````
Response :
````
[
    {
        "id": 266622,
        "latitude": 42.698334,
        "longitude": 23.319941,
        "localDateTime": "2020-08-03T10:32:42.851",
        "wifiScanResults": [
            {
                "id": 266623,
                "bssid": "so:me:bs:s:id",
                "ssid": "somevalue",
                "rssi": -50,
                "capabilities": "somecapabilities",
                "frequency": 2437
            }
        ]
    }
]
````

* Data is stored in object-relational database management system PostgreSQL.

* The whole functionality is tested with JUnit5.
