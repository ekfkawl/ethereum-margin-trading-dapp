package ekfkawl.dapp.domain.web.api;

import ekfkawl.dapp.domain.service.BTCService;
import ekfkawl.dapp.domain.web.response.SingleResult;
import ekfkawl.dapp.domain.web.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PriceController {

    private final ResponseService responseService;
    private final BTCService btcService;

    @GetMapping("api/btc")
    public SingleResult<Double> getLastBTCPrice() throws IOException, ParseException {
        return responseService.getSingleResult(btcService.getLastBTCPrice());
    }
}
