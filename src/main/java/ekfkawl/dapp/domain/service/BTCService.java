package ekfkawl.dapp.domain.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BTCService {

    public Double getCurrentPriceUSD(String symbol) throws IOException, ParseException {
        String doc = Jsoup.connect(String.format("https://min-api.cryptocompare.com/data/price?fsym=%s&tsyms=USD", symbol))
                .ignoreContentType(true)
                .execute().body();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(doc);

        return Double.parseDouble(jsonObject.get("USD").toString());
    }
}
