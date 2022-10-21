package com.bitstamp.service.model.api;

public enum TransactionType {

    DEPOSIT(0),
    WITHDRAWAL(1),
    MARKET_TRADE(2),
    SUB_ACCOUNT_TRANSFER(14),
    CREDITED_WITH_STAKED_ASSETS(25),
    SENT_ASSETS_TO_STAKING(26),
    STAKING_REWARD(27),
    REFERRAL_REWARD(32),
    INTER_ACCOUNT_TRANSFER(35),

    UNKNOWN(99);

    private final Integer bitstampCode;

    TransactionType(Integer bitstampCode) {
        this.bitstampCode = bitstampCode;
    }

    public Integer getBitstampCode() {
        return bitstampCode;
    }
}