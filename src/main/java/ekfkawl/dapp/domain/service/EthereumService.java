package ekfkawl.dapp.domain.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;

@Component
public class EthereumService {
    private String contract = "0xeDa47b4B6fE8B047F76F7a13c3C994CfA5Fb519b";

    private String from = "0x7eC140D2AA2d549EFD6D3F11EAaFd00d48A73463";

    private String pwd = "238304e4feeb4817cdc112255f39ae1432f3ee8aa204b0c41185f8c40753b6a5";

    private Admin web3j = null;

    public EthereumService() {
        web3j = Admin.build(new HttpService()); // default server : http://localhost:8545
    }


    public int ethGetBlockCount() {
        Request<?, EthBlockNumber> ethBlockNumberRequest = web3j.ethBlockNumber();
        System.out.println(ethBlockNumberRequest);
        return 0;
    }

    public List<Type> ethCall(Function function) throws IOException {
        // 1. Account Lock 해제
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

        if (personalUnlockAccount.accountUnlocked()) { // unlock 일때

            //2. transaction 제작
            Transaction transaction = Transaction.createEthCallTransaction(from, contract,
                    FunctionEncoder.encode(function));

            //3. ethereum 호출후 결과 가져오기
            EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

            //4. 결과값 decode
            List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                    function.getOutputParameters());

            return decode;
        } else {
            throw new PersonalLockException("check ethereum personal Lock");
        }
    }

    public String ethSendTransaction(Function function, BigInteger value)
            throws IOException, InterruptedException, ExecutionException {

        // 1. Account Lock 해제
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

        if (personalUnlockAccount.accountUnlocked()) { // unlock 일때

            //2. account에 대한 nonce값 가져오기.
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                    from, DefaultBlockParameterName.LATEST).sendAsync().get();

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            //3. Transaction값 제작
            Transaction transaction = Transaction.createFunctionCallTransaction(from, nonce,
                    Transaction.DEFAULT_GAS,
                    null, contract,
                    value == null ? (BigInteger)null : value,
                    FunctionEncoder.encode(function));

            // 4. ethereum Call &
            EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction).send();

            // transaction에 대한 transaction Hash값 얻기.
            String transactionHash = ethSendTransaction.getTransactionHash();

            // ledger에 쓰여지기 까지 기다리기.
//            Thread.sleep(1000);

            return transactionHash;
        } else {
            throw new PersonalLockException("check ethereum personal Lock");
        }
    }

    public TransactionReceipt getReceipt(String transactionHash) throws IOException {

        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();

        if (transactionReceipt.getTransactionReceipt().isPresent()) {
        } else {
            System.out.println("transaction complete not yet");
        }

        return transactionReceipt.getResult();
    }

    private class PersonalLockException extends RuntimeException {
        public PersonalLockException(String msg) {
            super(msg);
        }
    }
}