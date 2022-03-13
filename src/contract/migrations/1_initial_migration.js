// const Migrations = artifacts.require("Migrations");
const FutureTrade = artifacts.require("FutureTrade");

module.exports = function (deployer) {
  // deployer.deploy(Migrations);
  deployer.deploy(FutureTrade);
};
