package ua.edu.ucu.oopmiddle.core.scrapers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import ua.edu.ucu.oopmiddle.core.CompanyInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class PDLScraper implements Scraper {
    @Override
    public void scrapeTo(CompanyInfo companyInfo) {
        String query = URLEncoder.encode(
                "SELECT NAME FROM COMPANY WHERE WEBSITE='" + companyInfo.getDomain() + "'",
                StandardCharsets.UTF_8);
        try {
            URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", System.getenv("PDL_API_KEY"));
            connection.connect();

            String data = new String(connection.getInputStream().readAllBytes());
            JSONArray jsonArray = new JSONObject(data).getJSONArray("data");
            JSONObject dataObject = jsonArray.getJSONObject(0);

            String employees = dataObject.optString("employee_count");
            if (!employees.isEmpty())
                companyInfo.setEmployees(employees);

            String name = dataObject.optString("name");
            if (!name.isEmpty())
                companyInfo.setName(name);

            String twitterURL = dataObject.optString("twitter_url");
            if (!twitterURL.isEmpty() && companyInfo.getTwitterURL() == null)
                companyInfo.setTwitterURL(twitterURL);

            String facebookURL = dataObject.optString("facebook_url");
            if (!facebookURL.isEmpty() && companyInfo.getFacebookURL() == null)
                companyInfo.setFacebookURL(facebookURL);
        } catch (IOException ignored) {
        }
    }
}
