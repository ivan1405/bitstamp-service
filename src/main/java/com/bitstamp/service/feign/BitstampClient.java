package com.bitstamp.service.feign;

import com.bitstamp.service.model.BitstampCryptoTransactions;
import com.bitstamp.service.model.BitstampUserTransaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name = "bitstampApiClient",
        url = "${bitstamp.baseUrl}"
)
public interface BitstampClient {

    @PostMapping("/api/v2/user_transactions/")
    List<BitstampUserTransaction> getUserTransactions();

    @PostMapping("/api/v2/user_transactions/{currency}/")
    List<BitstampUserTransaction> getUserTransactions(@PathVariable("currency") String currency);

    @PostMapping("/api/v2/crypto-transactions/")
    BitstampCryptoTransactions getCryptoTransactions();
}