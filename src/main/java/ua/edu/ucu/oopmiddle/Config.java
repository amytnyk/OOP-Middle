package ua.edu.ucu.oopmiddle;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import ua.edu.ucu.oopmiddle.core.WebScraper;

@Configuration
@EnableAsync
@EnableCaching
public class Config {
    @Bean
    public WebScraper getWebScraper() {
        return new WebScraper();
    }
}
