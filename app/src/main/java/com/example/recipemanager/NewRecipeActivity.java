package com.example.recipemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NewRecipeActivity extends AppCompatActivity {

    ImageView chooseImage;
    Button chooseButton;
    private ArrayList<Integer> idsIngredients = new ArrayList<>();
    private ArrayList<Integer> idsMeasures = new ArrayList<>();
    private int recipeId;
    private List<Integer> selectedCategoryList;
    private List<Category> categoryList;
    private MultipleChooseAdapter adapter;
    private static final int PERMISION_CODE = 1001;
    private static final int IMAGE_PICK_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Views
        chooseImage = findViewById(R.id.recipe_image);
        chooseButton = findViewById(R.id.choose_image_btn);

        fillCategoryList();
        OptionsForCategory();


        //Handle button click
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISION_CODE);

                    } else {
                        //permission already granted
                        pickImageFromGallery();
                    }
                } else {
                    //system os is less then marshmallow
                    pickImageFromGallery();
                }
            }
        });
    }

    public void onAddNewIngredient(View view){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.rlForIngredient);
        LinearLayout linearLayoutHorizontal = new LinearLayout(this);

        EditText etIngredient = new EditText(this);
        etIngredient.setHint(R.string.ingredient_hint);
        LinearLayout.LayoutParams lpIng = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        etIngredient.setId(View.generateViewId());
        idsIngredients.add(etIngredient.getId());
        linearLayoutHorizontal.addView(etIngredient, lpIng);


        EditText etMeasure = new EditText(this);
        etMeasure.setHint(R.string.measure_hint);
        RelativeLayout.LayoutParams lpMea = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        etMeasure.setId(View.generateViewId());
        linearLayoutHorizontal.addView(etMeasure, lpMea);
        idsMeasures.add(etMeasure.getId());
        linearLayout.addView(linearLayoutHorizontal);

    }

    private void OptionsForCategory() {

        RecyclerView recycler_options = (RecyclerView) findViewById(R.id.recycle_options);
        recycler_options.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        adapter = new MultipleChooseAdapter(getBaseContext(), categoryList);
        recycler_options.setLayoutManager(layoutManager);
        recycler_options.setAdapter(adapter);
    }

    public void onClickDone(View view){
        selectedCategoryList = adapter.getFilterArray();
        EditText nameView = (EditText) findViewById(R.id.recipeName_detail);
        String nameText = nameView.getText().toString();
        EditText instructionView = (EditText) findViewById(R.id.recipeInstruction_detail);
        String instructionText = instructionView.getText().toString();



        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
        try{
            SQLiteDatabase db = recipeDatabaseHelper.getWritableDatabase();
            ContentValues recipeValues = new ContentValues();
            recipeValues.put("NAME", nameText);
            recipeValues.put("INSTRUCTION", instructionText);
            recipeValues.put("IMAGE_RESOURCE_ID", R.drawable.pineapplepizza);
            db.insert("RECIPE", null, recipeValues);
            db.close();
        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable 1", Toast.LENGTH_SHORT).show();
        }

        try {
            SQLiteDatabase db = recipeDatabaseHelper.getReadableDatabase();
            Cursor cursorRecipeId = db.rawQuery("SELECT RECIPE._id FROM RECIPE WHERE RECIPE.NAME = '"+ nameText+"'", null);
            if (cursorRecipeId.moveToFirst()){
                recipeId = cursorRecipeId.getInt(0);
            }
            cursorRecipeId.close();
            db.close();
        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable 2", Toast.LENGTH_SHORT).show();
        }

        try {
            SQLiteDatabase db = recipeDatabaseHelper.getWritableDatabase();
            for (Integer categoryId : selectedCategoryList){
                ContentValues recipeCategoryValues = new ContentValues();
                recipeCategoryValues.put("RECIPE_ID", recipeId);
                recipeCategoryValues.put("CATEGORY_ID", categoryId);
                db.insert("RECIPECATEGORY", null, recipeCategoryValues);
                recipeCategoryValues.clear();
            }
            db.close();
        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable 3", Toast.LENGTH_SHORT).show();
        }

        EditText ingredientView = (EditText) findViewById(R.id.recipeIngredient_detail);
        String ingredientText = ingredientView.getText().toString();
        EditText measureView = (EditText) findViewById(R.id.recipeMeasure_detail);
        String measureText = measureView.getText().toString();
        try {
            SQLiteDatabase db = recipeDatabaseHelper.getWritableDatabase();
            ContentValues ingredientValues = new ContentValues();
            ingredientValues.put("NAME", ingredientText);
            ingredientValues.put("MEASURE", measureText);
            ingredientValues.put("RECIPE_ID", recipeId);
            db.insert("INGREDIENT", null, ingredientValues);
            if (idsIngredients != null && idsMeasures != null){
                for (int i = 0; i < idsIngredients.size(); i++){

                    EditText addedIngredientView = (EditText) findViewById(idsIngredients.get(i));
                    String addedIngredientText = addedIngredientView.getText().toString();
                    EditText addedMeasureView = (EditText) findViewById(idsMeasures.get(i));
                    String addedMeasureText = addedIngredientView.getText().toString();
                    ContentValues addedIngredientValues = new ContentValues();
                    addedIngredientValues.put("NAME", addedIngredientText);
                    addedIngredientValues.put("MEASURE", addedMeasureText);
                    addedIngredientValues.put("RECIPE_ID", recipeId);
                    db.insert("INGREDIENT", null, addedIngredientValues);

                }
                db.close();
            } else {
                Toast.makeText(this, "No Ingredients Passed", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable 4", Toast.LENGTH_SHORT).show();
        }

        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), "New Recipe created!", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewRecipeActivity.this, "Undone!", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permision denied...!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set image to image view
            chooseImage.setImageURI(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void fillCategoryList() {
        categoryList = new ArrayList<>();
        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
        try {
            SQLiteDatabase db = recipeDatabaseHelper.getReadableDatabase();
            Cursor cursorCategory = db.query("CATEGORY",
                    new String[] {"_id", "NAME"},
                    null, null, null, null, null);
            while(cursorCategory.moveToNext()){
                int id = cursorCategory.getInt(0);
                String name = cursorCategory.getString(1);
                categoryList.add(new Category(id, name));
            }
            cursorCategory.close();
            db.close();
        } catch (SQLException e){
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}
