package com.bitstamp.service.model;

import lombok.Data;

@Data
public class BitstampCryptoTransaction {

    private String txid;
    private String destinationAddress;
    private Float amount;
    private String currency;
}