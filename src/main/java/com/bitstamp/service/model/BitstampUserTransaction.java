package com.bitstamp.service.model;

import lombok.Data;

@Data
public class BitstampUserTransaction {

    private Integer id;
    private Float fee;
    private Float btc_usd;
    private Float usd;
    private Float btc;
    private Float type;
    private Float xrp;
    private Float eur;
}