/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 04/25/2018
 */

package com.sudee.recipeapp.commands;

import com.sudee.recipeapp.models.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
    private Set<Recipe> recipes;
}
