// SPDX-License-Identifier: MIT
pragma solidity >=0.7.22 <0.9.0;

contract FutureTrade {

    event sendBat(string _order, address _sender, bool _isBatLong, address payable _to);
    event sendEvent(address _sender, uint256 _currentValue);
    event changeRet(uint256 _value);

    address owner;

    struct Margin {
        uint256 entryValue;
        uint256 batValue;
        bool isBatLong;
        bool isClose;
    }

    address[] batAddr;
    mapping (address => Margin[]) addrMargin;

    constructor() payable {
        owner = msg.sender;
    }

    receive() external payable {

    }

    modifier onlyOwner {
        require(msg.sender == owner, "only owner");
        _;
    }

    function calculateBat(address payable _to, uint256 _index) public payable onlyOwner{
        _to.transfer(msg.value);
        addrMargin[_to][_index].isClose = true;
    }

    function getBalance(address _addr) public view onlyOwner returns(uint256){
        return _addr.balance;
    }

    function getBatAddr(uint256 _index) public view returns(address) {
        return batAddr[_index];
    }

    function bat(uint256 _entryValue, bool _isBatLong, address payable _to) public payable {
        require(msg.value >= 0.01 ether, "bat 0.01~ eth");

        Margin memory m;
        m.entryValue = _entryValue;
        m.batValue = msg.value;
        m.isBatLong = _isBatLong;
        m.isClose = false;
        addrMargin[msg.sender].push(m);
        batAddr.push(msg.sender);

        _to.transfer(msg.value);

        emit sendBat("bat", msg.sender, _isBatLong, _to);
    }

    function getBats(address _addr) public view returns (Margin[] memory) {
        return addrMargin[_addr];
    }

    function getBatByAddr(address _addr, uint256 _index) public view returns (uint256, uint256, bool, bool) {
        Margin memory m = addrMargin[_addr][_index];

        return (m.entryValue, m.batValue, m.isBatLong, m.isClose);
    }
}
