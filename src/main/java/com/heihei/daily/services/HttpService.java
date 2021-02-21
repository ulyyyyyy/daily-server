package com.heihei.daily.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class HttpService {

    public <T,B> T request(String url, HttpMethod httpMethod, B body, Class<T> bodyType) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), StandardCharsets.UTF_8);

        httpHeaders.setContentType(mediaType);
        HttpEntity<B> entity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<T> exchange = restTemplate.exchange(url, httpMethod, entity, bodyType);
        return exchange.getBody();
    }

}
