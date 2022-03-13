package ekfkawl.dapp.domain.web.scheduler;

import ekfkawl.dapp.domain.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class PositionCheck {
    private final BasicService basicService;

    @Scheduled(fixedDelay = 10000)
    public void positionCheck() throws IOException, ParseException, ExecutionException, InterruptedException {
        basicService.checkBatPool();
        System.out.println("checkBatPool - " + System.currentTimeMillis() / 1000);
    }
}
