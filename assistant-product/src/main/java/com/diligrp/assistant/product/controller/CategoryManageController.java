package com.diligrp.assistant.product.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
public class CategoryManageController {
    @RequestMapping(value = "/test.do")
    public String test(HttpServletRequest request) {
        return "I'm test url";
    }
}
