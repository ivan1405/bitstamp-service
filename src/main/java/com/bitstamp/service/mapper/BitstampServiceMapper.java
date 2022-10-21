package com.bitstamp.service.mapper;

import com.bitstamp.service.model.BitstampCryptoTransaction;
import com.bitstamp.service.model.BitstampCryptoTransactions;
import com.bitstamp.service.model.BitstampUserTransaction;
import com.bitstamp.service.model.api.CryptoTransaction;
import com.bitstamp.service.model.api.CryptoTransactionResponse;
import com.bitstamp.service.model.api.TransactionType;
import com.bitstamp.service.model.api.UserTransactionResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.List;

import static com.bitstamp.service.model.api.TransactionType.UNKNOWN;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BitstampServiceMapper {

    @Mapping(source = "type", target = "transactionType")
    UserTransactionResponse toUserTransaction(BitstampUserTransaction userTransaction);

    List<UserTransactionResponse> toUserTransactions(List<BitstampUserTransaction> bitstampUserTransactions);

    @Mapping(source = "txid", target = "transactionId")
    CryptoTransaction toCryptoTransaction(BitstampCryptoTransaction cryptoTransaction);

    CryptoTransactionResponse toCryptoTransactionResponse(BitstampCryptoTransactions bitstampCryptoTransactions);

    default TransactionType toTransactionType(Integer type) {
        return Arrays.stream(TransactionType.values())
                .filter(value -> value.getBitstampCode().equals(type))
                .findFirst()
                .orElse(UNKNOWN);
    }
}