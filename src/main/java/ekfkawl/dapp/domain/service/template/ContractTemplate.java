package ekfkawl.dapp.domain.service.template;

import ekfkawl.dapp.domain.service.EthereumService;
import lombok.RequiredArgsConstructor;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ContractTemplate {
    private final EthereumService ethereumService;

    public ContractTemplate(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    public void transaction(ContractCallback callback, Function function, BigInteger value) throws IOException, ExecutionException, InterruptedException {
        String txHash = ethereumService.ethSendTransaction(function, value);
        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

    public void transaction(Function function, BigInteger value) throws IOException, ExecutionException, InterruptedException {
        String txHash = ethereumService.ethSendTransaction(function, value);
        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

    public List<Type> call(Function function) throws IOException {
        List<Type> res = ethereumService.ethCall(function);
        for (Type type : res) {
            System.out.println("v = " + type.getValue());
        }
        return res;
    }

}
