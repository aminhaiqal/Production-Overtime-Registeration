package com.example.demo.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * _____________________________________________________________
 * |   This class maps incoming HTTP requests to their 
 * |   corresponding handler functions using URL patterns and 
 * |   HTTP methods. It allows for dynamic addition and removal 
 * |   of routes, making it easy to manage the behavior of a web 
 * |   application's endpoints.
 * |_____________________________________________________________
 */

@Controller
public class router {
    @GetMapping("/")
    public String hello(){
        return "index";
    }
}
