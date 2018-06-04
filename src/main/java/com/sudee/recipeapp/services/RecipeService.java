package com.sudee.recipeapp.services;

import com.sudee.recipeapp.commands.RecipeCommand;
import com.sudee.recipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String l);

    RecipeCommand findCommandById(String l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(String idToDelete);
}
