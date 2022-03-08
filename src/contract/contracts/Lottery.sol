// SPDX-License-Identifier: MIT
pragma solidity >=0.7.22 <0.9.0;

contract Lottery
{
    address public owner;
    uint256 private _pot;

    constructor()
    {
        owner = msg.sender;
    }

    function getPot() public view returns (uint256)
    {
        return _pot;
    }

    function getNumber(uint256 num) public pure returns (uint256)
    {
        return num;
    }

    function getOwner() public view returns (address)
    {
        return owner;
    }

    function setPot(uint256 pot) public
    {
        _pot = pot;
    }
}