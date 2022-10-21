package com.bitstamp.service.model.api;

import lombok.Data;

@Data
public class UserTransactionResponse {

    private Integer id;
    private TransactionType transactionType;
    private Float fee;
    private Float btc_usd;
    private Float usd;
    private Float btc;
    private Float xrp;
    private Float eur;
}
