package ekfkawl.dapp.domain.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class BasicServiceTest {

    @Autowired
    BasicService basicService;

    @Test
    void get1() throws IOException, ExecutionException, InterruptedException {
        int pot = basicService.getPot();
        System.out.println("pot = " + pot);
    }

    @Test
    void set1() throws IOException, ExecutionException, InterruptedException {
        basicService.setPot(123);
    }
}