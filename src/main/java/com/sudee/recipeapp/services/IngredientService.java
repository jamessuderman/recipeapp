/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 05/09/2018
 */

package com.sudee.recipeapp.services;

import com.sudee.recipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findRecipeByIngredientId(String recipeId, String ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteIngredient(String recipeId, String id);
}
