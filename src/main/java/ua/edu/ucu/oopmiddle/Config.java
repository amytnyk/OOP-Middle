package ua.edu.ucu.oopmiddle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.edu.ucu.oopmiddle.core.WebScraper;

@Configuration
public class Config {
    @Bean
    public WebScraper getWebScraper() {
        return new WebScraper();
    }
}
