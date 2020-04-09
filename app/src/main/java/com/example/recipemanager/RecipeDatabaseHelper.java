package com.example.recipemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "recipe manager";
    private static final int DB_VERSION = 1;
    private static final String RECIPE = "RECIPE";
    private static final String CATEGORY = "CATEGORY";
    private static final String INGREDIENT = "INGREDIENT";
    private static final String RECIPE_CATEGORY = "RECIPE-CATEGORY";

    RecipeDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ RECIPE +" (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "INSTRUCTION TEXT," +
                "IMAGE_RESOURCE_ID INTEGER);");
        db.execSQL("CREATE TABLE "+ CATEGORY +" (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME STRING);");
        db.execSQL("CREATE TABLE "+ RECIPE_CATEGORY +" (RECIPE_ID INTEGER," +
                "CAREGORY_ID INTEGER," +
                "FOREIGN KEY(RECIPE_ID) REFERENCES "+ RECIPE +" (_id)," +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES "+ CATEGORY +" (_id))");
        db.execSQL("CREATE TABLE "+ INGREDIENT +" (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "MEASURE TEXT," +
                "RECIPE_ID INTEGER," +
                "FOREIGN KEY(RECIPE_ID) REFERENCES "+ RECIPE +" (_id));");
        insertRecipe(db, "Summer Pudding", "Crush the biscuits and most of the almonds inside a plastic food bag using a rolling pin", R.drawable.homemadechickenpotpie);
        insertRecipe(db, "diavolo", "Heat the oil in a deep frying pan and fry the beef mince for 4-5 minutes", R.drawable.diavolo);
        insertCategory(db, "Pica");
        insertCategory(db, "Dessert");
        insertCategory(db, "Lamb");
        insertCategory(db, "Breakfast");
        insertIngredient(db, "Strawberries", "300g", 1);
        insertIngredient(db, "Blackberries", "250g", 1);
        insertIngredient(db, "Redcurrants", "100g", 1);
        insertIngredient(db, "Shoulder", "300g", 2);
        insertIngredient(db, "Flour", "250g", 2);
        insertIngredient(db, "Vegetable Oil", "100g", 2);
        insertRecipeCategory(db, 1, 2);
        insertRecipeCategory(db, 1, 4);
        insertRecipeCategory(db, 2, 1);
        insertRecipeCategory(db, 2, 3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertRecipe(SQLiteDatabase db, String name,
                                     String instruction, int resourceId){
        ContentValues recipeValues = new ContentValues();
        recipeValues.put("NAME", name);
        recipeValues.put("INSTRUCTION", instruction);
        recipeValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert(RECIPE, null, recipeValues);
    }

    private static void insertCategory(SQLiteDatabase db,
                                       String name){
        ContentValues categoryValues = new ContentValues();
        categoryValues.put("NAME", name);
        db.insert(CATEGORY, null, categoryValues);
    }

    private static void insertIngredient(SQLiteDatabase db,
                                         String name, String measure, int recipe_id){
        ContentValues ingredientValues = new ContentValues();
        ingredientValues.put("NAME", name);
        ingredientValues.put("MEASURE", measure);
        ingredientValues.put("RECIPE_ID", recipe_id);
        db.insert(INGREDIENT, null, ingredientValues);
    }

    private static void insertRecipeCategory(SQLiteDatabase db, int recipe_id,
                                             int category_id){
        ContentValues recipeCategoryValues = new ContentValues();
        recipeCategoryValues.put("RECIPE_ID", recipe_id);
        recipeCategoryValues.put("CAREGORY_ID", category_id);
        db.insert(RECIPE_CATEGORY, null, recipeCategoryValues);
    }
}
