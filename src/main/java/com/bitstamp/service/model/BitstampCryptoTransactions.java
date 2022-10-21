package com.bitstamp.service.model;

import lombok.Data;

import java.util.List;

@Data
public class BitstampCryptoTransactions {

    private List<BitstampCryptoTransaction> deposits;
    private List<BitstampCryptoTransaction> withdrawals;
}