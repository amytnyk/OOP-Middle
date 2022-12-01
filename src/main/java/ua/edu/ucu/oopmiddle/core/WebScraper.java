package ua.edu.ucu.oopmiddle.core;

import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinService;

public class WebScraper {
    public CompanyInfo fetchCompanyInfo(String domain) {
//        Document doc = Jsoup.connect("https://ucu.edu.ua")
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
//                .get();
        return CompanyInfo.builder()
                .domain(domain)
                .name(domain)

                .employees("100-200")
                .twitterURL("https://twitter.com/" + domain)
                .facebookURL("https://facebook.com/" + domain)
                .address("Boston")
                .logo(VaadinService.getCurrent().getResourceAsStream("WEB-INF/images/default_logo.png"))
                .build();
    }
}
