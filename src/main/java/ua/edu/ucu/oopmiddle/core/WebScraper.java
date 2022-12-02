package ua.edu.ucu.oopmiddle.core;

import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.ucu.oopmiddle.core.scrapers.Scraper;

import java.util.List;

public class WebScraper {
    @Autowired
    private List<Scraper> scrapers;

    public CompanyInfo fetchCompanyInfo(String domain) {
        CompanyInfo companyInfo = new CompanyInfo(domain);
        scrapers.forEach(x -> x.scrapeTo(companyInfo));

        return companyInfo;
    }
}
