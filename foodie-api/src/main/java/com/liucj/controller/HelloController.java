package com.liucj.controller;

import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

//@Controller
@ApiIgnore //swagger中不显示
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello(){
        return "hello word";
    }
}
