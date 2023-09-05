package com.ing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    @RequestMapping(value = "/login")
    public String login() {
        System.out.println("login"); 
        return "login";
    }

    @RequestMapping(value = "/register")
    public String register() {
        System.out.println("register"); 
        return "register";
    }

}
