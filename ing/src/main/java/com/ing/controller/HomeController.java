// 테스트용 파일이라서 별도 주석X

package com.ing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/blank")
    public String blank() {
        System.out.println("blank"); 
        return "layout/blank";
    }

    @RequestMapping(value = "/header")
    public String header() {
        System.out.println("header");
        return "layout/header";
    }

    @RequestMapping(value = "/nav")
    public String nav() {
        System.out.println("nav");
        
        return "layout/nav";
    }

    @RequestMapping(value = "/footer")
    public String footer() {
        System.out.println("footer");
                
        return "layout/footer";
    }

}
