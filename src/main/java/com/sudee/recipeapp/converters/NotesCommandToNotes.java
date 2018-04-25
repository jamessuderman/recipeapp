/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 04/25/2018
 */

package com.sudee.recipeapp.converters;

import com.sudee.recipeapp.commands.NotesCommand;
import com.sudee.recipeapp.models.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand source) {
        return null;
    }
}
