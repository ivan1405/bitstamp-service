package com.bitstamp.service.service;

import com.bitstamp.service.feign.BitstampClient;
import com.bitstamp.service.mapper.BitstampServiceMapper;
import com.bitstamp.service.model.BitstampCryptoTransactions;
import com.bitstamp.service.model.BitstampUserTransaction;
import com.bitstamp.service.model.BitstampWithdrawal;
import com.bitstamp.service.model.api.CryptoTransactionResponse;
import com.bitstamp.service.model.api.UserTransactionResponse;
import com.bitstamp.service.model.api.WithdrawalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bitstamp.service.model.CountryIsoCode.GB;
import static com.bitstamp.service.model.Currency.EUR;
import static com.bitstamp.service.model.WithdrawalType.SEPA;

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
    public List<UserTransactionResponse> fetchUserTransactions(java.lang.String currency) {
        List<BitstampUserTransaction> userTransactions = bitstampClient.getUserTransactions(currency);
        return bitstampServiceMapper.toUserTransactions(userTransactions);
    }

    @Override
    public CryptoTransactionResponse fetchCryptoTransactions() {
        BitstampCryptoTransactions cryptoTransactions = bitstampClient.getCryptoTransactions();
        return bitstampServiceMapper.toCryptoTransactionResponse(cryptoTransactions);
    }

    @Override
    public WithdrawalResponse executeWithdrawal() {
        BitstampWithdrawal withdrawal = new BitstampWithdrawal();
        withdrawal.setType(SEPA.name().toLowerCase());
        withdrawal.setAmount(100.00F);
        withdrawal.setAccountCurrency(EUR);
        withdrawal.setCity("London");
        withdrawal.setName("Name");
        withdrawal.setAddress("test");
        withdrawal.setPostalCode("12345");
        withdrawal.setCountry(GB);
        withdrawal.setIban("GB29NWBK60161331926819");
        return bitstampClient.executeWithdrawal(withdrawal);
    }

}