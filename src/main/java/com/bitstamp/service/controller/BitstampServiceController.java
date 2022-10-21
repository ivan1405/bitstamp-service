package com.bitstamp.service.controller;

import com.bitstamp.service.model.api.CryptoTransactionResponse;
import com.bitstamp.service.model.api.UserTransactionResponse;
import com.bitstamp.service.model.api.WithdrawalResponse;
import com.bitstamp.service.service.BitstampClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BitstampServiceController {

    private final BitstampClientService bitstampClientService;

    public BitstampServiceController(final BitstampClientService bitstampClientService) {
        this.bitstampClientService = bitstampClientService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user-transactions")
    public ResponseEntity<List<UserTransactionResponse>> getUserTransactions() {
        return ResponseEntity.ok(bitstampClientService.fetchUserTransactions());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user-transactions/{currency}")
    public ResponseEntity<List<UserTransactionResponse>> getUserTransactions(@PathVariable("currency") String currency) {
        return ResponseEntity.ok(bitstampClientService.fetchUserTransactions(currency));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/crypto-transactions")
    public ResponseEntity<CryptoTransactionResponse> getCryptoTransactions() {
        return ResponseEntity.ok(bitstampClientService.fetchCryptoTransactions());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/execute-withdrawal")
    public ResponseEntity<WithdrawalResponse> executeWithdrawal() {
        return ResponseEntity.ok(bitstampClientService.executeWithdrawal());
    }

}