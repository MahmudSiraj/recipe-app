package com.mahmud.recipeapp.controllers;


import com.mahmud.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.debug("Inside the controller...");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
