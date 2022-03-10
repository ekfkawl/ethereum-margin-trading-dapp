package ekfkawl.dapp.domain.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BTCService {

    public Double getLastBTCPrice() throws IOException, ParseException {
        String doc = Jsoup.connect("https://api.coindesk.com/v1/bpi/currentprice.json")
                .ignoreContentType(true)
                .execute().body();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(doc);
        String bpi = jsonObject.get("bpi").toString();

        jsonObject = (JSONObject)jsonParser.parse(bpi);
        String usd = jsonObject.get("USD").toString();

        jsonObject = (JSONObject)jsonParser.parse(usd);
        return Double.parseDouble(jsonObject.get("rate").toString().replaceAll(",", ""));

    }
}
