package com.mahmud.recipeapp.services;

import com.mahmud.recipeapp.commands.RecipeCommand;
import com.mahmud.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
