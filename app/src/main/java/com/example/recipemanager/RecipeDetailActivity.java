package com.example.recipemanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "Recipe Item";
    private List<Recipe> recipeList;

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

        String recipeName = recipe.getName();
        //String recipeCategory = recipe.getCategory();
        String recipeInstruction = recipe.getInstruction();
        int recipeImage = recipe.getImageResource();

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(recipeName);
        ImageView imageView = (ImageView) findViewById(R.id.recipe_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, recipeImage));
        imageView.setContentDescription(recipeName);
        TextView textView = (TextView) findViewById(R.id.recipe_category);
        //textView.setText(recipeCategory);


    }
}
