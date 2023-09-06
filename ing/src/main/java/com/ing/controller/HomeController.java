/**
 * jsp 파일 동작 확인 등 테스트를 위한 Controller
 */

package com.ing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/blank")
    public String blank() {
        System.out.println("blank"); 
        return "layout/blank";
    }

    @GetMapping(value = "/header")
    public String header() {
        System.out.println("header");
        return "layout/header";
    }

    @GetMapping(value = "/nav")
    public String nav() {
        System.out.println("nav");
        
        return "layout/nav";
    }

    @GetMapping(value = "/footer")
    public String footer() {
        System.out.println("footer");
                
        return "layout/footer";
    }

}
