package resources;


import cucumber.pogo.AddPlace;
import cucumber.pogo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String placeName, String placeLanguage, String placeAddress) {
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(placeAddress);
        addPlace.setLanguage(placeLanguage);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setName(placeName);
        addPlace.setWebsite("http://google.com");

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        return addPlace;
    }

    public String deletePlacePayLoad(String placeId) {

        return "{\r\n\"place_id\":\""+placeId+"\"\r\n}";
    }
}
