package com.sudee.recipeapp.services;

import com.sudee.recipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
