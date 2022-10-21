package com.bitstamp.service.mapper;

import com.bitstamp.service.model.BitstampCryptoTransaction;
import com.bitstamp.service.model.BitstampCryptoTransactions;
import com.bitstamp.service.model.BitstampUserTransaction;
import com.bitstamp.service.model.api.CryptoTransaction;
import com.bitstamp.service.model.api.CryptoTransactionResponse;
import com.bitstamp.service.model.api.UserTransactionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-30T15:38:05+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4 (Amazon.com Inc.)"
)
@Component
public class BitstampServiceMapperImpl implements BitstampServiceMapper {

    @Override
    public UserTransactionResponse toUserTransaction(BitstampUserTransaction userTransaction) {
        if ( userTransaction == null ) {
            return null;
        }

        UserTransactionResponse userTransactionResponse = new UserTransactionResponse();

        if ( userTransaction.getType() != null ) {
            userTransactionResponse.setTransactionType( toTransactionType( userTransaction.getType().intValue() ) );
        }
        userTransactionResponse.setId( userTransaction.getId() );
        userTransactionResponse.setFee( userTransaction.getFee() );
        userTransactionResponse.setBtc_usd( userTransaction.getBtc_usd() );
        userTransactionResponse.setUsd( userTransaction.getUsd() );
        userTransactionResponse.setBtc( userTransaction.getBtc() );
        userTransactionResponse.setXrp( userTransaction.getXrp() );
        userTransactionResponse.setEur( userTransaction.getEur() );

        return userTransactionResponse;
    }

    @Override
    public List<UserTransactionResponse> toUserTransactions(List<BitstampUserTransaction> bitstampUserTransactions) {
        if ( bitstampUserTransactions == null ) {
            return null;
        }

        List<UserTransactionResponse> list = new ArrayList<UserTransactionResponse>( bitstampUserTransactions.size() );
        for ( BitstampUserTransaction bitstampUserTransaction : bitstampUserTransactions ) {
            list.add( toUserTransaction( bitstampUserTransaction ) );
        }

        return list;
    }

    @Override
    public CryptoTransaction toCryptoTransaction(BitstampCryptoTransaction cryptoTransaction) {
        if ( cryptoTransaction == null ) {
            return null;
        }

        CryptoTransaction cryptoTransaction1 = new CryptoTransaction();

        cryptoTransaction1.setTransactionId( cryptoTransaction.getTxid() );
        cryptoTransaction1.setDestinationAddress( cryptoTransaction.getDestinationAddress() );
        cryptoTransaction1.setAmount( cryptoTransaction.getAmount() );
        cryptoTransaction1.setCurrency( cryptoTransaction.getCurrency() );

        return cryptoTransaction1;
    }

    @Override
    public CryptoTransactionResponse toCryptoTransactionResponse(BitstampCryptoTransactions bitstampCryptoTransactions) {
        if ( bitstampCryptoTransactions == null ) {
            return null;
        }

        CryptoTransactionResponse cryptoTransactionResponse = new CryptoTransactionResponse();

        cryptoTransactionResponse.setDeposits( bitstampCryptoTransactionListToCryptoTransactionList( bitstampCryptoTransactions.getDeposits() ) );
        cryptoTransactionResponse.setWithdrawals( bitstampCryptoTransactionListToCryptoTransactionList( bitstampCryptoTransactions.getWithdrawals() ) );

        return cryptoTransactionResponse;
    }

    protected List<CryptoTransaction> bitstampCryptoTransactionListToCryptoTransactionList(List<BitstampCryptoTransaction> list) {
        if ( list == null ) {
            return null;
        }

        List<CryptoTransaction> list1 = new ArrayList<CryptoTransaction>( list.size() );
        for ( BitstampCryptoTransaction bitstampCryptoTransaction : list ) {
            list1.add( toCryptoTransaction( bitstampCryptoTransaction ) );
        }

        return list1;
    }
}
