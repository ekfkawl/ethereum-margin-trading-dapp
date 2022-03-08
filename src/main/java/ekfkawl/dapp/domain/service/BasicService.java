package ekfkawl.dapp.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;


@Service
//@RequiredArgsConstructor
public class BasicService {

    public EthereumService ethereumService;

    public BasicService(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    public int getPot() throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("getPot", Collections.emptyList(), Arrays.asList(new TypeReference<Uint256>() {}));

        return ((BigInteger)ethereumService.ethCall(function)).intValue();
    }

    public void getNumber(int num)
    {
        // this method implement next posting
    }

    public void getOwner()
    {
        // this method implement next posting
    }

    public void setPot(int num) throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("setPot", Arrays.asList(new Uint256(num)), Collections.emptyList());

        String txHash = ethereumService.ethSendTransaction(function);

        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

    public void tests() {
        System.out.println("Dasdasd");
    }
}
