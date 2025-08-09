package com.example.study_quiz_ai.modules;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.core.base.BaseController;

@RestController
public class HelloController extends BaseController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello, World!";
    }
}
