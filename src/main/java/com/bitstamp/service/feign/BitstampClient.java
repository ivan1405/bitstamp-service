package com.bitstamp.service.feign;

import com.bitstamp.service.config.BitstampConfig;
import com.bitstamp.service.model.BitstampCryptoTransactions;
import com.bitstamp.service.model.BitstampUserTransaction;
import com.bitstamp.service.model.BitstampWithdrawal;
import com.bitstamp.service.model.api.WithdrawalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name = "bitstampApiClient",
        url = "${bitstamp.baseUrl}",
        configuration = BitstampConfig.class
)
public interface BitstampClient {

    @PostMapping("/api/v2/user_transactions/")
    List<BitstampUserTransaction> getUserTransactions();

    @PostMapping("/api/v2/user_transactions/{currency}/")
    List<BitstampUserTransaction> getUserTransactions(@PathVariable("currency") String currency);

    @PostMapping("/api/v2/crypto-transactions/")
    BitstampCryptoTransactions getCryptoTransactions();

    @PostMapping(value = "/api/v2/withdrawal/open/", consumes = "application/x-www-form-urlencoded")
    WithdrawalResponse executeWithdrawal(BitstampWithdrawal bitstampWithdrawal);
}