package parsing;

import java.io.IOException;
import java.util.Map;

public class GetInfo {
    private static final PDLParser pdlParser = new PDLParser();
    private static final BrendParser brendParser = new BrendParser();

    public void getInfo(String domen) throws IOException {
//        String domen = "Github.com";
        Map<String, String> pdl_info = pdlParser.getInfo(domen); // вертає словник {employee_count: value(can be null), facebook_url: value(can be null)}
        Map<String, String> brend_info = brendParser.getInfo(domen); // словник {name: url, facebook_url: url, twitter_url: url, logo: url, icon: url} значення можуть бути null, якщо в базі немає
    }
}
