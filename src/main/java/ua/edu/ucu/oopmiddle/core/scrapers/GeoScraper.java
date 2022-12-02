package ua.edu.ucu.oopmiddle.core.scrapers;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.stereotype.Component;
import ua.edu.ucu.oopmiddle.core.CompanyInfo;

import java.io.IOException;

@Component
public class GeoScraper implements Scraper {
    @Override
    public void scrapeTo(CompanyInfo companyInfo) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(System.getenv("GOOGLE_API_KEY"))
                .build();
        try {
            PlacesSearchResponse placesSearchResponse = new TextSearchRequest(context)
                    .query(companyInfo.getDomain()).await();
            String placeId = placesSearchResponse.results[0].placeId;

            PlaceDetails placeDetails = new PlaceDetailsRequest(context).placeId(placeId).await();

            companyInfo.setAddress(placeDetails.formattedAddress);
        } catch (ApiException | InterruptedException | IOException | ArrayIndexOutOfBoundsException ignored) {
        }
    }
}
