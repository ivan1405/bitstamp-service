package com.bitstamp.service.service;

import com.bitstamp.service.model.api.CryptoTransactionResponse;
import com.bitstamp.service.model.api.UserTransactionResponse;
import com.bitstamp.service.model.api.WithdrawalResponse;

import java.util.List;

public interface BitstampClientService {

    List<UserTransactionResponse> fetchUserTransactions();

    List<UserTransactionResponse> fetchUserTransactions(String currency);

    CryptoTransactionResponse fetchCryptoTransactions();

    WithdrawalResponse executeWithdrawal();
}