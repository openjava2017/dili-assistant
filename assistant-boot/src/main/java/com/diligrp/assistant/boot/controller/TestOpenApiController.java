package com.diligrp.assistant.boot.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/assistant")
public class TestOpenApiController {
    @RequestMapping(value = "/test.do")
    public String staticTest(HttpServletRequest request) {
        return "I'm test url";
    }
}
