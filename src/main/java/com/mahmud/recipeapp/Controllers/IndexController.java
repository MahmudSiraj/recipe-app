package com.mahmud.recipeapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
        System.out.println("Heyy!!salsfkaskaskjsa");
        return "index";
    }
}
