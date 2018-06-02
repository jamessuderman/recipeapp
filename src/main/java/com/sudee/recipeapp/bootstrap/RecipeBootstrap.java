package com.sudee.recipeapp.bootstrap;

import com.sudee.recipeapp.models.*;
import com.sudee.recipeapp.repositories.CategoryRepository;
import com.sudee.recipeapp.repositories.RecipeRepository;
import com.sudee.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Profile("default")
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
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tablespoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaspoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonOptional.get();
        UnitOfMeasure dashUom = dashOptional.get();
        UnitOfMeasure pintUom = pintOptional.get();
        UnitOfMeasure cupUom = cupOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setServings(12);
        guacRecipe.setUrl("http://www.recipes.com");
        guacRecipe.setSource("Sharon Suderman");
        guacRecipe.setDifficulty(Difficulty.BREEZE);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("Ripe Avacado", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher Salt", new BigDecimal(.5), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("Fresh Lime Juice", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Minced Red Onion", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Serrano Chiles", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Fresh Grated Black Pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("Ripe Tomato", new BigDecimal(.5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacoRecipe.setCookTime(9);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setServings(8);
        tacoRecipe.setUrl("http://www.recipes.com");
        tacoRecipe.setSource("Sharon Suderman");
        tacoRecipe.setDifficulty(Difficulty.STUMPER);
        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");

        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(.5), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Clove of Garlic", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Orange Zest", new BigDecimal(1), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Fresh Squeezed Orange Juice", new BigDecimal(3), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Boneless Chicken Thighs", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Small Corn Tortillas", new BigDecimal(8), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Packed Baby Arugala", new BigDecimal(3), cupUom));
        tacoRecipe.addIngredient(new Ingredient("Medium Ripe Avacado", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Radishes", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Cherry Tomato", new BigDecimal(.5), pintUom));
        tacoRecipe.addIngredient(new Ingredient("Red Onion", new BigDecimal(.25), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Sour Cream", new BigDecimal(4), cupUom));
        tacoRecipe.addIngredient(new Ingredient("Lime Wedges", new BigDecimal(4), eachUom));

        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);
        return recipes;
    }
}
