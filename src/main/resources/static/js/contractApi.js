getPot = async () => {
    let pot = await this.contract.methods.getPot().call();
    console.log(pot);
}

bet = async () => {
    let nonce = await web3.eth.getTransactionCount(account);
    contract.methods.setPot(456).send({from:account, gas:300000, nonce:nonce})
        .on('transactionHash', (hash) =>{
            this.web3.eth.getTransactionReceipt(hash).then(console.log);
        })

}

initWeb3 = () => {
    if (window.ethereum) {
        this.web3 = new Web3(window.ethereum);
        try {
            window.ethereum.enable();
        } catch (error) {
            console.log(`User denied account access error : ${error}`)
        }
    } else if (window.web3) {
        this.web3 = new Web3(Web3.currentProvider);
    } else {
        console.log('Non-Ethereum browser detected');
    }

    let accounts = this.web3.eth.getAccounts();
    this.account = accounts[0];
    this.contract = new this.web3.eth.Contract(contractABI, contractAddress);
}

async function asyncCall(f) {
    f();
}