package ekfkawl.dapp.domain.service.template;

import ekfkawl.dapp.domain.service.EthereumService;
import lombok.RequiredArgsConstructor;
import org.web3j.abi.datatypes.Function;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class ContractTemplate {
    private final EthereumService ethereumService;

    public ContractTemplate(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    public void execute(ContractCallback callback, Function function, BigInteger value) throws IOException, ExecutionException, InterruptedException {
        String txHash = ethereumService.ethSendTransaction(function, value);
        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

    public void execute(Function function, BigInteger value) throws IOException, ExecutionException, InterruptedException {
        String txHash = ethereumService.ethSendTransaction(function, value);
        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

}
