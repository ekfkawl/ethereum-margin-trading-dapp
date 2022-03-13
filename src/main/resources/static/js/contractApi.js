bat = async (entryUSD, size) => {
    let nonce = await web3.eth.getTransactionCount(account);
    contract.methods.bat(entryUSD, true, masterAddress).send({
        from: account,
        value: size,
        gas: 300000,
        nonce: nonce
    }).on('transactionHash', (hash) => {
        this.web3.eth.getTransactionReceipt(hash).then(console.log);
        onUpdate();
    }).on('error', function(error){
        switch (error.code) {
            case -32603:
                console.log('plz bat 0.01 eth ~');
                break;
            case 4001:
                console.log('cancel');
                break;
        }
    })
}

getBats = async () => {
    return await this.contract.methods.getBats(account).call();
}

getBatsEx = async (topicsHash) => {
    let res = [];

    let blockCount = await web3.eth.getBlockNumber();
    for (let i = 1; i < blockCount; i++) {
        let block = await web3.eth.getBlock(i);
        let tr = await this.web3.eth.getTransactionReceipt(block.transactions);

        if (tr.logs.length == 0 || !tr.logs[0].topics) {
            continue;
        }

        if (tr.logs[0].topics == topicsHash) {
            let t = await web3.eth.getTransaction(block.transactions);
            tr.value = t.value;

            res.push(tr);
        }
    }

    return res;
}

initWeb3 = async() => {
    if (window.ethereum) {
        this.web3 = new Web3(window.ethereum);
        try {
            await window.ethereum.enable();
            console.log('enabled');
        } catch (error) {
            console.log(`User denied account access error : ${error}`)
        }
    } else if (window.web3) {
        this.web3 = new Web3(Web3.currentProvider);
        console.log('new Web3');
    } else {
        console.log('Non-Ethereum browser detected');
    }

    let accounts = await this.web3.eth.getAccounts();
    this.account = accounts[0];
    this.contract = new this.web3.eth.Contract(contractABI, contractAddress);
}

async function asyncCall(f) {
    f();
}