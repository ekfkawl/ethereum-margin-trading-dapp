package ekfkawl.dapp.domain.service;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
class BTCServiceTest {

    @Autowired
    BTCService btcService;

    @Test
    public void btcPrice() throws IOException, ParseException {
        System.out.println(btcService.getCurrentPriceUSD("BTC"));
    }

}