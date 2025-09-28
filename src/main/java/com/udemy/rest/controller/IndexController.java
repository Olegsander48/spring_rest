package com.udemy.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping({"", "/", "/index", "/main"})
    public String getIndex() {
        return "Hello world";
    }
}
