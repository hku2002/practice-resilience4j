package com.example.circuitbreaker.common.service;

import com.example.circuitbreaker.common.exception.IgnoreException;
import com.example.circuitbreaker.common.exception.RecordException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CircuitBreakerService {

    @CircuitBreaker(name = "simpleCircuitBreakerConfig", fallbackMethod = "fallback")
    public String process(String param) throws InterruptedException {
        return sampleAnotherServer(param);
    }

    public String sampleAnotherServer(String param) throws InterruptedException {
        if ("record".equals(param)) {
            throw new RecordException("record exception");
        } else if ("Ignore".equals(param)) {
            throw new IgnoreException("ignore exception");
        } else if ("timeout".equals(param)) {
            Thread.sleep(3500L);
        }
        return "success";
    }

    private String fallback(String param, Exception e) {
        log.info("fallback called");
        log.info("fallback request param: {}", param);
        return "Recovered: " + e;
    }
}
