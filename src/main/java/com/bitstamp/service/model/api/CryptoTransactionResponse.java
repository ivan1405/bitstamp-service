package com.bitstamp.service.model.api;

import lombok.Data;

import java.util.List;

@Data
public class CryptoTransactionResponse {

    private List<CryptoTransaction> deposits;
    private List<CryptoTransaction> withdrawals;
}