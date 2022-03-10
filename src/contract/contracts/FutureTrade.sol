// SPDX-License-Identifier: MIT
pragma solidity >=0.7.22 <0.9.0;

contract FutureTrade {

    event sendBat(string _order, address _sender, bool _isBatLong, address payable _to);
    event sendEvent(address _sender, uint256 _currentValue);
    event changeRet(uint256 _value);

    uint256 testv;

    address owner;

    struct Margin {
        uint256 entryValue;
        uint256 batValue;
        bool isBatLong;
        bool isClose;
    }

    mapping (address => Margin[]) addrMargin;
    mapping (address => uint256) addrMarginRound;

    constructor() payable {
        owner = msg.sender;
    }

    receive() external payable {

    }

    modifier onlyOwner {
        require(msg.sender == owner, "only owner");
        _;
    }


    function transfer(address payable _to) public payable {
        _to.transfer(msg.value);
    }

    function getBalance(address _addr) public view returns(uint256) {
        return _addr.balance;
    }

    function bat(uint256 _entryValue, bool _isBatLong, address payable _to) public payable {
        require(msg.value >= 0.01 ether, "bat 0.01 eth~");

        Margin memory m;
        m.entryValue = _entryValue;
        m.batValue = msg.value;
        m.isBatLong = _isBatLong;
        m.isClose = false;
        addrMargin[msg.sender].push(m);
        _to.transfer(msg.value);

        emit sendBat("bat", msg.sender, _isBatLong, _to);
    }

    function getBat0() public view returns(uint256) {
        return addrMargin[msg.sender][0].batValue;
    }

    function getTest() public view returns(uint256) {
        return testv;
    }

    function setTest(uint256 _v) public {
        testv = _v;
    }

    function checkBat(address _addr, uint256 currentBTCPrice) public payable onlyOwner {
        uint256 sz = addrMargin[_addr].length;
        require(sz > 0);

        Margin memory m;
        for (uint i = 0; i < sz; i++) {
            if (addrMargin[_addr][i].isClose) {
                continue;
            }

            m = addrMargin[_addr][i];

            uint256 v = (m.batValue / 100) * (currentBTCPrice * 100 / m.entryValue);

            emit changeRet(v);

            (bool sent,) = payable(_addr).call{value:v}("");
            require(sent, "failed to pay");

            addrMargin[_addr][i].isClose = true;
        }
    }

    function batSuccess(address _to) public payable onlyOwner {
        uint256 v = addrMargin[_to][0].batValue;
        (bool sent,) = payable(_to).call{value:v}("");
        require(sent, "Failed to pay");
    }

    function sizetest() public view returns(uint256) {
        return addrMargin[msg.sender].length - 1;
    }

}
