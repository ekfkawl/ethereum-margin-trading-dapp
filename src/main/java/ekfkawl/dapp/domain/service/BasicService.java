package ekfkawl.dapp.domain.service;

import ekfkawl.dapp.domain.service.template.ContractTemplate;
import ekfkawl.dapp.global.dto.BatDTO;
import ekfkawl.dapp.global.utils.MathUtil;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Service
//@RequiredArgsConstructor
public class BasicService {

    public EthereumService ethereumService;
    public ContractTemplate contractTemplate;
    public BTCService btcService;

    public BasicService(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
        this.contractTemplate = new ContractTemplate(ethereumService);
        this.btcService = new BTCService();
    }

    public List<Type> getBatByAddress(String addr, int index) throws IOException {
        return contractTemplate.call(
                new Function("getBatByAddr",
                        Arrays.asList(new Address(addr), new Uint256(index)),
                        Arrays.asList(new TypeReference<Uint256>(){}, new TypeReference<Uint256>(){}, new TypeReference<Uint256>(){}, new TypeReference<Uint256>(){}))
        );
    }

    public List<String> getBatAddrList() throws IOException {
        HashSet<String> hRes = new HashSet<>();

        int i = 0;
        while (true) {
            List<Type> response = contractTemplate.call(
                    new Function("getBatAddr",
                            Arrays.asList(new Uint256(i++)),
                            Arrays.asList(new TypeReference<Address>() {
                            })));

            if (response.size() == 0) {
                break;
            }
            hRes.add((String)response.get(0).getValue());
        }

        return hRes.stream().collect(Collectors.toList());
    }

    public void calculateBat(String addr, int index, BigInteger value) throws IOException, ExecutionException, InterruptedException {
        contractTemplate.transaction(new Function("calculateBat", Arrays.asList(new Address(addr), new Uint256(index)), Collections.emptyList()), value);
    }

    public void checkBatPool() throws IOException, ParseException, ExecutionException, InterruptedException {

        Double eth = btcService.getCurrentPriceUSD("ETH");
        System.out.println("eth = " + eth);

        List<String> batAddrList = getBatAddrList();

        BatDTO batDTO = new BatDTO();
        for (String addr : batAddrList) {

            int index = 0;
            while (true) {
                List<Type> bat = getBatByAddress(addr, index);

                if (bat.size() == 0) {
                    break;
                }

                batDTO.setClose(new BigInteger("1").equals(bat.get(3).getValue()));
                if (!batDTO.isClose()) {
                    batDTO.setEntryValue(bat.get(0).getValue().toString());
                    batDTO.setBatValue(bat.get(1).getValue().toString());
                    batDTO.setBatLong(new BigInteger("1").equals(bat.get(2).getValue()));


                    BigInteger value = MathUtil.toPercentValue(Long.parseLong(batDTO.getEntryValue()), eth.longValue(), 10.0, new BigInteger(batDTO.getBatValue()));

                    if (value.compareTo(new BigInteger("0")) <= 0 ) {
                        calculateBat(addr, index, null);
                        System.out.println("send null");
                    }
//                    }else {
//                        calculateBat(addr, index, value);
//                        System.out.println("send value = " + value);
//                    }

                }

                index++;
            }

        }
    }


    public void calBatPool(String addr, int index) throws IOException, ParseException, ExecutionException, InterruptedException {

        Double eth = btcService.getCurrentPriceUSD("ETH");
        System.out.println("eth = " + eth);

        BatDTO batDTO = new BatDTO();

        List<Type> bat = getBatByAddress(addr, index);

        if (bat.size() == 0) {
            return;
        }

        batDTO.setClose(new BigInteger("1").equals(bat.get(3).getValue()));
        if (!batDTO.isClose()) {
            batDTO.setEntryValue(bat.get(0).getValue().toString());
            batDTO.setBatValue(bat.get(1).getValue().toString());
            batDTO.setBatLong(new BigInteger("1").equals(bat.get(2).getValue()));


            BigInteger value = MathUtil.toPercentValue(Long.parseLong(batDTO.getEntryValue()), eth.longValue(), 10.0, new BigInteger(batDTO.getBatValue()));

            if (value.compareTo(new BigInteger("0")) <= 0) {
                calculateBat(addr, index, null);
                System.out.println("send null");
            } else {
                calculateBat(addr, index, value);
                System.out.println("send value = " + value);
            }

        }

    }

}
