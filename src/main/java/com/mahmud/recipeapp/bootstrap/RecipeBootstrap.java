package com.mahmud.recipeapp.bootstrap;

import com.mahmud.recipeapp.domain.*;
import com.mahmud.recipeapp.repositories.CategoryRepository;
import com.mahmud.recipeapp.repositories.RecipeRepository;
import com.mahmud.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Data loaded to repository...");
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(1);

        //get units of measure using query-methods
        Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByUom("Each");

        if (!eachOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure is not found");
        }

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        if (!teaspoonOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure is not found");
        }

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByUom("Tablespoon");

        if (!tablespoonOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure is not found");
        }

        Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByUom("Dash");

        if (!dashOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure is not found");
        }

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByUom("Cup");

        if (!cupOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure is not found");
        }

        //get optionals
        UnitOfMeasure eachUOM = eachOptional.get();
        UnitOfMeasure teaspoonUOM = teaspoonOptional.get();
        UnitOfMeasure tablespoonUOM = tablespoonOptional.get();
        UnitOfMeasure dashUOM = dashOptional.get();
        UnitOfMeasure cupUOM = cupOptional.get();


        //get categories
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category is not found");
        }


        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category is not found");
        }

        Category mexicanCategory = mexicanCategoryOptional.get();
        Category americanCategory = americanCategoryOptional.get();

        // Perfect Guacamole recipe
        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setPrepTime(0);
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, " +
                "cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. " +
                "Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted " +
                "into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. " +
                "As soon as you see pockets of the air start to puff up in the tortilla, " +
                "turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. " +
                "Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. " +
                "Serve with lime wedges.");
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("Simply Recipes");

        Notes perfectGuacamoleNotes = new Notes();
        perfectGuacamoleNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");

        perfectGuacamole.setNotes(perfectGuacamoleNotes);

        perfectGuacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal(0.5), teaspoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("lime juice", new BigDecimal(1), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("red onion", new BigDecimal(0.25), cupUOM));
        perfectGuacamole.addIngredient(new Ingredient("serrano chillies", new BigDecimal(2), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("black pepper", new BigDecimal(1), dashUOM));
        perfectGuacamole.addIngredient(new Ingredient("tomato", new BigDecimal(0.5), eachUOM));

        perfectGuacamole.getCategories().add(mexicanCategory);
        perfectGuacamole.getCategories().add(americanCategory);

        recipes.add(perfectGuacamole);

        return recipes;

    }

}