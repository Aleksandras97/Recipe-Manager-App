package com.example.recipemanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.w3c.dom.Text;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "Recipe Item";
    private List<Recipe> recipeList;
    private String categories = "";
    private String ingredientNames = "";
    private String ingredientMeasures = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //Display details of Recipe
        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra(EXTRA_RECIPE_ID);

        int recipeId = recipe.getId();
        String recipeName = recipe.getName();
        String recipeInstruction = recipe.getInstruction();
        int recipeImage = recipe.getImageResource();
        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
        try {
            SQLiteDatabase db = recipeDatabaseHelper.getReadableDatabase();
            Cursor cursorCategories = db.rawQuery("SELECT CATEGORY.NAME " +
                    "FROM CATEGORY " +
                    "INNER JOIN RECIPECATEGORY ON CATEGORY._id = RECIPECATEGORY.CAREGORY_ID " +
                    "INNER JOIN RECIPE ON RECIPE._id = RECIPECATEGORY.RECIPE_ID " +
                    "WHERE RECIPE._id = "+ recipeId, null);
            Cursor cursorIngredients = db.rawQuery("SELECT INGREDIENT.NAME, INGREDIENT.MEASURE " +
                    "From INGREDIENT " +
                    "INNER JOIN RECIPE ON RECIPE._id = INGREDIENT.RECIPE_ID " +
                    "WHERE RECIPE._id = "+ recipeId, null);
            while(cursorCategories.moveToNext()){
                String categoryName = cursorCategories.getString(0);
                categories = categories + categoryName + " ";
            }
            while (cursorIngredients.moveToNext()){
                String ingredientName = cursorIngredients.getString(0);
                String ingredientMeasure = cursorIngredients.getString(1);
                ingredientNames = ingredientNames + ingredientName + "\n";
                ingredientMeasures = ingredientMeasures + ingredientMeasure + " \n";
            }
            cursorCategories.close();
            cursorIngredients.close();
            db.close();
        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(recipeName);
        ImageView imageView = (ImageView) findViewById(R.id.recipe_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, recipeImage));
        imageView.setContentDescription(recipeName);
        TextView instructionTextView = (TextView) findViewById(R.id.recipe_instructions);
        instructionTextView.setText(recipeInstruction);
        TextView categoriesTextView = (TextView) findViewById(R.id.recipe_category);
        categoriesTextView.setText(categories);
        TextView ingredientTextView = (TextView) findViewById(R.id.ingredients);
        TextView measureTextView = (TextView) findViewById(R.id.mesure);
        ingredientTextView.setText(ingredientNames);
        measureTextView.setText(ingredientMeasures);
        categories = "";
        ingredientNames = "";
        ingredientMeasures = "";

    }
}
