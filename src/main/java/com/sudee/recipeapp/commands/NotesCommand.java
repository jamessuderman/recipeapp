/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 04/25/2018
 */

package com.sudee.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private String id;
    private String recipeNotes;
}
