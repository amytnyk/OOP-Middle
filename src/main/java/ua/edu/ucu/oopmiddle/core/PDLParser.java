package ua.edu.ucu.oopmiddle.core;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class PDLParser {
    public Map<String, String> getInfo(String domen) throws IOException {
        String API_KEY = "8d9b6eab9428e854aecf2d2b81161cf7e84cd701014d88979fcacbf0666da9e9";
        String query = URLEncoder.encode("SELECT NAME FROM COMPANY WHERE WEBSITE='" + domen + "'", StandardCharsets.UTF_8);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
//        JSONObject jsonObject = new JSONObject(text);
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> mp = objectMapper.readValue(text, new TypeReference<>() {});
//        mp.
//        Map<String, String> dict = new HashMap<>();
//        try {
//            dict.put("employee_count", Integer.toString(jsonObject.getJSONArray("data").getJSONObject(0).getInt("employee_count")));
//            System.out.println(Integer.toString(jsonObject.getJSONArray("data").getJSONObject(0).getInt("employee_count")));
//        }
//        catch (JSONException e) {
//            dict.put("employee_count", "null");
//            System.out.println("null");
//        }
////        try {
////            dict.put("name", jsonObject.getJSONArray("data").getJSONObject(0).getString("name"));
////            System.out.println(jsonObject.getJSONArray("data").getJSONObject(0).getString("name"));
////        }
////        catch (JSONException e) {
////            dict.put("name", "null");
////            System.out.println("null");
////        }
//        try {
//            dict.put("facebook_url", jsonObject.getJSONArray("data").getJSONObject(0).getString("facebook_url"));
//            System.out.println(jsonObject.getJSONArray("data").getJSONObject(0).getString("facebook_url"));
//        } catch (JSONException e) {
//            dict.put("facebook_url", "null");
//            System.out.println("null");
//        }
//        return dict;
        return new HashMap<>();
    }
}
