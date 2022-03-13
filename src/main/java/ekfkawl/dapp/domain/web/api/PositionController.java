package ekfkawl.dapp.domain.web.api;

import ekfkawl.dapp.domain.service.BasicService;
import ekfkawl.dapp.domain.web.response.SingleResult;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class PositionController {
    private final BasicService basicService;

    @PostMapping("api/position/{addr}/{idx}")
    public void calPosition(@PathVariable String addr, @PathVariable String idx) throws IOException, ParseException, ExecutionException, InterruptedException {
        basicService.calBatPool(addr, Integer.parseInt(idx));
    }

}
