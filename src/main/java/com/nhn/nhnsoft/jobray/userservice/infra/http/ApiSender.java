package com.nhn.nhnsoft.jobray.userservice.infra.http;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class ApiSender {

    private final RestTemplate restTemplate;

    public ResponseEntity<Object> postApi(Object requestBody, String url) throws Exception {
        // Setting Header
        HttpHeaders headers = this.settingHeader();

        HttpEntity<?> entity = new HttpEntity<>(requestBody, headers);

        // Execute Http Method
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                new ParameterizedTypeReference<Object>() {
                });

        return response;
    }

    public ResponseEntity<Object> getApi(String url) throws Exception {
        // Setting Header
        HttpHeaders headers = this.settingHeader();

        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Execute Http Method
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<Object>() {
                });

        return response;
    }

    public ResponseEntity<Object> deleteApi(Object requestBody, URI url) throws Exception {
        // Setting Header
        HttpHeaders headers = this.settingHeader();

        HttpEntity<?> entity = new HttpEntity<>(requestBody, headers);

        // Execute Http Method
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.DELETE, entity,
                new ParameterizedTypeReference<Object>() {
                });

        return response;
    }

    public ResponseEntity<Object> deleteApi(String url) throws Exception {
        // Setting Header
        HttpHeaders headers = this.settingHeader();

        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Execute Http Method
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.DELETE, entity,
                new ParameterizedTypeReference<Object>() {
                });

        return response;
    }

    private HttpHeaders settingHeader() {
        // Init Header
        HttpHeaders headers = new HttpHeaders();
        // Set Content Type
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
