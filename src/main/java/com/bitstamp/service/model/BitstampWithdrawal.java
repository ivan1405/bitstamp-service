package com.bitstamp.service.model;

import feign.form.FormProperty;
import lombok.Data;

@Data
public class BitstampWithdrawal {

    private String type;
    private Float amount;
    @FormProperty("account_currency")
    private Currency accountCurrency;
    private java.lang.String name;
    private java.lang.String iban;
    @FormProperty("postal_code")
    private java.lang.String postalCode;
    private java.lang.String address;
    private java.lang.String city;
    private CountryIsoCode country;
}