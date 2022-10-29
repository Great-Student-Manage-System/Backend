package com.example.demo.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        requestURI = URLDecoder.decode(requestURI, StandardCharsets.UTF_8);
        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) return;
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json") && cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
            String uuid = objectMapper.readTree(cachingResponse.getContentAsByteArray()).get("uuid").asText();
            log.info("RequestID : {}, RequestURI : {}, Request Body : {}", uuid, requestURI, objectMapper.readTree(cachingRequest.getContentAsByteArray()));
            if(ex!=null) {
                log.info("RequestID : {}, Response Body : {}", uuid, objectMapper.readTree(cachingResponse.getContentAsByteArray()));
            }else {
                log.error("RequestID : {}, Error Body : {}", uuid, objectMapper.readTree(cachingResponse.getContentAsByteArray()));
            }
        }
    }
}
