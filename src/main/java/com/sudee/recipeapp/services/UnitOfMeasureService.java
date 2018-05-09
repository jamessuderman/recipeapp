/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 05/09/2018
 */

package com.sudee.recipeapp.services;

import com.sudee.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
