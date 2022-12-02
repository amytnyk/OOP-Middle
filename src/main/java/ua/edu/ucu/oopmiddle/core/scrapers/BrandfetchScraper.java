package ua.edu.ucu.oopmiddle.core.scrapers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import ua.edu.ucu.oopmiddle.core.CompanyInfo;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.awt.image.*;

@Component
public class BrandfetchScraper implements Scraper {
    private InputStream downloadImage(String src) {
        try {
            BufferedImage image = ImageIO.read(new URL(src));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException | IllegalArgumentException ignored) {
            return null;
        }
    }

    private String getSuitableImageSource(JSONObject image) {
        JSONArray formats = image.getJSONArray("formats");
        for (int i = 0; i < formats.length(); ++i) {
            JSONObject format = formats.getJSONObject(i);
            if (!format.optString("format").equals("svg"))
                return format.optString("src");
        }
        return null;
    }

    @Override
    public void scrapeTo(CompanyInfo companyInfo) {
        try {
            URL url = new URL("https://api.brandfetch.io/v2/brands/" + companyInfo.getDomain());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", System.getenv("BRANDFETCH_API_KEY"));
            connection.connect();

            String data = new String(connection.getInputStream().readAllBytes());
            JSONObject jsonObject = new JSONObject(data);

            String name = jsonObject.optString("name");
            if (!name.isEmpty())
                companyInfo.setName(name);

            for (int i = 0; i < jsonObject.getJSONArray("links").length(); ++i) {
                JSONObject link = jsonObject.getJSONArray("links").getJSONObject(i);
                String linkName = link.optString("name");
                if (linkName.equals("twitter"))
                    companyInfo.setTwitterURL(link.optString("url"));
                else if (linkName.equals("facebook"))
                    companyInfo.setFacebookURL(link.optString("url"));
            }

            for (int i = 0; i < jsonObject.getJSONArray("logos").length(); ++i) {
                JSONObject image = jsonObject.getJSONArray("logos").getJSONObject(i);
                String imageType = image.optString("type");
                String src = getSuitableImageSource(image);
                if (src != null) {
                    InputStream srcInputStream = downloadImage(src);
                    if (imageType.equals("logo")) {
                        companyInfo.setLogo(srcInputStream);
                    } else if (imageType.equals("icon"))
                        companyInfo.setIcon(srcInputStream);
                }
            }
        } catch (IOException ignored) {
        }
    }
}
