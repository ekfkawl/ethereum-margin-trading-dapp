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
    void checkPosition() throws IOException, ExecutionException, InterruptedException {
        basicService.checkPositionIndex("0xc40E583F30ab5C4c03f1EAc217b39dF4D569fE80", 2, Long.parseUnsignedLong("1000000000000000000"));
    }

    @Test
    void transfer() throws IOException, ExecutionException, InterruptedException {
        basicService.transfer("0xED5A49F23dfe3f169915A98Cf0eD266Cd6d7f998");
    }
}