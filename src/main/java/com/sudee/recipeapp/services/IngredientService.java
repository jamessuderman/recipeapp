/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 05/09/2018
 */

package com.sudee.recipeapp.services;

import com.sudee.recipeapp.commands.IngredientCommand;
import com.sudee.recipeapp.models.Ingredient;

public interface IngredientService {
    IngredientCommand findRecipeByIngredientId(Long recipeId, Long ingredientId);
}
