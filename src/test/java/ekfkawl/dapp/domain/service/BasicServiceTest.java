package ekfkawl.dapp.domain.service;

import ekfkawl.dapp.global.utils.MathUtil;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.abi.datatypes.Type;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class BasicServiceTest {

    @Autowired
    BasicService basicService;

    @Autowired
    EthereumService ethereumService;

    @Test
    void get1() throws IOException {
        List<Type> batByAddress = basicService.getBatByAddress("0x4C4cC7A6a729bF4326eEe3194C879dF649a9ee28", 0);

        System.out.println(batByAddress.get(1).getValue());

    }

    @Test
    void transfer() throws IOException, ExecutionException, InterruptedException {
        basicService.calculateBat("0x4C4cC7A6a729bF4326eEe3194C879dF649a9ee28", 0, BigInteger.valueOf(1000000000000000000L));
    }

    @Test
    void block() throws IOException {
        System.out.println(basicService.getBatAddrList());
    }

    @Test
    void cal() throws IOException, ParseException, ExecutionException, InterruptedException {
        basicService.checkBatPool();
    }

    @Test
    void toPercentValueTest() {
        System.out.println(MathUtil.toPercentValue(2398, 2398 / 2, 10.0, new BigInteger("100")));
        System.out.println(MathUtil.toPercentValue(100, 92, 10.0, new BigInteger("100")));
        System.out.println(MathUtil.toPercentValue(100, 120, 1, new BigInteger("1000000000000000000000")));
    }

    @Test
    void BigIntCompareTest() {
        BigInteger v1 = new BigInteger("-1");
        BigInteger v2 = new BigInteger("0");
        if (v1.compareTo(v2) <= 0) {
            System.out.println("dd");
        }
    }
}