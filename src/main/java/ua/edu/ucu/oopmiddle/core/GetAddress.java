package parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import lombok.SneakyThrows;

public class GetAddress {
    @SneakyThrows
    public String getAdress(String domen) {
        String API_KEY = "";
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        PlacesSearchResponse placesRespose = new TextSearchRequest(context).query(domen).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String address = placesRespose.results[0].formattedAddress;
        String placeId = placesRespose.results[0].placeId;

        PlaceDetails placeDetails = new PlaceDetailsRequest(context).placeId(placeId).await();
        return address;

    }
}
