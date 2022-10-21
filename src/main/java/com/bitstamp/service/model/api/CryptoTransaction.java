package com.bitstamp.service.model.api;

import lombok.Data;

@Data
public class CryptoTransaction {

    private String transactionId;
    private String destinationAddress;
    private Float amount;
    private String currency;
}
