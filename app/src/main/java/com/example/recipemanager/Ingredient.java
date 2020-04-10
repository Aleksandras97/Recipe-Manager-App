package com.example.recipemanager;

public class Ingredient {

    private int id;
    private String name;
    private int measure;
    private int recipe_id;

    public Ingredient(String name, int measure) {
        this.name = name;
        this.measure = measure;
    }

    public Ingredient(int id, String name, int measure) {
        this.id = id;
        this.name = name;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public int getMeasure() {
        return measure;
    }

    public int getRecipe_id() {
        return recipe_id;
    }
}
