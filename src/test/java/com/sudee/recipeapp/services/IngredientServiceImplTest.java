/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 05/09/2018
 */

package com.sudee.recipeapp.services;

import com.sudee.recipeapp.commands.IngredientCommand;
import com.sudee.recipeapp.converters.IngredientCommandToIngredient;
import com.sudee.recipeapp.converters.IngredientToIngredientCommand;
import com.sudee.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.sudee.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.sudee.recipeapp.models.Ingredient;
import com.sudee.recipeapp.models.Recipe;
import com.sudee.recipeapp.repositories.RecipeRepository;
import com.sudee.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeRepository, unitOfMeasureRepository);
    }

    @Test
    public void findRecipeIdAndId() throws Exception {

    }

    @Test
    public void findRecipeIdAndHappyPath() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2");
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById("1")).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findRecipeByIngredientId("1", "3");

        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById("1");
    }

    @Test
    public void testSaveRecipeCommand() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId("3");
        command.setRecipeId("2");

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId("3");

        when(recipeRepository.findById("1")).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById("1");
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testDeleteIngredient() {
        //given
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId("3");
        recipe.addIngredient(ingredient);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById("1")).thenReturn(recipeOptional);

        //when
        ingredientService.deleteIngredient("1", "3");

        //then
        verify(recipeRepository, times(1)).findById("1");
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}
