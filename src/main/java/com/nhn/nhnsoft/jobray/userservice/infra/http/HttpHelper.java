package com.nhn.nhnsoft.jobray.userservice.infra.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhn.nhnsoft.jobray.userservice.infra.http.exception.FailToEncodeUrlException;
import com.nhn.nhnsoft.jobray.userservice.infra.http.exception.FailToGetUriException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;

public class HttpHelper {

    /**
     * Path 적용
     *
     * @param url
     * @param segments
     * @return
     */
    public static String AddPath(String url, String... segments) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        builder.pathSegment(segments);

        return builder.build(false).toUriString();
    }

    /**
     * 페이징 및 sort 적용
     *
     * @param url
     * @param pageable
     * @param orderCol
     * @param sortAsc
     * @return
     */
    public static String getURLWithPagedRequest(String url, Pageable pageable, String orderCol, String sortAsc) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (pageable != null) {
            builder.queryParam("page", pageable.getPageNumber());
            builder.queryParam("size", pageable.getPageSize());
            if (orderCol != null && !orderCol.isEmpty()) {
                builder.queryParam("sort", String.format("%s,%s", orderCol,
                        sortAsc != null && sortAsc.equalsIgnoreCase("true") ? "asc" : "desc"));
            }
        }

        return builder.build(false).toUriString();
    }

    /**
     * Object 를 JSON 형식으로 바꿔서 HttpEntity 에 넣기
     *
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static HttpEntity<String> getHttpEntityWithJSON(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(objectMapper.writeValueAsString(obj), httpHeaders);

        return httpEntity;
    }

    /**
     * url 에 파라미터 적용
     *
     * @param url
     * @param params
     * @return
     */
    public static String getURLWithParameters(String url, HashMap<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value != null && !value.isEmpty()) {
                builder.queryParam(key, value);
            }
        }

        return builder.build(false).toUriString();
    }

    public static String getEncodedUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new FailToEncodeUrlException("URL Encoding Exception occured. url is \'" + url + "\'");
        }
    }

    public static URI getUri(String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new FailToGetUriException("URI Syntax Exception occred. uri is \'" + uri + "\'");
        }
    }
}