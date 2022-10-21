package com.bitstamp.service.service;

import com.bitstamp.service.feign.BitstampClient;
import com.bitstamp.service.mapper.BitstampServiceMapper;
import com.bitstamp.service.model.BitstampCryptoTransactions;
import com.bitstamp.service.model.BitstampUserTransaction;
import com.bitstamp.service.model.api.CryptoTransactionResponse;
import com.bitstamp.service.model.api.UserTransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitstampClientServiceImpl implements BitstampClientService {

    private final BitstampClient bitstampClient;
    private final BitstampServiceMapper bitstampServiceMapper;

    public BitstampClientServiceImpl(final BitstampClient bitstampClient,
                                     final BitstampServiceMapper bitstampServiceMapper) {
        this.bitstampClient = bitstampClient;
        this.bitstampServiceMapper = bitstampServiceMapper;
    }

    @Override
    public List<UserTransactionResponse> fetchUserTransactions() {
        List<BitstampUserTransaction> userTransactions = bitstampClient.getUserTransactions();
        return bitstampServiceMapper.toUserTransactions(userTransactions);
    }

    @Override
    public List<UserTransactionResponse> fetchUserTransactions(String currency) {
        List<BitstampUserTransaction> userTransactions = bitstampClient.getUserTransactions(currency);
        return bitstampServiceMapper.toUserTransactions(userTransactions);
    }

    @Override
    public CryptoTransactionResponse fetchCryptoTransactions() {
        BitstampCryptoTransactions cryptoTransactions = bitstampClient.getCryptoTransactions();
        return bitstampServiceMapper.toCryptoTransactionResponse(cryptoTransactions);
    }

}