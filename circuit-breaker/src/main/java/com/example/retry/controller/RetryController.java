package com.example.retry.controller;

import com.example.retry.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class RetryController {

    private final RetryService retryService;

    @GetMapping("/retry")
    public String retry(@RequestParam String param) {
        return retryService.process(param);
    }
}
