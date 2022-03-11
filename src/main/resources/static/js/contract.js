let contractAddress = '0xED5A49F23dfe3f169915A98Cf0eD266Cd6d7f998';

let contractABI = [
    {
        "inputs": [],
        "stateMutability": "payable",
        "type": "constructor",
        "payable": true
    },
    {
        "anonymous": false,
        "inputs": [
            {
                "indexed": false,
                "internalType": "uint256",
                "name": "_value",
                "type": "uint256"
            }
        ],
        "name": "changeRet",
        "type": "event"
    },
    {
        "anonymous": false,
        "inputs": [
            {
                "indexed": false,
                "internalType": "string",
                "name": "_order",
                "type": "string"
            },
            {
                "indexed": false,
                "internalType": "address",
                "name": "_sender",
                "type": "address"
            },
            {
                "indexed": false,
                "internalType": "bool",
                "name": "_isBatLong",
                "type": "bool"
            },
            {
                "indexed": false,
                "internalType": "address payable",
                "name": "_to",
                "type": "address"
            }
        ],
        "name": "sendBat",
        "type": "event"
    },
    {
        "anonymous": false,
        "inputs": [
            {
                "indexed": false,
                "internalType": "address",
                "name": "_sender",
                "type": "address"
            },
            {
                "indexed": false,
                "internalType": "uint256",
                "name": "_currentValue",
                "type": "uint256"
            }
        ],
        "name": "sendEvent",
        "type": "event"
    },
    {
        "stateMutability": "payable",
        "type": "receive",
        "payable": true
    },
    {
        "inputs": [
            {
                "internalType": "address payable",
                "name": "_to",
                "type": "address"
            }
        ],
        "name": "transfer",
        "outputs": [],
        "stateMutability": "payable",
        "type": "function",
        "payable": true
    },
    {
        "inputs": [
            {
                "internalType": "address",
                "name": "_addr",
                "type": "address"
            }
        ],
        "name": "getBalance",
        "outputs": [
            {
                "internalType": "uint256",
                "name": "",
                "type": "uint256"
            }
        ],
        "stateMutability": "view",
        "type": "function",
        "constant": true
    },
    {
        "inputs": [
            {
                "internalType": "uint256",
                "name": "_entryValue",
                "type": "uint256"
            },
            {
                "internalType": "bool",
                "name": "_isBatLong",
                "type": "bool"
            },
            {
                "internalType": "address payable",
                "name": "_to",
                "type": "address"
            }
        ],
        "name": "bat",
        "outputs": [],
        "stateMutability": "payable",
        "type": "function",
        "payable": true
    },
    {
        "inputs": [],
        "name": "getBats",
        "outputs": [
            {
                "components": [
                    {
                        "internalType": "uint256",
                        "name": "entryValue",
                        "type": "uint256"
                    },
                    {
                        "internalType": "uint256",
                        "name": "batValue",
                        "type": "uint256"
                    },
                    {
                        "internalType": "bool",
                        "name": "isBatLong",
                        "type": "bool"
                    },
                    {
                        "internalType": "bool",
                        "name": "isClose",
                        "type": "bool"
                    }
                ],
                "internalType": "struct FutureTrade.Margin[]",
                "name": "",
                "type": "tuple[]"
            }
        ],
        "stateMutability": "view",
        "type": "function",
        "constant": true
    },
    {
        "inputs": [
            {
                "internalType": "address",
                "name": "_addr",
                "type": "address"
            },
            {
                "internalType": "uint256",
                "name": "_index",
                "type": "uint256"
            },
            {
                "internalType": "uint256",
                "name": "_size",
                "type": "uint256"
            }
        ],
        "name": "checkPositionIndex",
        "outputs": [],
        "stateMutability": "payable",
        "type": "function",
        "payable": true
    },
    {
        "inputs": [
            {
                "internalType": "address",
                "name": "_addr",
                "type": "address"
            },
            {
                "internalType": "uint256",
                "name": "currentBTCPrice",
                "type": "uint256"
            }
        ],
        "name": "checkBat",
        "outputs": [],
        "stateMutability": "payable",
        "type": "function",
        "payable": true
    },
    {
        "inputs": [
            {
                "internalType": "address",
                "name": "_to",
                "type": "address"
            }
        ],
        "name": "batSuccess",
        "outputs": [],
        "stateMutability": "payable",
        "type": "function",
        "payable": true
    },
    {
        "inputs": [
            {
                "internalType": "address",
                "name": "_addr",
                "type": "address"
            }
        ],
        "name": "transTest",
        "outputs": [],
        "stateMutability": "payable",
        "type": "function",
        "payable": true
    }
];