package com.example.retry.service;

import com.example.retry.common.exception.IgnoreException;
import com.example.retry.common.exception.RetryException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

    @Retry(name = "simpleRetryConfig", fallbackMethod = "fallback")
    public String process(String param) {
        return sampleAnotherServer(param);
    }

    public String sampleAnotherServer(String param) {
        throw new RetryException("retry exception");
//        throw new IgnoreException("ignore exception");
    }

    private String fallback(String param, Exception e) {
        log.info("fallback called");
        log.info("fallback request param: {}", param);
        return "Recovered: " + e;
    }

}
