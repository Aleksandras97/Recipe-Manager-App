package com.example.recipemanager;

public class Ingredient {

    private int id;
    private String name;
    private String measure;
    private int recipe_id;

    public Ingredient(String name, String measure, int recipe_id) {
        this.name = name;
        this.measure = measure;
        this.recipe_id = recipe_id;
    }

    public Ingredient(int id, String name, String measure, int recipe_id) {
        this.id = id;
        this.name = name;
        this.measure = measure;
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    public int getRecipe_id() {
        return recipe_id;
    }
}
