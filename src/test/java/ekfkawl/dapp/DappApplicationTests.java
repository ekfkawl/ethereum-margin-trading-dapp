package ekfkawl.dapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Ethereum;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class DappApplicationTests {

	private Ethereum web3j;

	@Test
	void getBlockNumber() throws ExecutionException, InterruptedException {
		Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:8545/"));
		EthBlockNumber result = web3.ethBlockNumber().sendAsync().get();
		System.out.println(result.getBlockNumber());
	}

	@Test
	public void getEthClientVersionASync() throws Exception
	{
		Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
		Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
		System.out.println(web3ClientVersion.getWeb3ClientVersion());
	}

	@Test
	public void getEthClientVersionRx() throws Exception
	{
		Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
		web3.web3ClientVersion().flowable().subscribe(x -> {
			System.out.println(x.getWeb3ClientVersion());
		});
	}
}
