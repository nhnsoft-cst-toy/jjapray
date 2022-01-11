package com.nhn.nhnsoft.jobray.userservice.global.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return this.getCommonRestTemplate();
    }

    public RestTemplate getCommonRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        HttpClient client =
                HttpClientBuilder.create().setMaxConnTotal(50).setMaxConnPerRoute(20).build();

        factory.setHttpClient(client);
        factory.setConnectTimeout(2000);
        factory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
        ClientHttpRequestInterceptor interceptor = new RequestResponseLoggingInterceptor();

        restTemplate.getMessageConverters().add(0,
                new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.setInterceptors(Collections.singletonList(interceptor));

        return restTemplate;
    }

    class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

        Logger log = LoggerFactory.getLogger(RequestResponseLoggingInterceptor.class);

        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            traceRequest(request, body);
            ClientHttpResponse response = execution.execute(request, body);
            traceResponse(response);
            return response;
        }

        private void traceRequest(HttpRequest request, byte[] body) throws IOException {
            log.info(
                    "================================================request begin================================================");
            log.info("URI         : {}", request.getURI());
            log.info("Method      : {}", request.getMethod());
            log.info("Headers     : {}", request.getHeaders());
            log.info("Request body: {}", new String(body, "UTF-8"));
            log.info(
                    "=================================================request end=================================================");
        }

        private void traceResponse(ClientHttpResponse response) throws IOException {
            StringBuilder inputStringBuilder = new StringBuilder();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }
            log.info(
                    "================================================response begin================================================");
            log.info("Status code  : {}", response.getStatusCode());
            log.info("Status text  : {}", response.getStatusText());
            log.info("Headers      : {}", response.getHeaders());
            log.info("Response body: {}", inputStringBuilder.toString());
            log.info(
                    "=================================================response end=================================================");
        }
    }
}
