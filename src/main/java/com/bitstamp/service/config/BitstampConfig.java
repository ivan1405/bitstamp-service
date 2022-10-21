package com.bitstamp.service.config;

import feign.Client;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.form.ContentType;
import lombok.Setter;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Configuration
@Setter
public class BitstampConfig implements RequestInterceptor {

    private final String baseUrl;
    private final String apiKey;
    private final String apiSecret;

    public BitstampConfig(@Value("${bitstamp.baseUrl}") final String baseUrl,
                          @Value("${bitstamp.apiKey}") final String apiKey,
                          @Value("${bitstamp.apiSecret}") final String apiSecret) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

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
        String contentType = requestTemplate.headers().containsKey("Content-Type") ? ContentType.URLENCODED.getHeader() : "";
        String payloadString = requestTemplate.request().body() != null ? new String(requestTemplate.request().body()) : "";
        String signatureParams = apiKey + httpVerb + urlHost + urlPath + urlQuery + contentType + nonce + timestamp + version + payloadString;

        requestTemplate.header("Accept", "*/*");
        requestTemplate.header("Host", urlHost);
        requestTemplate.header("X-Auth", apiKey);
        requestTemplate.header("X-Auth-Timestamp", timestamp);
        requestTemplate.header("X-Auth-Nonce", nonce);
        requestTemplate.header("X-Auth-Version", version);
        requestTemplate.header("X-Auth-Signature", sign(signatureParams));
        if(!contentType.equals("")) {
            requestTemplate.header("Content-Type", contentType);
        }
    }

    @Bean
    @ConditionalOnProperty(prefix = "proxy", name = "host")
    public Client feignClient(@Value("${proxy.host}") String proxyHost,
                              @Value("${proxy.port}") Integer proxyPort) {
        return new Client.Proxied(null, null,
                new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
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