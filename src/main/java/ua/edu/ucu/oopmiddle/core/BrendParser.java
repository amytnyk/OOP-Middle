package parsing;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class BrendParser {
    public Map<String, String> getInfo(String domen) throws IOException {
        String API_KEY = "Bearer DolFt2ECwwYUlZbgg0AOnE01TvNuAgJyN0s2vrct1W4=";
        URL url = new URL("https://api.brandfetch.io/v2/brands/" + domen);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        Map<String, String> dict = new HashMap<>();
        try {
            dict.put("name", jsonObject.getString("name"));
            System.out.println(jsonObject.getString("name"));
        } catch (JSONException e) {
            dict.put("name", "null");
            System.out.println("null");
        }
        for(int i = 0;i<jsonObject.getJSONArray("links").toList().size();++i) {
            if(Objects.equals(jsonObject.getJSONArray("links").getJSONObject(i).getString("name"), "twitter")) {
                dict.put("twitter", jsonObject.getJSONArray("links").getJSONObject(i).getString("url"));
                System.out.println(jsonObject.getJSONArray("links").getJSONObject(i).getString("url"));
            }
            if(Objects.equals(jsonObject.getJSONArray("links").getJSONObject(i).getString("name"), "facebook")) {
                dict.put("facebook", jsonObject.getJSONArray("links").getJSONObject(i).getString("url"));
                System.out.println(jsonObject.getJSONArray("links").getJSONObject(i).getString("url"));
            }
        }
        boolean logo = false, icon = false;
        for(int i = 0;i<jsonObject.getJSONArray("logos").toList().size();++i) {
            if(Objects.equals(jsonObject.getJSONArray("logos").getJSONObject(i).getString("type"), "logo") && !logo) {
                jsonObject.getJSONArray("logos").getJSONObject(i).getJSONArray("formats").getJSONObject(0).getString("src");
                dict.put("logo", jsonObject.getJSONArray("logos").getJSONObject(i).getJSONArray("formats")
                        .getJSONObject(0).getString("src"));
                System.out.println(jsonObject.getJSONArray("logos").getJSONObject(i).getJSONArray("formats")
                        .getJSONObject(0).getString("src"));
                logo = true;
            }
            if(Objects.equals(jsonObject.getJSONArray("logos").getJSONObject(i).getString("type"), "icon") && !icon) {
                jsonObject.getJSONArray("logos").getJSONObject(i).getJSONArray("formats").getJSONObject(0).getString("src");
                dict.put("icon", jsonObject.getJSONArray("logos").getJSONObject(i).getJSONArray("formats")
                        .getJSONObject(0).getString("src"));
                System.out.println(jsonObject.getJSONArray("logos").getJSONObject(i).getJSONArray("formats")
                        .getJSONObject(0).getString("src"));
                icon = true;
            }
        }
        return dict;
    }
}
