package com.bitstamp.service.model;

import lombok.Data;

@Data
public class BitstampCryptoTransaction {

    private WithdrawalType txid;
    private WithdrawalType destinationAddress;
    private Float amount;
    private WithdrawalType currency;
}