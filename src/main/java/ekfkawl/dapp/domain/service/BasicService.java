package ekfkawl.dapp.domain.service;

import ekfkawl.dapp.domain.service.template.ContractTemplate;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;


@Service
//@RequiredArgsConstructor
public class BasicService {

    public EthereumService ethereumService;
    public ContractTemplate contractTemplate;

    public BasicService(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
        this.contractTemplate = new ContractTemplate(ethereumService);
    }

    public int getPot() throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("getPot", Collections.emptyList(), Arrays.asList(new TypeReference<Uint256>() {}));

        return ((BigInteger)ethereumService.ethCall(function)).intValue();
    }

    public void checkPositionIndex(String addr, int index, Long size) throws IOException, ExecutionException, InterruptedException {
        contractTemplate.execute(
                new Function("checkPositionIndex", Arrays.asList(new Address(addr), new Uint256(index), new Uint256(size)), Collections.emptyList()),
                null);
    }

    public void transfer(String addr) throws IOException, ExecutionException, InterruptedException {
        contractTemplate.execute(new Function("transTest", Arrays.asList(new Address(addr)), Collections.emptyList()),
                BigInteger.valueOf(1000000000000000000L));
    }
}
