package com.bitstamp.service.config;

import com.bitstamp.service.feign.BitstampClient;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Setter;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Configuration
@EnableFeignClients(clients = {BitstampClient.class})
@Setter
public class BitstampConfig implements RequestInterceptor {

    @Value("${bitstamp.baseUrl}")
    private String baseUrl;

    @Value("${bitstamp.apiKey}")
    private String apiKey;

    @Value("${bitstamp.apiSecret}")
    private String apiSecret;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String apiKey = String.format("%s %s", "BITSTAMP", this.apiKey);
        String httpVerb = requestTemplate.request().httpMethod().name();
        String urlHost = this.baseUrl.replace("https://", "");
        String urlPath = requestTemplate.request().url();
        String urlQuery = requestTemplate.queryLine();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nonce = UUID.randomUUID().toString();
        String version = "v2";
        String signatureParams = apiKey + httpVerb + urlHost + urlPath + urlQuery + nonce + timestamp + version;

        requestTemplate.header("Accept", "*/*");
        requestTemplate.header("Host", urlHost);
        requestTemplate.header("X-Auth", apiKey);
        requestTemplate.header("X-Auth-Timestamp", timestamp);
        requestTemplate.header("X-Auth-Nonce", nonce);
        requestTemplate.header("X-Auth-Version", version);
        requestTemplate.header("X-Auth-Signature", sign(signatureParams));
    }

    private String sign(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(this.apiSecret.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            return new String(Hex.encodeHex(rawHmac)).toUpperCase();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}