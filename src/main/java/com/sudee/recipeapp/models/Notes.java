package com.sudee.recipeapp.models;

import lombok.Data;

@Data
public class Notes {

    private String id;
    private Recipe recipe;
    private String recipeNotes;

}
