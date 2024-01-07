package com.programming.userservice.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/demo")
public class DemoController {

    @GetMapping
    public String hellp() {
        return "Hi There";
    }

}
