package com.example.soap.res;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Default {

    @GetMapping("/")
    String index() {
        return "index";
    }
}
