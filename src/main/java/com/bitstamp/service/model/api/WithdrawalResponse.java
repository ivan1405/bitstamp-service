package com.bitstamp.service.model.api;

import com.bitstamp.service.model.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawalResponse {

    private Integer id;
    private String datetime;
    private Integer type;
    private Currency currency;
    private Float amount;
    private Integer status;
}